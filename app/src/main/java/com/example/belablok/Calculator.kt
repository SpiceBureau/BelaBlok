package com.example.belablok

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        val viScoreInput = findViewById<EditText>(R.id.etVi)

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
        val btnPad: ImageView = findViewById(R.id.btnPad)
        val btnDone: Button = findViewById(R.id.btnDone)

        val player1ShuffleImageView: ImageView = findViewById(R.id.player1ShuffleImg)
        val player2ShuffleImageView: ImageView = findViewById(R.id.player2ShuffleImg)
        val player3ShuffleImageView: ImageView = findViewById(R.id.player3ShuffleImg)
        val player4ShuffleImageView: ImageView = findViewById(R.id.player4ShuffleImg)

        val player1Name: TextView = findViewById(R.id.player1)
        val player2Name: TextView = findViewById(R.id.player2)
        val player3Name: TextView = findViewById(R.id.player3)
        val player4Name: TextView = findViewById(R.id.player4)

        if (intent.getStringExtra("newGameRoundFlag") == "false") {
            gameHand = Gson().fromJson(intent.getStringExtra("gameRound"), GameHand::class.java)
            gameRoundIndex = intent.getStringExtra("index")?.let { Integer.parseInt(it) }!!

            miScoreInput.setText(gameHand.miPoints)
            viScoreInput.setText(gameHand.viPoints)
            updateDisplay(miScoreInput, viScoreInput)

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

        player1Name.text = intent.getStringExtra("Player1")
        player2Name.text = intent.getStringExtra("Player2")
        player3Name.text = intent.getStringExtra("Player3")
        player4Name.text = intent.getStringExtra("Player4")

        gameHand.shuffler = intent.getStringExtra("shuffler").toString()

        when (gameHand.shuffler){
            player1Name.text -> player1ShuffleImageView.setImageResource(R.drawable.cards_shufflemvmv)
            player2Name.text -> player2ShuffleImageView.setImageResource(R.drawable.cards_shufflevmvm)
            player3Name.text -> player3ShuffleImageView.setImageResource(R.drawable.cards_shufflemvmv)
            player4Name.text -> player4ShuffleImageView.setImageResource(R.drawable.cards_shufflevmvm)
        }

        val miScoreTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (currentFocus != miScoreInput) { return }
                val player1Input = miScoreInput.text.toString().toIntOrNull() ?: return

                player1Score = player1Input
                val difference = roundPoints - player1Score
                player2Score = if (difference >= 0) difference
                else 0

                if (player2Score == 0) viScoreInput.setText("")
                else viScoreInput.setText(player2Score.toString())

                gameHand.miPoints = player1Score.toString()
                gameHand.viPoints = player2Score.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        val viScoreTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.v("wtf", s.toString())
                if (currentFocus != viScoreInput) { return }
                val player2Input = viScoreInput.text.toString().toIntOrNull() ?: return

                player2Score = player2Input
                val difference = roundPoints - player2Score
                player1Score = if (difference >= 0) difference
                else 0

                if (player1Score == 0) miScoreInput.setText("")
                else miScoreInput.setText(player1Score.toString())

                gameHand.miPoints = player1Score.toString()
                gameHand.viPoints = player2Score.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        miScoreInput.addTextChangedListener(miScoreTextWatcher)
        viScoreInput.addTextChangedListener(viScoreTextWatcher)

        miScoreInput.setOnClickListener {
            openMiEditText(miScoreInput)
        }
        viScoreInput.setOnClickListener {
            openMiEditText(viScoreInput)
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

            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
        }
        btn150.setOnLongClickListener {
            gameHand.numOf150CallsMi = 0
            gameHand.numOf150CallsVi = 0
            btn150.setTextColor(getColor(R.color.white))
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
        }
        btn200.setOnLongClickListener {
            gameHand.numOf200CallsMi = 0
            gameHand.numOf200CallsVi = 0
            btn200.setTextColor(getColor(R.color.white))

            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
        }
        btnStiljonz.setOnLongClickListener {
            gameHand.stiljaMi = 0
            gameHand.stiljaVi = 0
            btnStiljonz.setTextColor(getColor(R.color.white))

            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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
            btnPad.setImageResource(R.drawable.falling_downw)
        }

        btnPad.setOnClickListener {
            if (pointDirection.isChecked){
                gameHand.miPoints = "162"
                gameHand.padVi = 1
                gameHand.padMi = 0
                miScoreInput.setText("162")

                gameHand.viPoints = "0"
                viScoreInput.setText("")

                btnPad.setImageResource(R.drawable.falling_downvi)
            }
            else {
                gameHand.miPoints = "0"
                gameHand.padMi = 1
                gameHand.padVi = 0
                miScoreInput.setText("")

                gameHand.viPoints = "162"
                viScoreInput.setText("162")
                btnPad.setImageResource(R.drawable.falling_downmi)
            }
            miScoreInput.removeTextChangedListener(miScoreTextWatcher)
            viScoreInput.removeTextChangedListener(viScoreTextWatcher)
            updateDisplay(miScoreInput, viScoreInput)
            miScoreInput.addTextChangedListener(miScoreTextWatcher)
            viScoreInput.addTextChangedListener(viScoreTextWatcher)
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

        player1Name.setOnClickListener {
            if (gameHand.caller != player1Name.text.toString()) {
                gameHand.caller = player1Name.text.toString()
                player1Name.setBackgroundResource(R.drawable.rounded_text_mi)
            }
            else {
                gameHand.caller = ""
                player1Name.setBackgroundResource(R.drawable.rounded_text_neutral)
            }
        }

        player2Name.setOnClickListener {
            if (gameHand.caller != player2Name.text.toString()) {
                gameHand.caller = player2Name.text.toString()
                player2Name.setBackgroundResource(R.drawable.rounded_text_vi)
            }
            else {
                gameHand.caller = ""
                player2Name.setBackgroundResource(R.drawable.rounded_text_neutral)
            }
        }

        player3Name.setOnClickListener {
            if (gameHand.caller != player3Name.text.toString()) {
                gameHand.caller = player3Name.text.toString()
                player3Name.setBackgroundResource(R.drawable.rounded_text_mi)
            }
            else {
                gameHand.caller = ""
                player3Name.setBackgroundResource(R.drawable.rounded_text_neutral)
            }
        }

        player4Name.setOnClickListener {
            if (gameHand.caller != player4Name.text.toString()) {
                gameHand.caller = player4Name.text.toString()
                player4Name.setBackgroundResource(R.drawable.rounded_text_vi)
            }
            else {
                gameHand.caller = ""
                player4Name.setBackgroundResource(R.drawable.rounded_text_neutral)
            }
        }
    }
    private fun showHide(view: View, visibility: Int) {
        view.visibility = if (visibility == View.GONE) View.GONE else View.VISIBLE
    }
    private fun updateDisplay(miScoreDisplay: EditText, viScoreDisplay: EditText) {
        miScoreDisplay.setText(gameHand.getMiPointsSum().toString())
        viScoreDisplay.setText(gameHand.getViPointsSum().toString())
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