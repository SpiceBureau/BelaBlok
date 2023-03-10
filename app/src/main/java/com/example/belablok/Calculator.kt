package com.example.belablok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_activity)

        supportActionBar?.hide()

        var gameRound = GameRound()
        val newGameFlag = intent.getStringExtra("newGameRound")?.let { checkIfNewGame(it) }

        var roundIndex = "-1"
        if (!newGameFlag!!){
            roundIndex = intent.getStringExtra("index").toString()
            gameRound = Gson().fromJson(intent.getStringExtra("gameRound"), GameRound::class.java)
        }

        val txtMi: TextView = findViewById(R.id.txtMi)
        val txtVi: TextView = findViewById(R.id.txtVi)
        val ctv20MI: TextView = findViewById(R.id.ctvMI20)
        val ctv20VI: TextView = findViewById(R.id.ctvVI20)
        val ctv50MI: TextView = findViewById(R.id.ctv50MI)
        val ctv50VI: TextView = findViewById(R.id.ctv50VI)
        val ctv100MI: TextView = findViewById(R.id.ctv100MI)
        val ctv100VI: TextView = findViewById(R.id.ctv100VI)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btn0: Button = findViewById(R.id.btn0)
        val btnPad: Button = findViewById(R.id.btnPad)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnReturn: Button = findViewById(R.id.btnBack)
        val btnDone: Button = findViewById(R.id.btnDone)
        val btn20: TextView = findViewById(R.id.btn20Calls)
        val btn50: TextView = findViewById(R.id.btn50Calls)
        val btn100: TextView = findViewById(R.id.btn100Calls)
        val btn150: Button = findViewById(R.id.btn150Calls)
        val btn200: Button = findViewById(R.id.btn200Calls)
        val btnStiljonz: Button = findViewById(R.id.btnŠtiljonž)
        val hercButton: ImageView = findViewById(R.id.Herc)
        val karaButton: ImageView = findViewById(R.id.Kara)
        val trefButton: ImageView = findViewById(R.id.Tref)
        val pikButton: ImageView = findViewById(R.id.Pik)

        var pointsDirectionFlag = false // 0 = Mi || 1 = Vi

        if (!newGameFlag){
            updatecalculator(txtMi, txtVi, gameRound)

            ctv20MI.text = gameRound.numOf20CallsMi.toString()
            if (gameRound.numOf20CallsMi != 0){ showHide(ctv20MI) }
            ctv20VI.text = gameRound.numOf20CallsVi.toString()
            if (gameRound.numOf20CallsVi != 0){ showHide(ctv20VI) }

            ctv50MI.text = gameRound.numOf50CallsMi.toString()
            if (gameRound.numOf50CallsMi != 0){ showHide(ctv50MI) }
            ctv50VI.text = gameRound.numOf50CallsVi.toString()
            if (gameRound.numOf50CallsVi != 0){ showHide(ctv50VI) }

            ctv100MI.text = gameRound.numOf100CallsMi.toString()
            if (gameRound.numOf100CallsMi != 0){ showHide(ctv100MI) }
            ctv100VI.text = gameRound.numOf100CallsVi.toString()
            if (gameRound.numOf100CallsVi != 0){ showHide(ctv100VI) }

            if (gameRound.numOf150CallsMi == 1){ btn150.setTextColor(getColor(R.color.miColor)) }
            if (gameRound.numOf150CallsVi == 1){ btn150.setTextColor(getColor(R.color.viColor)) }

            if (gameRound.numOf200CallsMi == 1){ btn200.setTextColor(getColor(R.color.miColor)) }
            if (gameRound.numOf200CallsVi == 1){ btn200.setTextColor(getColor(R.color.viColor)) }

            if (gameRound.stiljaMi == 1){ btnStiljonz.setTextColor(getColor(R.color.miColor)) }
            if (gameRound.stiljaVi == 1){ btnStiljonz.setTextColor(getColor(R.color.viColor)) }

            if(gameRound.adut == "herc"){ hercButton.setImageResource(R.drawable.herc) }
            if(gameRound.adut == "tref"){ trefButton.setImageResource(R.drawable.tref) }
            if(gameRound.adut == "kara"){ karaButton.setImageResource(R.drawable.kara) }
            if(gameRound.adut == "pik"){ pikButton.setImageResource(R.drawable.pik) }
        }


        txtMi.setOnClickListener{
            pointsDirectionFlag = false
            txtMi.setTextColor(getColor(R.color.miColor))
            txtVi.setTextColor(Color.WHITE)
        }
        txtVi.setOnClickListener{
            pointsDirectionFlag = true
            txtVi.setTextColor(getColor(R.color.viColor))
            txtMi.setTextColor(Color.WHITE)
        }

        hercButton.setOnClickListener{
            if (gameRound.adut == "herc"){
                hercButton.setImageResource(R.drawable.herc_white)
                gameRound.adut = "null"
            }
            else {
                hercButton.setImageResource(R.drawable.herc)
                gameRound.adut = "herc"

                trefButton.setImageResource(R.drawable.tref_white)
                pikButton.setImageResource(R.drawable.pik_white)
                karaButton.setImageResource(R.drawable.kara_white)
            }
        }
        karaButton.setOnClickListener{
            if (gameRound.adut == "kara"){
                karaButton.setImageResource(R.drawable.kara_white)
                gameRound.adut = "null"
            }
            else{
                karaButton.setImageResource(R.drawable.kara)
                gameRound.adut = "kara"

                trefButton.setImageResource(R.drawable.tref_white)
                pikButton.setImageResource(R.drawable.pik_white)
                hercButton.setImageResource(R.drawable.herc_white)
            }
        }
        pikButton.setOnClickListener {
            if (gameRound.adut == "pik"){
                pikButton.setImageResource(R.drawable.pik_white)
                gameRound.adut = "null"
            }
            else{
                pikButton.setImageResource(R.drawable.pik)
                gameRound.adut = "pik"

                trefButton.setImageResource(R.drawable.tref_white)
                karaButton.setImageResource(R.drawable.kara_white)
                hercButton.setImageResource(R.drawable.herc_white)
            }
        }
        trefButton.setOnClickListener {
            if (gameRound.adut == "tref"){
                trefButton.setImageResource(R.drawable.tref_white)
                gameRound.adut = "null"
            }
            else{
                trefButton.setImageResource(R.drawable.tref)
                gameRound.adut = "tref"

                hercButton.setImageResource(R.drawable.herc_white)
                karaButton.setImageResource(R.drawable.kara_white)
                pikButton.setImageResource(R.drawable.pik_white)
            }
        }
        btn0.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "0" }
                else { gameRound.miPoints += "0" }
                
                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "0" }
                else { gameRound.viPoints += "0" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn1.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "1" }
                else { gameRound.miPoints += "1" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "1" }
                else { gameRound.viPoints += "1" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn2.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "2" }
                else { gameRound.miPoints += "2" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "2" }
                else { gameRound.viPoints += "2" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn3.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "3" }
                else { gameRound.miPoints += "3" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "3" }
                else { gameRound.viPoints += "3" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn4.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "4" }
                else { gameRound.miPoints += "4" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "4" }
                else { gameRound.viPoints += "4" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn5.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "5" }
                else { gameRound.miPoints += "5" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "5" }
                else { gameRound.viPoints += "5" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn6.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "6" }
                else { gameRound.miPoints += "6" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") {
                    gameRound.viPoints = "6"
                } else {
                    gameRound.viPoints += "6"

                    val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                    gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
                }
            }
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn7.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "7" }
                else { gameRound.miPoints += "7" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "7" }
                else { gameRound.viPoints += "7" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn8.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "8" }
                else { gameRound.miPoints += "8" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "8" }
                else { gameRound.viPoints += "8" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn9.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameRound.miPoints == "0") { gameRound.miPoints = "9" }
                else { gameRound.miPoints += "9" }

                val pointDiff = (162 - Integer.parseInt(gameRound.miPoints))
                gameRound.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameRound.viPoints == "0") { gameRound.viPoints = "9" }
                else { gameRound.viPoints += "9" }

                val pointDiff = (162 - Integer.parseInt(gameRound.viPoints))
                gameRound.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }

        btnPad.setOnClickListener {
            if (pointsDirectionFlag){
                gameRound.padVi = 1
                gameRound.miPoints = "162"
                gameRound.viPoints = "0"
            }
            else{
                gameRound.padMi = 1
                gameRound.miPoints = "0"
                gameRound.viPoints = "162"
            }
            
            updatecalculator(txtMi, txtVi, gameRound)
        }
        btnClear.setOnClickListener {
            btnStiljonz.setTextColor(Color.WHITE)
            btn150.setTextColor(Color.WHITE)
            btn200.setTextColor(Color.WHITE)
            hercButton.setImageResource(R.drawable.herc_white)
            trefButton.setImageResource(R.drawable.tref_white)
            pikButton.setImageResource(R.drawable.pik_white)
            karaButton.setImageResource(R.drawable.kara_white)
            gameRound.numOf20CallsMi = 0
            gameRound.numOf20CallsVi = 0
            gameRound.numOf50CallsMi = 0
            gameRound.numOf50CallsVi = 0
            gameRound.numOf100CallsMi = 0
            gameRound.numOf100CallsVi = 0
            gameRound.numOf150CallsMi = 0
            gameRound.numOf150CallsVi = 0
            gameRound.numOf200CallsMi = 0
            gameRound.numOf200CallsVi = 0
            gameRound.stiljaMi = 0
            gameRound.stiljaVi = 0
            gameRound.padMi = 0
            gameRound.padVi = 0
            gameRound.miPoints = "0"
            gameRound.viPoints = "0"
            if (ctv20MI.visibility == View.VISIBLE){showHide(ctv20MI)}
            if (ctv20VI.visibility == View.VISIBLE){showHide(ctv20VI)}
            if (ctv50MI.visibility == View.VISIBLE){showHide(ctv50MI)}
            if (ctv50VI.visibility == View.VISIBLE){showHide(ctv50VI)}
            if (ctv100MI.visibility == View.VISIBLE){showHide(ctv100MI)}
            if (ctv100VI.visibility == View.VISIBLE){showHide(ctv100VI)}
            
            updatecalculator(txtMi, txtVi, gameRound)
        }

        btnStiljonz.setOnClickListener {
            val (newMiPoints, newViPoints) = pad(pointsDirectionFlag)
            gameRound.viPoints = newViPoints.toString()
            gameRound.miPoints = newMiPoints.toString()

            if (pointsDirectionFlag){
                gameRound.stiljaMi = 0
                gameRound.stiljaVi = 1
                btnStiljonz.setTextColor(getColor(R.color.viColor))
            }
            else{
                gameRound.stiljaMi = 1
                gameRound.stiljaVi = 0
                btnStiljonz.setTextColor(getColor(R.color.miColor))
            }

           updatecalculator(txtMi, txtVi, gameRound)
        }
        btn20.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameRound.numOf20CallsVi == 0){
                    showHide(ctv20VI)
                }
                gameRound.numOf20CallsVi += 1
                ctv20VI.text = gameRound.numOf20CallsVi.toString()
            }
            else{ // MI
                if (gameRound.numOf20CallsMi == 0){
                    showHide(ctv20MI)
                }
                gameRound.numOf20CallsMi += 1
                ctv20MI.text = gameRound.numOf20CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn50.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameRound.numOf50CallsVi == 0){
                    showHide(ctv50VI)
                }
                gameRound.numOf50CallsVi += 1
                ctv50VI.text = gameRound.numOf50CallsVi.toString()
            }
            else{ // MI
                if (gameRound.numOf50CallsMi == 0){
                    showHide(ctv50MI)
                }
                gameRound.numOf50CallsMi += 1
                ctv50MI.text = gameRound.numOf50CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn100.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameRound.numOf100CallsVi == 0){
                    showHide(ctv100VI)
                }
                gameRound.numOf100CallsVi += 1
                ctv100VI.text = gameRound.numOf100CallsVi.toString()
            }
            else{ // MI
                if (gameRound.numOf100CallsMi == 0){
                    showHide(ctv100MI)
                }
                gameRound.numOf100CallsMi += 1
                ctv100MI.text = gameRound.numOf100CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn150.setOnClickListener {
            if (pointsDirectionFlag){
                gameRound.numOf150CallsMi = 0
                gameRound.numOf150CallsVi = 1
                btn150.setTextColor(getColor(R.color.viColor))
            }
            else {
                gameRound.numOf150CallsMi = 1
                gameRound.numOf150CallsVi = 0
                btn150.setTextColor(getColor(R.color.miColor))
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }
        btn200.setOnClickListener {
            if (pointsDirectionFlag){
                gameRound.numOf200CallsMi = 0
                gameRound.numOf200CallsVi = 1
                btn200.setTextColor(getColor(R.color.viColor))
            }
            else {
                gameRound.numOf200CallsMi = 1
                gameRound.numOf200CallsVi = 0
                btn200.setTextColor(getColor(R.color.miColor))
            }

            updatecalculator(txtMi, txtVi, gameRound)
        }

        btnDone.setOnClickListener {
            val intent = Intent()
            val data = Gson().toJson(gameRound)
            intent.putExtra("gameRound", data)
            intent.putExtra("index", roundIndex)
            setResult(RESULT_OK, intent)
            finish()
            overridePendingTransition(R.anim.nothing, R.anim.left_rigt_exit)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransition(R.anim.nothing, R.anim.right_left)
            }
        })
    }

    private fun pad(pointsDirectionFlag: Boolean): Pair<Int, Int> {
        return if (pointsDirectionFlag){
            Pair(0, 162)
        } else{
            Pair(162, 0)
        }
    }
        
    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){View.GONE} else{View.VISIBLE}
    }

    private fun checkIfNewGame(flag: String): Boolean {
        return flag == "true"
    }

    private fun updatecalculator(txtMi: TextView, txtVi: TextView, gameRound: GameRound){
        txtMi.text = gameRound.getMiPointsSum().toString()
        txtVi.text = gameRound.getViPointsSum().toString()
    }

    private fun checkPad(gameRound: GameRound){
        TODO()
    }
}