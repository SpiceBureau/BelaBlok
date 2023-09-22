package com.example.belablok

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson

class Calculator : AppCompatActivity() {

    private val roundPoints = 162
    private var player1Score = 0
    private var player2Score = 0
    private var gameHand = GameHand()
    private var gameRoundIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator2_activity)

        supportActionBar?.hide()

        val miScoreInput = findViewById<EditText>(R.id.etMi )
        val miScoreSumDisplay = findViewById<TextView>(R.id.etMiSum)

        val viScoreInput = findViewById<EditText>(R.id.etVi)
        val viScoreSumDisplay = findViewById<TextView>(R.id.etViSum)

        val pointDirection = findViewById<ToggleButton>(R.id.pointDirrection)

        val hercButton: ImageView = findViewById(R.id.Herc)
        val karaButton: ImageView = findViewById(R.id.Kara)
        val trefButton: ImageView = findViewById(R.id.Tref)
        val pikButton: ImageView = findViewById(R.id.Pik)

        val btn20: TextView = findViewById(R.id.btn20Calls)
        val btn50: TextView = findViewById(R.id.btn50Calls)
        val btn100: TextView = findViewById(R.id.btn100Calls)
        val btn150: Button = findViewById(R.id.btn150Calls)
        val btn200: Button = findViewById(R.id.btn200Calls)

        val ctv20MI: TextView = findViewById(R.id.ctvMI20)
        val ctv20VI: TextView = findViewById(R.id.ctvVI20)
        val ctv50MI: TextView = findViewById(R.id.ctv50MI)
        val ctv50VI: TextView = findViewById(R.id.ctv50VI)
        val ctv100MI: TextView = findViewById(R.id.ctv100MI)
        val ctv100VI: TextView = findViewById(R.id.ctv100VI)
        val btnStiljonz: Button = findViewById(R.id.btnŠtiljonž)

        val btnClear: ImageView = findViewById(R.id.btnClear)
        val btnPad: Button = findViewById(R.id.btnPad)
        val btnDone: Button = findViewById(R.id.btnDone)

        if (intent.getStringExtra("newGameRoundFlag") == "false") {
            gameHand = Gson().fromJson(intent.getStringExtra("gameRound"), GameHand::class.java)
            gameRoundIndex = intent.getStringExtra("index")?.let { Integer.parseInt(it) }!!

            miScoreInput.setText(gameHand.miPoints)
            viScoreInput.setText(gameHand.viPoints)
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)

            when (gameHand.adut) {
                "herc" -> hercButton.setImageResource(R.drawable.herc)
                "tref" -> trefButton.setImageResource(R.drawable.tref)
                "kara" -> karaButton.setImageResource(R.drawable.kara)
                "pik" -> pikButton.setImageResource(R.drawable.pik)
            }
            if (gameHand.numOf20CallsMi > 0) {
                showHide(ctv20MI, View.VISIBLE)
                ctv20MI.text = gameHand.numOf20CallsMi.toString()
            }
            if (gameHand.numOf20CallsVi > 0) {
                showHide(ctv20VI, View.VISIBLE)
                ctv20VI.text = gameHand.numOf20CallsVi.toString()
            }
            if (gameHand.numOf50CallsMi > 0) {
                showHide(ctv50MI, View.VISIBLE)
                ctv50MI.text = gameHand.numOf50CallsMi.toString()
            }
            if (gameHand.numOf50CallsVi > 0) {
                showHide(ctv50VI, View.VISIBLE)
                ctv50VI.text = gameHand.numOf50CallsVi.toString()
            }
            if (gameHand.numOf100CallsMi > 0) {
                showHide(ctv100MI, View.VISIBLE)
                ctv100MI.text = gameHand.numOf100CallsMi.toString()
            }
            if (gameHand.numOf100CallsVi > 0) {
                showHide(ctv100VI, View.VISIBLE)
                ctv100VI.text = gameHand.numOf100CallsVi.toString()
            }
            if (gameHand.numOf150CallsMi == 1) { btn150.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.numOf150CallsVi == 1) { btn150.setTextColor(getColor(R.color.viColor)) }
            if (gameHand.numOf200CallsMi == 1) { btn200.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.numOf200CallsVi == 1) { btn200.setTextColor(getColor(R.color.viColor)) }
            if (gameHand.stiljaMi == 1) { btnStiljonz.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.stiljaVi == 1) { btnStiljonz.setTextColor(getColor(R.color.viColor)) }
        }

        miScoreInput.addTextChangedListener {
            if (currentFocus != miScoreInput) { return@addTextChangedListener }
            val player1Input = miScoreInput.text.toString().toIntOrNull() ?: return@addTextChangedListener

            player1Score = player1Input
            val difference = roundPoints - player1Score
            if (difference >= 0) player2Score = difference

            if (player2Score == 0) viScoreInput.setText("")
            else viScoreInput.setText(player2Score.toString())

            gameHand.miPoints = player1Score.toString()
            gameHand.viPoints = player2Score.toString()

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        viScoreInput.addTextChangedListener {
            if (currentFocus != viScoreInput) { return@addTextChangedListener }
            val player2Input = viScoreInput.text.toString().toIntOrNull() ?: return@addTextChangedListener

            player2Score = player2Input
            val difference = roundPoints - player2Score
            if (difference >= 0) player1Score = difference

            if (player1Score == 0) miScoreInput.setText("")
            else miScoreInput.setText(player1Score.toString())

            miScoreInput.setText(player1Score.toString())
            gameHand.miPoints = player1Score.toString()
            gameHand.viPoints = player2Score.toString()

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }

        miScoreSumDisplay.setOnClickListener {
            openMiEditText(miScoreInput)
        }
        viScoreSumDisplay.setOnClickListener {
            openViEditText(viScoreInput)
        }


        hercButton.setOnClickListener{
            if (gameHand.adut == "herc"){
                hercButton.setImageResource(R.drawable.herc_white)
                gameHand.adut = "null"
            }
            else {
                hercButton.setImageResource(R.drawable.herc)
                gameHand.adut = "herc"

                trefButton.setImageResource(R.drawable.tref_white)
                pikButton.setImageResource(R.drawable.pik_white)
                karaButton.setImageResource(R.drawable.kara_white)
            }
        }
        karaButton.setOnClickListener{
            if (gameHand.adut == "kara"){
                karaButton.setImageResource(R.drawable.kara_white)
                gameHand.adut = "null"
            }
            else{
                karaButton.setImageResource(R.drawable.kara)
                gameHand.adut = "kara"

                trefButton.setImageResource(R.drawable.tref_white)
                pikButton.setImageResource(R.drawable.pik_white)
                hercButton.setImageResource(R.drawable.herc_white)
            }
        }
        pikButton.setOnClickListener {
            if (gameHand.adut == "pik"){
                pikButton.setImageResource(R.drawable.pik_white)
                gameHand.adut = "null"
            }
            else{
                pikButton.setImageResource(R.drawable.pik)
                gameHand.adut = "pik"

                trefButton.setImageResource(R.drawable.tref_white)
                karaButton.setImageResource(R.drawable.kara_white)
                hercButton.setImageResource(R.drawable.herc_white)
            }
        }
        trefButton.setOnClickListener {
            if (gameHand.adut == "tref"){
                trefButton.setImageResource(R.drawable.tref_white)
                gameHand.adut = "null"
            }
            else{
                trefButton.setImageResource(R.drawable.tref)
                gameHand.adut = "tref"

                hercButton.setImageResource(R.drawable.herc_white)
                karaButton.setImageResource(R.drawable.kara_white)
                pikButton.setImageResource(R.drawable.pik_white)
            }
        }

        btn20.setOnClickListener {
            if (pointDirection.isChecked){
                if (gameHand.numOf20CallsVi == 0){
                    showHide(ctv20VI, View.VISIBLE)
                }
                gameHand.numOf20CallsVi += 1
                ctv20VI.text = gameHand.numOf20CallsVi.toString()
            }
            else{
                if (gameHand.numOf20CallsMi == 0){
                    showHide(ctv20MI, View.VISIBLE)
                }
                gameHand.numOf20CallsMi += 1
                ctv20MI.text = gameHand.numOf20CallsMi.toString()
            }

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btn20.setOnLongClickListener {
            if (pointDirection.isChecked){
                if (gameHand.numOf20CallsVi > 0) { gameHand.numOf20CallsVi -= 1 }
                ctv20VI.text = gameHand.numOf20CallsVi.toString()

                if (gameHand.numOf20CallsVi == 0){
                    showHide(ctv20VI, View.GONE)
                }
            }
            else{
                if (gameHand.numOf20CallsMi > 0) { gameHand.numOf20CallsMi -= 1 }
                ctv20MI.text = gameHand.numOf20CallsMi.toString()

                if (gameHand.numOf20CallsMi == 0){
                    showHide(ctv20MI, View.GONE)
                }
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btn50.setOnClickListener {
            if (pointDirection.isChecked){
                if (gameHand.numOf50CallsVi == 0){
                    showHide(ctv50VI, View.VISIBLE)
                }
                gameHand.numOf50CallsVi += 1
                ctv50VI.text = gameHand.numOf50CallsVi.toString()
            }
            else{
                if (gameHand.numOf50CallsMi == 0){
                    showHide(ctv50MI, View.VISIBLE)
                }
                gameHand.numOf50CallsMi += 1
                ctv50MI.text = gameHand.numOf50CallsMi.toString()
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btn50.setOnLongClickListener {
            if (pointDirection.isChecked) {
                if (gameHand.numOf50CallsVi > 0) {
                    gameHand.numOf50CallsVi -= 1
                }
                ctv50VI.text = gameHand.numOf50CallsVi.toString()

                if (gameHand.numOf50CallsVi == 0) {
                    showHide(ctv50VI, View.GONE)
                }
            } else {
                if (gameHand.numOf50CallsMi > 0) {
                    gameHand.numOf50CallsMi -= 1
                }
                ctv50MI.text = gameHand.numOf50CallsMi.toString()

                if (gameHand.numOf50CallsMi == 0) {
                    showHide(ctv50MI, View.GONE)
                }
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btn100.setOnClickListener {
            if (pointDirection.isChecked){
                if (gameHand.numOf100CallsVi == 0){
                    showHide(ctv100VI, View.VISIBLE)
                }
                gameHand.numOf100CallsVi += 1
                ctv100VI.text = gameHand.numOf100CallsVi.toString()
            }
            else{
                if (gameHand.numOf100CallsMi == 0){
                    showHide(ctv100MI, View.VISIBLE)
                }
                gameHand.numOf100CallsMi += 1
                ctv100MI.text = gameHand.numOf100CallsMi.toString()
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btn100.setOnLongClickListener {
            if (pointDirection.isChecked) {
                if (gameHand.numOf100CallsVi > 0) {
                    gameHand.numOf100CallsVi -= 1
                }
                ctv100VI.text = gameHand.numOf100CallsVi.toString()

                if (gameHand.numOf100CallsVi == 0) {
                    showHide(ctv100VI, View.GONE)
                }
            } else {
                if (gameHand.numOf100CallsMi > 0) {
                    gameHand.numOf100CallsMi -= 1
                }
                ctv100MI.text = gameHand.numOf100CallsMi.toString()

                if (gameHand.numOf100CallsMi == 0) {
                    showHide(ctv100MI, View.GONE)
                }
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btn150.setOnClickListener {
            if (pointDirection.isChecked){
                gameHand.numOf150CallsMi = 0
                gameHand.numOf150CallsVi = 1
                btn150.setTextColor(getColor(R.color.viColor))
            }
            else{
                gameHand.numOf150CallsMi = 1
                gameHand.numOf150CallsVi = 0
                btn150.setTextColor(getColor(R.color.miColor))
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btn150.setOnLongClickListener {
            gameHand.numOf150CallsMi = 0
            gameHand.numOf150CallsVi = 0
            btn150.setTextColor(getColor(R.color.white))
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btn200.setOnClickListener {
            if (pointDirection.isChecked){
                gameHand.numOf200CallsMi = 0
                gameHand.numOf200CallsVi = 1
                btn200.setTextColor(getColor(R.color.viColor))
            }
            else{
                gameHand.numOf200CallsMi = 1
                gameHand.numOf200CallsVi = 0
                btn200.setTextColor(getColor(R.color.miColor))
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btn200.setOnLongClickListener {
            gameHand.numOf200CallsMi = 0
            gameHand.numOf200CallsVi = 0
            btn200.setTextColor(getColor(R.color.white))

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btnStiljonz.setOnClickListener {
            if (pointDirection.isChecked){
                gameHand.stiljaMi = 0
                gameHand.miPoints = "0"
                miScoreInput.setText("")

                gameHand.stiljaVi = 1
                gameHand.viPoints = "162"
                viScoreInput.setText("162")
                btnStiljonz.setTextColor(getColor(R.color.viColor))
            }
            else{
                gameHand.stiljaMi = 1
                gameHand.miPoints = "162"
                miScoreInput.setText("162")

                gameHand.stiljaVi = 0
                gameHand.viPoints = "0"
                viScoreInput.setText("0")
                btnStiljonz.setTextColor(getColor(R.color.miColor))
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }
        btnStiljonz.setOnLongClickListener {
            gameHand.stiljaMi = 0
            gameHand.stiljaVi = 0
            btnStiljonz.setTextColor(getColor(R.color.white))

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
            true
        }

        btnClear.setOnClickListener {
            hercButton.setImageResource(R.drawable.herc_white)
            trefButton.setImageResource(R.drawable.tref_white)
            pikButton.setImageResource(R.drawable.pik_white)
            karaButton.setImageResource(R.drawable.kara_white)

            miScoreInput.setText("")
            viScoreInput.setText("")

            gameHand = GameHand()

            showHide(ctv20MI, View.GONE)
            showHide(ctv20VI, View.GONE)

            showHide(ctv50MI, View.GONE)
            showHide(ctv50VI, View.GONE)

            showHide(ctv100MI, View.GONE)
            showHide(ctv100VI, View.GONE)

            btn150.setTextColor(getColor(R.color.white))
            btn200.setTextColor(getColor(R.color.white))

            btnStiljonz.setTextColor(getColor(R.color.white))

            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }

        btnPad.setOnClickListener {
            if (pointDirection.isChecked){
                gameHand.miPoints = "162"
                gameHand.padVi = 1
                gameHand.padMi = 0
                miScoreInput.setText("162")

                gameHand.viPoints = "0"
                viScoreInput.setText("")
            }
            else {
                gameHand.miPoints = "0"
                gameHand.padMi = 1
                gameHand.padVi = 0
                miScoreInput.setText("")

                gameHand.viPoints = "162"
                viScoreInput.setText("162")
            }
            updateDisplay(miScoreSumDisplay, viScoreSumDisplay)
        }

        btnDone.setOnClickListener {
            val intent = Intent()
            val data = Gson().toJson(gameHand)
            intent.putExtra("gameRound", data)
            intent.putExtra("index", gameRoundIndex.toString())
            setResult(RESULT_OK, intent)
            finish()
            overridePendingTransition(R.anim.nothing, R.anim.left_rigt_exit)
        }
    }
    private fun showHide(view: View, visibility: Int) {
        view.visibility = if (visibility == View.GONE) View.GONE else View.VISIBLE
    }
    private fun updateDisplay(miScoreDisplay: TextView, viScoreDisplay: TextView) {
        miScoreDisplay.text = gameHand.getMiPointsSum().toString()
        viScoreDisplay.text = gameHand.getViPointsSum().toString()
    }
    private fun openMiEditText(miScoreInput: EditText) {
        miScoreInput.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(miScoreInput, InputMethodManager.SHOW_IMPLICIT)
    }
    private fun openViEditText(viScoreInput: EditText) {
        viScoreInput.requestFocus()
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(viScoreInput, InputMethodManager.SHOW_IMPLICIT)
    }

}