package com.example.belablok

import MyListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.ListAdapter


class ListView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_activity)

        this.actionBar?.hide()

        val tvMi: TextView = findViewById(R.id.tvMi)
        var gameRounds = mutableListOf<GameRound>()
        Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
        val extras = intent.extras
        if (extras != null) {
            gameRounds.add(GameRound(Integer.parseInt(extras.getString("miPoints").toString()), Integer.parseInt(extras.getString("viPoints").toString())))
        }

        tvMi.setOnClickListener {
            val i = Intent(this, Calculator::class.java)
            startActivity(i)
        }

        val listView: ListView = findViewById(R.id.listView);

        val customAdapter = MyListAdapter(this, R.layout.listview_item, gameRounds)

        listView.adapter = customAdapter
    }
}