package com.example.belablok.savedMatches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.example.belablok.R
import com.example.belablok.roundlist.RoundListAdapter
import com.example.belablok.storage.MatchStorage
import com.example.belablok.storage.User
import com.example.belablok.storage.UserStorage

class SavedMatches : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_matches)

        this.actionBar?.hide()

        val listView: ListView = findViewById(R.id.matchListView)

        val dataStorage = MatchStorage(applicationContext)
        var savedMatches = dataStorage.loadData()?.toMutableList()
    }
}