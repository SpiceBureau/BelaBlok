package com.example.belablok.screens.newmatch

import UserSpinnerAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.belablok.R
import com.example.belablok.screens.roundlist.RoundListScreen
import com.example.belablok.storage.MatchStorage
import com.example.belablok.storage.UserStorage
import com.example.belablok.storage.data_classes.Match
import com.example.belablok.storage.data_classes.Player
import com.google.gson.Gson
import java.util.Calendar


class NewMatchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_match_activity)

        supportActionBar?.hide()

        val kingsImage = findViewById<ImageView>(R.id.kingsImage)

        val player1Spinner = findViewById<Spinner>(R.id.player1Name)
        val player2Spinner = findViewById<Spinner>(R.id.player2Name)
        val player3Spinner = findViewById<Spinner>(R.id.player3Name)
        val player4Spinner = findViewById<Spinner>(R.id.player4Name)
        val dataStorage  = UserStorage(applicationContext)
        val usersList = dataStorage.loadData()
        /*dataStorage.saveData(listOf())*/

        val newMatch = Match(mutableListOf(), Player(""), Player(""), Player(""), Player(""))
        val c = Calendar.getInstance()
        newMatch.year = c.get(Calendar.YEAR)
        newMatch.month = c.get(Calendar.MONTH) + 1
        newMatch.day = c.get(Calendar.DAY_OF_MONTH)

        val adapter = UserSpinnerAdapter(this, R.layout.user_spinner_item, usersList.map { it.name })

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player1Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player2Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player3Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player4Spinner.adapter = adapter

        player1Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { newMatch.player1 = usersList[position] }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player2Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { newMatch.player2 = usersList[position] }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player3Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { newMatch.player3 = usersList[position] }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player4Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { newMatch.player4 = usersList[position] }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        kingsImage.setOnClickListener {
            val intent = Intent(this, RoundListScreen::class.java)
            val data = Gson().toJson(newMatch)
            intent.putExtra("Current match", data)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
    }
}