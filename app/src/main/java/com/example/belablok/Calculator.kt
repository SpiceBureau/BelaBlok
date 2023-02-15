package com.example.belablok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_activity)

        supportActionBar?.hide()

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

        var numOf20CallsMI = 0
        var numOf20CallsVI = 0

        var numOf50CallsMI = 0
        var numOf50CallsVI = 0

        var numOf100CallsMI = 0
        var numOf100CallsVI = 0

        var pointsDirectionFlag = false // 0 = Mi || 1 = Vi


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

        btn0.setOnClickListener {
            if (!pointsDirectionFlag) {
                if (txtMi.text.toString() == "0"){ txtMi.text = "0" }
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
        }

        btnPad.setOnClickListener {
            if (pointsDirectionFlag){
                txtMi.text = "162"
                txtVi.text = "0"
            }
            else{
                txtMi.text = "0"
                txtVi.text = "162"
            }
        }
        btnClear.setOnClickListener {
            txtMi.text = "0"
            txtVi.text = "0"
            btnStiljonz.setTextColor(Color.WHITE)
            btn150.setTextColor(Color.WHITE)
            btn200.setTextColor(Color.WHITE)
            numOf20CallsMI = 0
            numOf20CallsVI = 0
            numOf50CallsMI = 0
            numOf50CallsVI = 0
            numOf100CallsMI = 0
            numOf100CallsVI = 0
            if (ctv20MI.visibility == View.VISIBLE){showHide(ctv20MI)}
            if (ctv20VI.visibility == View.VISIBLE){showHide(ctv20VI)}
            if (ctv50MI.visibility == View.VISIBLE){showHide(ctv50MI)}
            if (ctv50VI.visibility == View.VISIBLE){showHide(ctv50VI)}
            if (ctv100MI.visibility == View.VISIBLE){showHide(ctv100MI)}
            if (ctv100VI.visibility == View.VISIBLE){showHide(ctv100VI)}
        }

        btnStiljonz.setOnClickListener {
            val (newMiPoints, newViPoints) = pad(pointsDirectionFlag)
            txtVi.text = newViPoints
            txtMi.text = newMiPoints

            if (pointsDirectionFlag){btnStiljonz.setTextColor(getColor(R.color.viColor))}
            else {btnStiljonz.setTextColor(getColor(R.color.miColor))}
        }
        btn20.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (numOf20CallsVI == 0){
                    showHide(ctv20VI)
                }
                numOf20CallsVI += 1
                ctv20VI.text = numOf20CallsVI.toString()
            }
            else{ // MI
                if (numOf20CallsMI == 0){
                    showHide(ctv20MI)
                }
                numOf20CallsMI += 1
                ctv20MI.text = numOf20CallsMI.toString()
            }
        }
        btn50.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (numOf50CallsVI == 0){
                    showHide(ctv50VI)
                }
                numOf50CallsVI += 1
                ctv50VI.text = numOf50CallsVI.toString()
            }
            else{ // MI
                if (numOf50CallsMI == 0){
                    showHide(ctv50MI)
                }
                numOf50CallsMI += 1
                ctv50MI.text = numOf50CallsMI.toString()
            }
        }
        btn100.setOnClickListener {
            if (pointsDirectionFlag){ // VI
                if (numOf100CallsVI == 0){
                    showHide(ctv100VI)
                }
                numOf100CallsVI += 1
                ctv100VI.text = numOf100CallsVI.toString()
            }
            else{ // MI
                if (numOf100CallsMI == 0){
                    showHide(ctv100MI)
                }
                numOf100CallsMI += 1
                ctv100MI.text = numOf100CallsMI.toString()
            }
        }
        btn150.setOnClickListener {
            if (pointsDirectionFlag){btn150.setTextColor(getColor(R.color.viColor))}
            else {btn150.setTextColor(getColor(R.color.miColor))}
        }
        btn200.setOnClickListener {
            if (pointsDirectionFlag){btn200.setTextColor(getColor(R.color.viColor))}
            else {btn200.setTextColor(getColor(R.color.miColor))}
        }

        btnDone.setOnClickListener {
            val intent = Intent()
            intent.putExtra("miPoints", txtMi.text.toString())
            intent.putExtra("viPoints", txtVi.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun pad(pointsDirectionFlag: Boolean): Pair<String, String> {
        return if (pointsDirectionFlag){
            Pair("0", "162")
        } else{
            Pair("162", "0")
        }
    }
    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){View.GONE} else{View.VISIBLE}
    }
}