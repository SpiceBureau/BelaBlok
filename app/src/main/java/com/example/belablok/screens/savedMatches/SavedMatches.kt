package com.example.belablok.screens.savedMatches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.belablok.R
import com.example.belablok.roundlist.MatchesListAdapter
import com.example.belablok.screens.roundlist.RoundListScreen
import com.example.belablok.storage.MatchStorage
import com.google.gson.Gson

class SavedMatches : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_matches_activity)
        supportActionBar?.hide()

        val listView: ListView = findViewById(R.id.matchListView)

        val dataStorage = MatchStorage(applicationContext)
        var savedMatches = dataStorage.loadData()

        val customAdapter = MatchesListAdapter(this, R.layout.saved_match_item, savedMatches)
        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, RoundListScreen::class.java)
            val data = Gson().toJson(savedMatches?.get(i))
            intent.putExtra("Current match", data)
            intent.putExtra("Archived", true)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
    }
}