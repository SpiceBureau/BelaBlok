package com.example.belablok

import MyListAdapter
import android.app.Activity
import android.content.Intent
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.widget.Adapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts


class ListView : ComponentActivity() {

    var gameRounds = mutableListOf<GameRound>()
    lateinit var listView: ListView
    lateinit var customAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_activity)

        this.actionBar?.hide()

        val tvMi: TextView = findViewById(R.id.tvMi)
        listView = findViewById(R.id.listView)
        customAdapter = MyListAdapter(this, R.layout.listview_item, gameRounds)


        tvMi.setOnClickListener {
            openSomeActivityForResult()
        }

        listView.adapter = customAdapter
    }

    private fun openSomeActivityForResult() {
        val intent = Intent(this, Calculator::class.java)
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val newGameRound = GameRound(Integer.parseInt(data?.getStringExtra("miPoints").toString()), Integer.parseInt(
                data?.getStringExtra("viPoints").toString()
            ))
            gameRounds.add(newGameRound)
            customAdapter.notifyDataSetChanged()
        }
    }
}