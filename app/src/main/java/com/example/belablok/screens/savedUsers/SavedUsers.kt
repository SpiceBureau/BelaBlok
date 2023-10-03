package com.example.belablok.screens.savedUsers

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.belablok.R
import com.example.belablok.roundlist.MatchesListAdapter
import com.example.belablok.roundlist.UsersListAdapter
import com.example.belablok.screens.roundlist.RoundListScreen
import com.example.belablok.storage.MatchStorage
import com.example.belablok.storage.UserStorage
import com.google.gson.Gson

class SavedUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.saved_users_activity)
        supportActionBar?.hide()

        val listView: ListView = findViewById(R.id.savedUsersList)

        val dataStorage = UserStorage(applicationContext)
        var savedUsers = dataStorage.loadData().toMutableList()

        val customAdapter = UsersListAdapter(this, R.layout.saved_users_item, savedUsers)
        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
        }
        listView.setOnItemLongClickListener { adapterView, view, i, l ->

            AlertDialog.Builder(this)
                .setTitle("Delete user?")

                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which ->
                        savedUsers.removeAt(i)
                        customAdapter.notifyDataSetChanged()
                        dataStorage.saveData(savedUsers)
                    })
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
            true
        }
    }
}