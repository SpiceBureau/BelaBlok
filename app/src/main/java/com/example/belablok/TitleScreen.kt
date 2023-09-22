package com.example.belablok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import kotlin.math.ln
import kotlin.math.pow

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        supportActionBar?.hide()

        val kingsImage = findViewById<ImageView>(R.id.kingsImage)

        val player1Name = findViewById<EditText>(R.id.player1Name)
        val player2Name = findViewById<EditText>(R.id.player2Name)
        val player3Name = findViewById<EditText>(R.id.player3Name)
        val player4Name = findViewById<EditText>(R.id.player4Name)
        var listOfPlayerViews = listOf(player1Name, player2Name, player3Name, player4Name)

        val tableView = findViewById<View>(R.id.table)

        kingsImage.setOnClickListener {
            val intent = Intent(this, GameList::class.java)
            intent.putExtra("Player1", player1Name.text)
            intent.putExtra("Player2", player2Name.text)
            intent.putExtra("Player3", player3Name.text)
            intent.putExtra("Player4", player4Name.text)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
        tableView.setOnClickListener {
            val totalMilliseconds = 6000L // Total time in milliseconds
            var currentIntervalMilliseconds = 60L // Initial interval in milliseconds
            val listOfNames = listOf(player1Name.text, player2Name.text, player3Name.text, player4Name.text).toMutableList()
            val finalSpeed = 1.0 / 900.0

            val countdownTimer = object : CountDownTimer(totalMilliseconds, currentIntervalMilliseconds) {
                override fun onTick(millisUntilFinished: Long) {
                    val initialSpeed = 1.0 / currentIntervalMilliseconds
                    val time = (totalMilliseconds - millisUntilFinished) / 1000 + 0.005
                    val k = ln((finalSpeed / initialSpeed)) / time
                    val newSpeed = (initialSpeed * (Math.E.pow(k * time)))
                    var foo = Math.E.pow(k * time)
                    Log.v("testic", "initialSpeed = $initialSpeed finalSpeed = $finalSpeed newSpeed = $newSpeed what = $foo")

                    currentIntervalMilliseconds = (1 / newSpeed).toLong()
                    listOfNames.shuffle()
                    setPlayerNamesOnTable(listOfNames, listOfPlayerViews)
                }
                override fun onFinish() {}
            }
            countdownTimer.start()
        }
    }

    private fun setPlayerNamesOnTable(
        listOfNames: List<Editable>,
        listOfPlayerViews: List<EditText>
    ) {
        for (playerName in listOfNames) {
            val index = listOfNames.indexOf(playerName)
            listOfPlayerViews[index].text = playerName
        }
    }

    private fun nonlinearFunction(x: Double) = (900.0 - 0.0) * Math.sqrt(1.0) / (100.0 - 0.0) / Math.sqrt((((900.0 - 0.0) / (100.0 - 0.0)).pow(2.0) - 1.0) / 4000.0 * x + 1.0) + 0.0

}