package com.example.belablok.screens.roundlist

import android.R.attr.value
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.Window
import android.widget.ListView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.belablok.R
import com.example.belablok.screens.calculator.CalculatorScreen
import com.example.belablok.screens.calculator.StatsScreen
import com.example.belablok.screens.mainmenu.MainMenu
import com.example.belablok.screens.savedMatches.SavedMatches
import com.example.belablok.storage.data_classes.GameRound
import com.example.belablok.storage.data_classes.Match
import com.example.belablok.storage.MatchStorage
import com.example.belablok.storage.data_classes.User
import com.google.gson.Gson


class RoundListScreen : ComponentActivity() {

    lateinit var listView: ListView
    lateinit var customAdapter: RoundListAdapter
    lateinit var tvMiPoints: TextView
    lateinit var tvViPoints: TextView
    lateinit var tvMiPointsToWin: TextView
    lateinit var tvViPointsToWin: TextView
    lateinit var tvPointDifference: TextView
    lateinit var tvViMatchPoints: TextView
    lateinit var tvMiMatchPoints: TextView
    lateinit var tvNG: TextView
    lateinit var playerToShuffle: User
    lateinit var currentMatch: Match

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.google.android.material.R.style.Theme_AppCompat_DayNight_DarkActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.round_list_activity)

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

        currentMatch = Gson().fromJson(intent.getStringExtra("Current match"), Match::class.java)
        playerToShuffle = currentMatch.player1!!
        if (currentMatch.gameRounds?.isNotEmpty() == true){
            tvNG.setTextColor(Color.GRAY)
        }
        else {
            tvNG.setOnClickListener {
                openCalculatorNewGame()
            }
        }

        customAdapter = RoundListAdapter(this, R.layout.round_list_item, currentMatch.gameRounds!!)

        listView.adapter = customAdapter
        listView.isClickable = true
        listView.setOnItemClickListener { adapterView, view, i, l ->
            if (currentMatch.gameRounds!![i].matchPointsListItemFlag) {
                val myIntent = setUpGraphData(i)
                resultLauncher.launch(myIntent)
                overridePendingTransition(R.anim.up_down, R.anim.nothing)
            }
            else {
                val data = Gson().toJson(currentMatch.gameRounds!![i])
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
                        if (i == currentMatch.gameRounds!!.size - 2){
                            currentMatch.gameRounds!!.filter { currentMatch.gameRounds!!.indexOf(it) != i }
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
            if (intent.getBooleanExtra("Archived", false)) {
                val intent = Intent(this, SavedMatches::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                return true
            }
            showConfirmationDialog()
            return true // Consume the event, so the default back button behavior is not triggered
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                val myIntent = setUpGraphData(currentMatch.gameRounds!!.size)
                resultLauncher.launch(myIntent)
                overridePendingTransition(R.anim.up_down, R.anim.nothing)
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
    private fun openCalculator(data: String, i: Int) {
        val intent = Intent(this, CalculatorScreen::class.java)
        intent.putExtra("gameRound", data)
        intent.putExtra("index", i.toString())
        intent.putExtra("newGameRoundFlag", "false")
        intent.putExtra("Player1", currentMatch.player1?.name)
        intent.putExtra("Player2", currentMatch.player2?.name)
        intent.putExtra("Player3", currentMatch.player3?.name)
        intent.putExtra("Player4", currentMatch.player4?.name)

        resultLauncher.launch(intent)
        overridePendingTransition(R.anim.left_right, R.anim.nothing)
    }
    private fun openCalculatorNewGame() {
        val intent = Intent(this, CalculatorScreen::class.java)
        intent.putExtra("newGameRoundFlag", "true")

        intent.putExtra("Player1", currentMatch.player1?.name)
        intent.putExtra("Player2", currentMatch.player2?.name)
        intent.putExtra("Player3", currentMatch.player3?.name)
        intent.putExtra("Player4", currentMatch.player4?.name)

        if (currentMatch.gameRounds!!.size == 0) { intent.putExtra("shuffler", currentMatch.player1?.name) }
        else {
            if (!currentMatch.gameRounds!!.last().matchPointsListItemFlag) {
                when (currentMatch.gameRounds!!.last().shuffler){
                    currentMatch.player1?.name -> intent.putExtra("shuffler", currentMatch.player2?.name)
                    currentMatch.player2?.name -> intent.putExtra("shuffler", currentMatch.player3?.name)
                    currentMatch.player3?.name -> intent.putExtra("shuffler", currentMatch.player4?.name)
                    currentMatch.player4?.name -> intent.putExtra("shuffler", currentMatch.player1?.name)
                }
            }
            else {
                if (currentMatch.gameRounds!!.last().matchWin == "Mi") {
                    when (currentMatch.gameRounds!!.elementAt(currentMatch.gameRounds!!.size - 2).shuffler){
                        currentMatch.player1?.name -> intent.putExtra("shuffler", currentMatch.player3?.name)
                        currentMatch.player2?.name -> intent.putExtra("shuffler", currentMatch.player3?.name)
                        currentMatch.player3?.name -> intent.putExtra("shuffler", currentMatch.player1?.name)
                        currentMatch.player4?.name -> intent.putExtra("shuffler", currentMatch.player1?.name)
                    }
                }
                else {
                    when (currentMatch.gameRounds!!.elementAt(currentMatch.gameRounds!!.size - 2).shuffler){
                        currentMatch.player1?.name -> intent.putExtra("shuffler", currentMatch.player2?.name)
                        currentMatch.player2?.name -> intent.putExtra("shuffler", currentMatch.player4?.name)
                        currentMatch.player3?.name -> intent.putExtra("shuffler", currentMatch.player4?.name)
                        currentMatch.player4?.name -> intent.putExtra("shuffler", currentMatch.player2?.name)
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

            val exsistingGameRound = Gson().fromJson(data?.getStringExtra("gameRound"), GameRound::class.java)
            currentMatch.gameRounds!![data?.getStringExtra("index")?.let { Integer.parseInt(it) }!!] = exsistingGameRound

            updateScoreBoard()

            customAdapter.notifyDataSetChanged()
        }
    }

    private var resultLauncherNG = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            val newGameRound = Gson().fromJson(data?.getStringExtra("gameRound"), GameRound::class.java)
            currentMatch.gameRounds!!.add(newGameRound)

            updateScoreBoard()

            customAdapter.notifyDataSetChanged()
        }
    }

    private fun updateScoreBoard(){
        var miPointsSum = 0
        var viPointsSum = 0

        var miMatchPoints = 0
        var viMatchPoints = 0

        for (gr in currentMatch.gameRounds!!){
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

        currentMatch.miMatchPoints = miMatchPoints
        currentMatch.viMatchPoints = viMatchPoints
        if ((miPointsSum >= 1001) or (viPointsSum >= 1001)) { setUpNewGame(miPointsSum) }
    }

    private fun setUpNewGame(miPointsSum: Int) {
        val matchPointListItem = GameRound()

        if (miPointsSum >= 1001){
            currentMatch.miMatchPoints += 1
            matchPointListItem.matchWin = "Mi"
        }
        else {
            currentMatch.viMatchPoints += 1
            matchPointListItem.matchWin = "Vi"
        }

        matchPointListItem.miMatchPoints = currentMatch.miMatchPoints
        matchPointListItem.viMatchPoints = currentMatch.viMatchPoints
        matchPointListItem.matchPointsListItemFlag = true

        currentMatch.gameRounds!!.add(matchPointListItem)

        tvMiPoints.text = "0"
        tvViPoints.text = "0"

        tvMiMatchPoints.text = currentMatch.miMatchPoints.toString()
        tvViMatchPoints.text = currentMatch.viMatchPoints.toString()

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

        for (gr in currentMatch.gameRounds!!){
            if (gr.matchPointsListItemFlag){
                if (currentMatch.miMatchPoints + currentMatch.viMatchPoints <= gr.miMatchPoints + gr.viMatchPoints){ continue }
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
        val myIntent = Intent(this, StatsScreen::class.java)

        val xValues = ArrayList<Double>() // runde
        val yValuesMi = ArrayList<Double>() // iznosi rundi (akumulirani)
        val yValuesVi = ArrayList<Double>()
        var handIndex = 0.0

        var miPointsSum = 0.0
        var viPointsSum = 0.0

        xValues.add(handIndex)
        yValuesMi.add( miPointsSum )
        yValuesVi.add( viPointsSum )

        for (gr in currentMatch.gameRounds!!){
            if (currentMatch.gameRounds!!.indexOf(gr) == matchIndex) { break }
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
        builder.setMessage("Save and Exit?")
            .setPositiveButton("Yes") { dialog, id ->
                currentMatch.caluclatetimePlayed(System.currentTimeMillis())
                val dataStorage = MatchStorage(applicationContext)
                val storedMatches = dataStorage.loadData()?.toMutableList()
                storedMatches?.add(currentMatch)
                dataStorage.saveData(storedMatches)

                val intent = Intent(this, MainMenu::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            .setNegativeButton("No (Don't save)") { dialog, id ->
                val intent = Intent(this, MainMenu::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            .setNeutralButton("Cancel") {dialog, id ->
                dialog.dismiss()
            }
        val dialog = builder.create()
        dialog.show()
    }
}