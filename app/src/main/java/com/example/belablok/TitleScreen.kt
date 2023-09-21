package com.example.belablok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        supportActionBar?.hide()

        val kingsImage = findViewById<ImageView>(R.id.kingsImage)

        kingsImage.setOnClickListener {
            val intent = Intent(this, GameList::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
    }
}