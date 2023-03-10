package com.example.belablok

import MyListAdapter
import android.R.attr.value
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson


class ListView : ComponentActivity() {

    var gameHands = mutableListOf<GameHand>()
    lateinit var listView: ListView
    lateinit var customAdapter: MyListAdapter
    lateinit var tvMiPoints: TextView
    lateinit var tvViPoints: TextView
    lateinit var tvMiPointsToWin: TextView
    lateinit var tvViPointsToWin: TextView
    lateinit var tvPointDifference: TextView
    lateinit var tvViMatchPoints: TextView
    lateinit var tvMiMatchPoints: TextView
    lateinit var tvNG: TextView

    var miMatchPoints = 0
    var viMatchPoints = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.google.android.material.R.style.Theme_AppCompat_DayNight_DarkActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_activity)

        this.actionBar?.hide()

        tvNG = findViewById(R.id.tvNG)
        tvMiPoints = findViewById(R.id.tvMiPoints)
        tvViPoints = findViewById(R.id.tvViPoints)
        tvMiPointsToWin = findViewById(R.id.tvMiPointsToWin)
        tvViPointsToWin = findViewById(R.id.tvViPointsToWin)
        tvPointDifference = findViewById(R.id.tvPointDifference)
        tvMiMatchPoints = findViewById(R.id.miMatchPoints)
        tvViMatchPoints = findViewById(R.id.viMatchPoints)

        listView = findViewById(R.id.listView)
        customAdapter = MyListAdapter(this, R.layout.listview_item, gameHands)


        tvNG.setOnClickListener {
            openSomeActivityForResultNG()
        }

        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val data = Gson().toJson(gameHands[i])
            //gameRounds[i] = GameRound()
            openSomeActivityForResult(data, i)
        }
        listView.setOnItemLongClickListener { adapterView, view, i, l ->

            AlertDialog.Builder(this)
                .setTitle("Delete round")
                .setMessage("Are you sure you want to delete this round?") // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which ->
                        if (!gameHands[i].matchPointsListItemFlag){
                            gameHands.removeAt(i)
                            updateScoreBoard()
                            customAdapter.notifyDataSetChanged()
                        }
                    }) // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
            true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                val myIntent = Intent(this, Stats::class.java)

                resultLauncher.launch(myIntent)
                overridePendingTransition(R.anim.up_down, R.anim.nothing)
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
    private fun openSomeActivityForResult(data: String, i: Int) {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("gameRound", data)
        intent.putExtra("index", i.toString())
        intent.putExtra("newGameRound", "false")
        resultLauncher.launch(intent)
        overridePendingTransition(R.anim.left_right, R.anim.nothing)
    }
    private fun openSomeActivityForResultNG() {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("newGameRound", "true")
        resultLauncherNG.launch(intent)
        overridePendingTransition(R.anim.left_right, R.anim.nothing)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val exsistingGameHand = Gson().fromJson(data?.getStringExtra("gameRound"), GameHand::class.java)

            gameHands[data?.getStringExtra("index")?.let { Integer.parseInt(it) }!!] = exsistingGameHand

            updateScoreBoard()

            customAdapter.notifyDataSetChanged()
        }
    }

    private var resultLauncherNG = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val newGameHand = Gson().fromJson(data?.getStringExtra("gameRound"), GameHand::class.java)
            gameHands.add(newGameHand)

            updateScoreBoard()

            customAdapter.notifyDataSetChanged()
        }
    }

    private fun updateScoreBoard(){
        var miPointsSum = 0
        var viPointsSum = 0
        for (gr in gameHands){
            if (gr.matchPointsListItemFlag){
                miPointsSum = 0
                viPointsSum = 0
            }
            miPointsSum += gr.getMiPointsSum()
            viPointsSum += gr.getViPointsSum()
        }
        tvMiPoints.text = miPointsSum.toString()
        tvViPoints.text = viPointsSum.toString()

        tvMiPointsToWin.text = if ((1001 - miPointsSum) > 0) (1001 - miPointsSum).toString() else "0"
        tvViPointsToWin.text = if ((1001 - viPointsSum) > 0) (1001 - viPointsSum).toString() else "0"

        val pointDifference = (kotlin.math.abs(miPointsSum - viPointsSum)).toString()

        tvPointDifference.text = pointDifference

        if ((miPointsSum >= 1001) or (viPointsSum >= 1001)) { setUpNewGame(miPointsSum, viPointsSum) }
    }

    private fun setUpNewGame(miPointsSum: Int, viPointsSum: Int) {
        val matchPointListItem = GameHand()
        if (miPointsSum >= 1001) { miMatchPoints += 1 }
        if (viPointsSum >= 1001) { viMatchPoints += 1 }

        matchPointListItem.miMatchPoints = miMatchPoints
        matchPointListItem.viMatchPoints = viMatchPoints
        matchPointListItem.matchPointsListItemFlag = true

        gameHands.add(matchPointListItem)

        tvMiMatchPoints.text = miMatchPoints.toString()
        tvViMatchPoints.text = viMatchPoints.toString()

        tvMiPoints.text = "0"
        tvViPoints.text = "0"

        //showAlertDialog()
    }

    private fun showAlertDialog() {
        // Create an alert builder
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.match_won_popup)
        val alertDialog = builder.create()

        val tvMiPoints = findViewById<TextView>(R.id.miPointsNum)
        val tvViPoints = findViewById<TextView>(R.id.viPointsNum)

        var miPointsSum = 0
        var viPointsSum = 0

        for (gr in gameHands){
            if (gr.matchPointsListItemFlag){
                miPointsSum = 0
                viPointsSum = 0
            }
            miPointsSum += gr.getMiPointsSum()
            viPointsSum += gr.getViPointsSum()
        }

        tvMiPoints.text = miPointsSum.toString()
        tvViPoints.text = viPointsSum.toString()

        alertDialog.show()
    }
    private fun sendDialogDataToActivity(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}