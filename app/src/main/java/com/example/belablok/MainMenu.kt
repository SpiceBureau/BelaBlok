package com.example.belablok

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.io.File

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        supportActionBar?.hide()

        val newMatchButton: Button = findViewById(R.id.newMatchButton)
        val savedMatchesButton: Button = findViewById(R.id.savedMatchesButton)
        val addPlayer: ImageView = findViewById(R.id.addPlayerButton)

        newMatchButton.setOnClickListener {
            val intent = Intent(this, NewMatchScreen::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }

        addPlayer.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            val dialogView = layoutInflater.inflate(R.layout.add_user_dialog_layout, null)
            val editTextDialog = dialogView.findViewById<EditText>(R.id.editTextDialog)

            dialogBuilder.setView(dialogView)

            dialogBuilder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
                val newUser = User(editTextDialog.text.toString())

                val dataStorage = UserStorage(applicationContext)
                val usersList = dataStorage.loadData().toMutableList()
                usersList.add(newUser)
                dataStorage.saveData(usersList)
            }

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }
}