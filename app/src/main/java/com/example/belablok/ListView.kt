package com.example.belablok

import MyListAdapter
import android.R.attr.value
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson


class ListView : ComponentActivity() {

    var gameRounds = mutableListOf<GameRound>()
    lateinit var listView: ListView
    lateinit var customAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_activity)

        this.actionBar?.hide()

        val tvNG: TextView = findViewById(R.id.tvNG)
        listView = findViewById(R.id.listView)
        customAdapter = MyListAdapter(this, R.layout.listview_item, gameRounds)


        tvNG.setOnClickListener {
            openSomeActivityForResultNG()
        }

        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val data = Gson().toJson(gameRounds[i])
            gameRounds[i] = GameRound()
            openSomeActivityForResult(data)
        }
        listView.setOnItemLongClickListener { adapterView, view, i, l ->
            gameRounds.removeAt(i)
            customAdapter.notifyDataSetChanged()
            true
        }
    }

    private fun openSomeActivityForResult(data: String) {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("gameRound", data)
        intent.putExtra("newGameRound", "false")
        resultLauncher.launch(intent)
    }
    private fun openSomeActivityForResultNG() {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("newGameRound", "true")
        resultLauncherNG.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val exsistingGameRound = Gson().fromJson(data?.getStringExtra("gameRound"), GameRound::class.java)

            for (gr in gameRounds){
                if (gr.miPoints == 0 && gr.viPoints == 0){
                    gameRounds[gameRounds.indexOf(gr)] = exsistingGameRound
                    break
                }
            }
            customAdapter.notifyDataSetChanged()
        }
    }

    private var resultLauncherNG = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val newGameRound = Gson().fromJson(data?.getStringExtra("gameRound"), GameRound::class.java)
            gameRounds.add(newGameRound)
            customAdapter.notifyDataSetChanged()
        }
    }
}