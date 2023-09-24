package com.example.belablok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        supportActionBar?.hide()

        val newMatchButton: Button = findViewById(R.id.newMatchButton)
        val savedMatchesButton: Button = findViewById(R.id.savedMatchesButton)

        newMatchButton.setOnClickListener {
            val intent = Intent(this, NewMatchScreen::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
    }
}