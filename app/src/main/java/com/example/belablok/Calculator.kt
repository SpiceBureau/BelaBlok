package com.example.belablok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_activity)

        supportActionBar?.hide()

        var gameHand = GameHand()
        val newGameFlag = intent.getStringExtra("newGameRound")?.let { checkIfNewGame(it) }

        var roundIndex = "-1"
        if (!newGameFlag!!){
            roundIndex = intent.getStringExtra("index").toString()
            gameHand = Gson().fromJson(intent.getStringExtra("gameRound"), GameHand::class.java)
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
            updatecalculator(txtMi, txtVi, gameHand)

            ctv20MI.text = gameHand.numOf20CallsMi.toString()
            if (gameHand.numOf20CallsMi != 0){ showHide(ctv20MI) }
            ctv20VI.text = gameHand.numOf20CallsVi.toString()
            if (gameHand.numOf20CallsVi != 0){ showHide(ctv20VI) }

            ctv50MI.text = gameHand.numOf50CallsMi.toString()
            if (gameHand.numOf50CallsMi != 0){ showHide(ctv50MI) }
            ctv50VI.text = gameHand.numOf50CallsVi.toString()
            if (gameHand.numOf50CallsVi != 0){ showHide(ctv50VI) }

            ctv100MI.text = gameHand.numOf100CallsMi.toString()
            if (gameHand.numOf100CallsMi != 0){ showHide(ctv100MI) }
            ctv100VI.text = gameHand.numOf100CallsVi.toString()
            if (gameHand.numOf100CallsVi != 0){ showHide(ctv100VI) }

            if (gameHand.numOf150CallsMi == 1){ btn150.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.numOf150CallsVi == 1){ btn150.setTextColor(getColor(R.color.viColor)) }

            if (gameHand.numOf200CallsMi == 1){ btn200.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.numOf200CallsVi == 1){ btn200.setTextColor(getColor(R.color.viColor)) }

            if (gameHand.stiljaMi == 1){ btnStiljonz.setTextColor(getColor(R.color.miColor)) }
            if (gameHand.stiljaVi == 1){ btnStiljonz.setTextColor(getColor(R.color.viColor)) }

            if(gameHand.adut == "herc"){ hercButton.setImageResource(R.drawable.herc) }
            if(gameHand.adut == "tref"){ trefButton.setImageResource(R.drawable.tref) }
            if(gameHand.adut == "kara"){ karaButton.setImageResource(R.drawable.kara) }
            if(gameHand.adut == "pik"){ pikButton.setImageResource(R.drawable.pik) }
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
        btn0.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "0" }
                else { gameHand.miPoints += "0" }
                
                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "0" }
                else { gameHand.viPoints += "0" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn1.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "1" }
                else { gameHand.miPoints += "1" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "1" }
                else { gameHand.viPoints += "1" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn2.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "2" }
                else { gameHand.miPoints += "2" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "2" }
                else { gameHand.viPoints += "2" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn3.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "3" }
                else { gameHand.miPoints += "3" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "3" }
                else { gameHand.viPoints += "3" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn4.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "4" }
                else { gameHand.miPoints += "4" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "4" }
                else { gameHand.viPoints += "4" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn5.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "5" }
                else { gameHand.miPoints += "5" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "5" }
                else { gameHand.viPoints += "5" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn6.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "6" }
                else { gameHand.miPoints += "6" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") {
                    gameHand.viPoints = "6"
                } else {
                    gameHand.viPoints += "6"

                    val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                    gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
                }
            }
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn7.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "7" }
                else { gameHand.miPoints += "7" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "7" }
                else { gameHand.viPoints += "7" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn8.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "8" }
                else { gameHand.miPoints += "8" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "8" }
                else { gameHand.viPoints += "8" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn9.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (gameHand.miPoints == "0") { gameHand.miPoints = "9" }
                else { gameHand.miPoints += "9" }

                val pointDiff = (162 - Integer.parseInt(gameHand.miPoints))
                gameHand.viPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }
            else {
                if (gameHand.viPoints == "0") { gameHand.viPoints = "9" }
                else { gameHand.viPoints += "9" }

                val pointDiff = (162 - Integer.parseInt(gameHand.viPoints))
                gameHand.miPoints = if (pointDiff < 0) "0" else pointDiff.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }

        btnPad.setOnClickListener {
            if (pointsDirectionFlag){
                gameHand.padVi = 1
                gameHand.miPoints = "162"
                gameHand.viPoints = "0"
            }
            else{
                gameHand.padMi = 1
                gameHand.miPoints = "0"
                gameHand.viPoints = "162"
            }
            
            updatecalculator(txtMi, txtVi, gameHand)
        }
        btnClear.setOnClickListener {
            btnStiljonz.setTextColor(Color.WHITE)
            btn150.setTextColor(Color.WHITE)
            btn200.setTextColor(Color.WHITE)
            hercButton.setImageResource(R.drawable.herc_white)
            trefButton.setImageResource(R.drawable.tref_white)
            pikButton.setImageResource(R.drawable.pik_white)
            karaButton.setImageResource(R.drawable.kara_white)
            gameHand.numOf20CallsMi = 0
            gameHand.numOf20CallsVi = 0
            gameHand.numOf50CallsMi = 0
            gameHand.numOf50CallsVi = 0
            gameHand.numOf100CallsMi = 0
            gameHand.numOf100CallsVi = 0
            gameHand.numOf150CallsMi = 0
            gameHand.numOf150CallsVi = 0
            gameHand.numOf200CallsMi = 0
            gameHand.numOf200CallsVi = 0
            gameHand.stiljaMi = 0
            gameHand.stiljaVi = 0
            gameHand.padMi = 0
            gameHand.padVi = 0
            gameHand.miPoints = "0"
            gameHand.viPoints = "0"
            if (ctv20MI.visibility == View.VISIBLE){showHide(ctv20MI)}
            if (ctv20VI.visibility == View.VISIBLE){showHide(ctv20VI)}
            if (ctv50MI.visibility == View.VISIBLE){showHide(ctv50MI)}
            if (ctv50VI.visibility == View.VISIBLE){showHide(ctv50VI)}
            if (ctv100MI.visibility == View.VISIBLE){showHide(ctv100MI)}
            if (ctv100VI.visibility == View.VISIBLE){showHide(ctv100VI)}
            
            updatecalculator(txtMi, txtVi, gameHand)
        }

        btnStiljonz.setOnClickListener {
            val (newMiPoints, newViPoints) = pad(pointsDirectionFlag)
            gameHand.viPoints = newViPoints.toString()
            gameHand.miPoints = newMiPoints.toString()

            if (pointsDirectionFlag){
                gameHand.stiljaMi = 0
                gameHand.stiljaVi = 1
                btnStiljonz.setTextColor(getColor(R.color.viColor))
            }
            else{
                gameHand.stiljaMi = 1
                gameHand.stiljaVi = 0
                btnStiljonz.setTextColor(getColor(R.color.miColor))
            }

           updatecalculator(txtMi, txtVi, gameHand)
        }
        btn20.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameHand.numOf20CallsVi == 0){
                    showHide(ctv20VI)
                }
                gameHand.numOf20CallsVi += 1
                ctv20VI.text = gameHand.numOf20CallsVi.toString()
            }
            else{ // MI
                if (gameHand.numOf20CallsMi == 0){
                    showHide(ctv20MI)
                }
                gameHand.numOf20CallsMi += 1
                ctv20MI.text = gameHand.numOf20CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn50.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameHand.numOf50CallsVi == 0){
                    showHide(ctv50VI)
                }
                gameHand.numOf50CallsVi += 1
                ctv50VI.text = gameHand.numOf50CallsVi.toString()
            }
            else{ // MI
                if (gameHand.numOf50CallsMi == 0){
                    showHide(ctv50MI)
                }
                gameHand.numOf50CallsMi += 1
                ctv50MI.text = gameHand.numOf50CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn100.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (gameHand.numOf100CallsVi == 0){
                    showHide(ctv100VI)
                }
                gameHand.numOf100CallsVi += 1
                ctv100VI.text = gameHand.numOf100CallsVi.toString()
            }
            else{ // MI
                if (gameHand.numOf100CallsMi == 0){
                    showHide(ctv100MI)
                }
                gameHand.numOf100CallsMi += 1
                ctv100MI.text = gameHand.numOf100CallsMi.toString()
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn150.setOnClickListener {
            if (pointsDirectionFlag){
                gameHand.numOf150CallsMi = 0
                gameHand.numOf150CallsVi = 1
                btn150.setTextColor(getColor(R.color.viColor))
            }
            else {
                gameHand.numOf150CallsMi = 1
                gameHand.numOf150CallsVi = 0
                btn150.setTextColor(getColor(R.color.miColor))
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }
        btn200.setOnClickListener {
            if (pointsDirectionFlag){
                gameHand.numOf200CallsMi = 0
                gameHand.numOf200CallsVi = 1
                btn200.setTextColor(getColor(R.color.viColor))
            }
            else {
                gameHand.numOf200CallsMi = 1
                gameHand.numOf200CallsVi = 0
                btn200.setTextColor(getColor(R.color.miColor))
            }

            updatecalculator(txtMi, txtVi, gameHand)
        }

        btnDone.setOnClickListener {
            val intent = Intent()
            val data = Gson().toJson(gameHand)
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

    private fun updatecalculator(txtMi: TextView, txtVi: TextView, gameHand: GameHand){
        txtMi.text = gameHand.getMiPointsSum().toString()
        txtVi.text = gameHand.getViPointsSum().toString()
    }

    private fun checkPad(gameHand: GameHand){
        TODO()
    }
}