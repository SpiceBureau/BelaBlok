package com.example.belablok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        supportActionBar?.hide()

        val kingsImage = findViewById<ImageView>(R.id.kingsImage)

/*        val player1Name = findViewById<EditText>(R.id.player1Name)
        val player2Name = findViewById<EditText>(R.id.player2Name)
        val player3Name = findViewById<EditText>(R.id.player3Name)
        val player4Name = findViewById<EditText>(R.id.player4Name)

        kingsImage.setOnClickListener {
            val intent = Intent(this, GameList::class.java)
            intent.putExtra("Player1", player1Name.text)
            intent.putExtra("Player2", player2Name.text)
            intent.putExtra("Player3", player3Name.text)
            intent.putExtra("Player4", player4Name.text)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }*/
    }
}