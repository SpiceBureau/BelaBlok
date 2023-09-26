package com.example.belablok.savedMatches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.belablok.R
import com.example.belablok.storage.MatchStorage

class SavedMatches : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_matches_activity)
        supportActionBar?.hide()

        val listView: ListView = findViewById(R.id.matchListView)

        val dataStorage = MatchStorage(applicationContext)
        var savedMatches = dataStorage.loadData()?.toMutableList()
    }
}