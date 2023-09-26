package com.example.belablok.newmatch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.belablok.R
import com.example.belablok.roundlist.RoundListScreen
import com.example.belablok.storage.UserStorage


class NewMatchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)

        supportActionBar?.hide()

        val kingsImage = findViewById<ImageView>(R.id.kingsImage)

        val player1Spinner = findViewById<Spinner>(R.id.player1Name)
        val player2Spinner = findViewById<Spinner>(R.id.player2Name)
        val player3Spinner = findViewById<Spinner>(R.id.player3Name)
        val player4Spinner = findViewById<Spinner>(R.id.player4Name)
        val dataStorage  = UserStorage(applicationContext)
        val usersList = dataStorage.loadData().map { it.name }

        Log.v("userList", usersList.size.toString())
        
        var player1Name = ""
        var player2Name = ""
        var player3Name = ""
        var player4Name = ""

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usersList)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player1Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player2Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player3Spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        player4Spinner.adapter = adapter

        player1Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { player1Name = parent?.selectedItem as String }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player2Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { player2Name = parent?.selectedItem as String }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player3Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { player3Name = parent?.selectedItem as String }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        player4Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { player4Name = parent?.selectedItem as String }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        kingsImage.setOnClickListener {
            val intent = Intent(this, RoundListScreen::class.java)
            intent.putExtra("Player1", player1Name)
            intent.putExtra("Player2", player2Name)
            intent.putExtra("Player3", player3Name)
            intent.putExtra("Player4", player4Name)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }

        for (usr in usersList) {
            Log.v("userList", usr!!)
        }
    }
}