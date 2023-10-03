package com.example.belablok.screens.mainmenu

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.belablok.screens.newmatch.NewMatchScreen
import com.example.belablok.R
import com.example.belablok.screens.savedMatches.SavedMatches
import com.example.belablok.screens.savedUsers.SavedUsers
import com.example.belablok.storage.data_classes.User
import com.example.belablok.storage.UserStorage

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)

        supportActionBar?.hide()

        val newMatchButton: Button = findViewById(R.id.newMatchButton)
        val savedMatchesButton: Button = findViewById(R.id.savedMatchesButton)
        val savedUsersButton: Button = findViewById(R.id.savedUsersButton)
        val addPlayer: ImageView = findViewById(R.id.addPlayerButton)

        newMatchButton.setOnClickListener {
            val intent = Intent(this, NewMatchScreen::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
        addPlayer.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            val dialogView = layoutInflater.inflate(R.layout.add_user_dialog_layout, null)
            val editTextDialog = dialogView.findViewById<EditText>(R.id.editTextDialog)

            dialogBuilder.setView(dialogView)

            dialogBuilder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
                val newUser = User(editTextDialog.text.toString())

                val dataStorage = UserStorage(applicationContext)
                val usersList = dataStorage.loadData().toMutableList()
                usersList.add(newUser)
                dataStorage.saveData(usersList)
            }

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
        savedMatchesButton.setOnClickListener {
            val intent = Intent(this, SavedMatches::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
        savedUsersButton.setOnClickListener {
            val intent = Intent(this, SavedUsers::class.java)
            startActivity(intent)

            overridePendingTransition(R.anim.left_right, R.anim.nothing)
        }
    }
}