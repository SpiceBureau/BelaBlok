package com.example.belablok

import MyListAdapter
import android.R.attr.value
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.Window
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson


class GameList : ComponentActivity() {

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
    var globalMiMatchPoints = 0
    var globalViMatchPoints = 0
    var player1 = ""
    var player2 = ""
    var player3 = ""
    var player4 = ""
    var playerToShuffle = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.google.android.material.R.style.Theme_AppCompat_DayNight_DarkActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listview_activity)

        this.actionBar?.hide()

        player1 = intent.getStringExtra("Player1").toString()
        Log.v("player1", player1)
        player2 = intent.getStringExtra("Player2").toString()
        player3 = intent.getStringExtra("Player3").toString()
        player4 = intent.getStringExtra("Player4").toString()
        playerToShuffle = player1

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
            openCalculatorNewGame()
        }

        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
            if (gameHands[i].matchPointsListItemFlag) {
                val myIntent = setUpGraphData(i)
                resultLauncher.launch(myIntent)
                overridePendingTransition(R.anim.up_down, R.anim.nothing)
            }
            else {
                val data = Gson().toJson(gameHands[i])
                //gameRounds[i] = GameRound()
                openCalculator(data, i)
            }
        }
        listView.setOnItemLongClickListener { adapterView, view, i, l ->

            AlertDialog.Builder(this)
                .setTitle("Delete round")
                .setMessage("Are you sure you want to delete this round?") // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which ->
                        if (i == gameHands.size - 2){
                            gameHands.removeAt(i)
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showConfirmationDialog()
            return true // Consume the event, so the default back button behavior is not triggered
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                val myIntent = setUpGraphData(gameHands.size)
                resultLauncher.launch(myIntent)
                overridePendingTransition(R.anim.up_down, R.anim.nothing)
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
    private fun openCalculator(data: String, i: Int) {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("gameRound", data)
        intent.putExtra("index", i.toString())
        intent.putExtra("newGameRoundFlag", "false")
        intent.putExtra("Player1", player1)
        intent.putExtra("Player2", player2)
        intent.putExtra("Player3", player3)
        intent.putExtra("Player4", player4)

        resultLauncher.launch(intent)
        overridePendingTransition(R.anim.left_right, R.anim.nothing)
    }
    private fun openCalculatorNewGame() {
        val intent = Intent(this, Calculator::class.java)
        intent.putExtra("newGameRoundFlag", "true")

        intent.putExtra("Player1", player1)
        intent.putExtra("Player2", player2)
        intent.putExtra("Player3", player3)
        intent.putExtra("Player4", player4)

        if (gameHands.size == 0) { intent.putExtra("shuffler", player1) }
        else {
            if (!gameHands.last().matchPointsListItemFlag) {
                when (gameHands.last().shuffler){
                    player1 -> intent.putExtra("shuffler", player2)
                    player2 -> intent.putExtra("shuffler", player3)
                    player3 -> intent.putExtra("shuffler", player4)
                    player4 -> intent.putExtra("shuffler", player1)
                }
            }
            else {
                if (gameHands.last().matchWin == "Mi") {
                    when (gameHands.elementAt(gameHands.size - 2).shuffler){
                        player1 -> intent.putExtra("shuffler", player3)
                        player2 -> intent.putExtra("shuffler", player3)
                        player3 -> intent.putExtra("shuffler", player1)
                        player4 -> intent.putExtra("shuffler", player1)
                    }
                }
                else {
                    when (gameHands.elementAt(gameHands.size - 2).shuffler){
                        player1 -> intent.putExtra("shuffler", player2)
                        player2 -> intent.putExtra("shuffler", player4)
                        player3 -> intent.putExtra("shuffler", player4)
                        player4 -> intent.putExtra("shuffler", player2)
                    }
                }
            }

        }


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

        var miMatchPoints = 0
        var viMatchPoints = 0

        for (gr in gameHands){
            if (gr.matchPointsListItemFlag){
                miMatchPoints = gr.miMatchPoints
                viMatchPoints = gr.viMatchPoints
                miPointsSum = 0
                viPointsSum = 0
            }
            else{
                miPointsSum += gr.getMiPointsSum()
                viPointsSum += gr.getViPointsSum()
            }
        }
        tvMiPoints.text = miPointsSum.toString()
        tvViPoints.text = viPointsSum.toString()

        tvMiPointsToWin.text = if ((1001 - miPointsSum) > 0) (1001 - miPointsSum).toString() else "0"
        tvViPointsToWin.text = if ((1001 - viPointsSum) > 0) (1001 - viPointsSum).toString() else "0"

        tvPointDifference.text = (kotlin.math.abs(miPointsSum - viPointsSum)).toString()

        tvMiMatchPoints.text = miMatchPoints.toString()
        tvViMatchPoints.text = viMatchPoints.toString()

        globalMiMatchPoints = miMatchPoints
        globalViMatchPoints = viMatchPoints
        if ((miPointsSum >= 1001) or (viPointsSum >= 1001)) { setUpNewGame(miPointsSum) }
    }

    private fun setUpNewGame(miPointsSum: Int) {
        val matchPointListItem = GameHand()

        if (miPointsSum >= 1001){
            globalMiMatchPoints += 1
            matchPointListItem.matchWin = "Mi"
        }
        else {
            globalViMatchPoints += 1
            matchPointListItem.matchWin = "Vi"
        }

        matchPointListItem.miMatchPoints = globalMiMatchPoints
        matchPointListItem.viMatchPoints = globalViMatchPoints
        matchPointListItem.matchPointsListItemFlag = true

        gameHands.add(matchPointListItem)

        tvMiPoints.text = "0"
        tvViPoints.text = "0"

        tvMiMatchPoints.text = globalMiMatchPoints.toString()
        tvViMatchPoints.text = globalViMatchPoints.toString()

        showAlertDialog()
    }

    private fun showAlertDialog() {
        // Create an alert builder
        var miPointsSum = 0
        var viPointsSum = 0
        var miPointsSumNoCalls = 0
        var viPointsSumNoCalls = 0
        var miPointsSumFromCalls = 0
        var viPointsSumFromCalls = 0


        val matchWonDialog = Dialog(this)
        matchWonDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        matchWonDialog.setCancelable(true)
        matchWonDialog.setContentView(R.layout.match_won_popup)

        val miPointsSumTv = matchWonDialog.findViewById<TextView>(R.id.miPointsSum)
        val miPointsNoCallsTv = matchWonDialog.findViewById<TextView>(R.id.miPointsNoCalls)
        val miPointsFromCallsTv = matchWonDialog.findViewById<TextView>(R.id.miPointsFromCalls)

        val viPointsSumTv = matchWonDialog.findViewById<TextView>(R.id.viPointsSum)
        val viPointsNoCallsTv = matchWonDialog.findViewById<TextView>(R.id.viPointsNoCalls)
        val viPointsFromCallsTv = matchWonDialog.findViewById<TextView>(R.id.viPointsFromCalls)

        for (gr in gameHands){
            if (gr.matchPointsListItemFlag){
                if (globalMiMatchPoints + globalViMatchPoints <= gr.miMatchPoints + gr.viMatchPoints){ continue }
                miPointsSum = 0
                miPointsSumNoCalls = 0
                miPointsSumFromCalls = 0
                viPointsSum = 0
                viPointsSumNoCalls = 0
                viPointsSumFromCalls= 0
            }
            else{
                miPointsSum += gr.getMiPointsSum()
                miPointsSumNoCalls += Integer.parseInt(gr.miPoints)
                miPointsSumFromCalls += gr.geMiPointsFromCalls()
                viPointsSum += gr.getViPointsSum()
                viPointsSumNoCalls += Integer.parseInt(gr.viPoints)
                viPointsSumFromCalls += gr.getViPointsFromCalls()
            }
        }

        miPointsSumTv.text = miPointsSum.toString()
        miPointsFromCallsTv.text = getString(R.string.popup_zvanja, miPointsSumFromCalls)
        miPointsNoCallsTv.text = getString(R.string.popup_igra, miPointsSumNoCalls)

        viPointsSumTv.text = viPointsSum.toString()
        viPointsFromCallsTv.text = getString(R.string.popup_zvanja, viPointsSumFromCalls)
        viPointsNoCallsTv.text = getString(R.string.popup_igra, viPointsSumNoCalls)

        matchWonDialog.show()
    }

    private fun setUpGraphData(matchIndex: Int): Intent {
        val myIntent = Intent(this, Stats::class.java)

        val xValues = ArrayList<Double>() // runde
        val yValuesMi = ArrayList<Double>() // iznosi rundi (akumulirani)
        val yValuesVi = ArrayList<Double>()
        var handIndex = 0.0

        var miPointsSum = 0.0
        var viPointsSum = 0.0

        xValues.add(handIndex)
        yValuesMi.add( miPointsSum )
        yValuesVi.add( viPointsSum )

        for (gr in gameHands){
            if (gameHands.indexOf(gr) == matchIndex) { break }
            handIndex += 1
            if (gr.matchPointsListItemFlag) {
                handIndex = 0.0
                miPointsSum = 0.0
                viPointsSum = 0.0

                xValues.clear()
                yValuesMi.clear()
                yValuesVi.clear()

                xValues.add(handIndex)
                yValuesMi.add( miPointsSum )
                yValuesVi.add( viPointsSum )
                continue
            }
            miPointsSum += (gr.getMiPointsSum().toDouble())
            viPointsSum += (gr.getViPointsSum().toDouble())
            xValues.add(handIndex)
            yValuesMi.add( miPointsSum )
            yValuesVi.add( viPointsSum )
        }
        myIntent.putExtra("xValues", xValues)
        myIntent.putExtra("yValuesMi", yValuesMi)
        myIntent.putExtra("yValuesVi", yValuesVi)

        return myIntent

    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Return to title screen?")
            .setPositiveButton("Yes") { dialog, id -> // If the user confirms, finish Activity B and go back to Activity A
                finish()
            }
            .setNegativeButton("No") { dialog, id -> // If the user cancels, dismiss the dialog and stay in Activity B
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
}