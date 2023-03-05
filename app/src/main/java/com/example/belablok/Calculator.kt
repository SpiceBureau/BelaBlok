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
            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()

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
                if (txtMi.text.toString() == "0") { txtMi.text = "0" }
                else { txtMi.append("0") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }


            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "0" }
                else { txtVi.append("0") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn1.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){
                    txtMi.text = "1" }
                else { txtMi.append("1") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "1" }
                else { txtVi.append("1") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn2.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "2" }
                else { txtMi.append("2") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "2" }
                else { txtVi.append("2") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn3.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "3" }
                else { txtMi.append("3") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "3" }
                else { txtVi.append("3") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn4.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "4" }
                else { txtMi.append("4") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "4" }
                else { txtVi.append("4") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn5.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "5" }
                else { txtMi.append("5") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "5" }
                else { txtVi.append("5") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn6.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "6" }
                else { txtMi.append("6") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "6" }
                else { txtVi.append("6") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn7.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "7" }
                else { txtMi.append("7") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "7" }
                else { txtVi.append("7") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn8.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "8" }
                else { txtMi.append("8") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "8" }
                else { txtVi.append("8") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }
        btn9.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "9" }
                else { txtMi.append("9") }

                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                if (txtVi.text.toString() == "0"){ txtVi.text = "9" }
                else { txtVi.append("9") }

                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }

            gameRound.miPoints = Integer.parseInt(txtMi.text.toString())
            gameRound.viPoints = Integer.parseInt(txtVi.text.toString())
        }

        btnPad.setOnClickListener {
            if (pointsDirectionFlag){
                txtMi.text = "162"
                txtVi.text = "0"
                gameRound.padVi = 1
            }
            else{
                txtMi.text = "0"
                txtVi.text = "162"
                gameRound.padMi = 1
            }
        }
        btnClear.setOnClickListener {
            txtMi.text = "0"
            txtVi.text = "0"
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
            if (ctv20MI.visibility == View.VISIBLE){showHide(ctv20MI)}
            if (ctv20VI.visibility == View.VISIBLE){showHide(ctv20VI)}
            if (ctv50MI.visibility == View.VISIBLE){showHide(ctv50MI)}
            if (ctv50VI.visibility == View.VISIBLE){showHide(ctv50VI)}
            if (ctv100MI.visibility == View.VISIBLE){showHide(ctv100MI)}
            if (ctv100VI.visibility == View.VISIBLE){showHide(ctv100VI)}
        }

        btnStiljonz.setOnClickListener {
            val (newMiPoints, newViPoints) = pad(pointsDirectionFlag)
            gameRound.viPoints = newViPoints
            gameRound.miPoints = newMiPoints

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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

            txtMi.text = gameRound.getMiPointsSum().toString()
            txtVi.text = gameRound.getViPointsSum().toString()
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

    private fun checkPad(gameRound: GameRound){
        TODO()
    }
}