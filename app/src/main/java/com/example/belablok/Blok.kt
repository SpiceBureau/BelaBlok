package com.example.belablok

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class Blok : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtMi: TextView = findViewById(R.id.txtMi)
        val txtVi: TextView = findViewById(R.id.txtVi)
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
        val btnDel: Button = findViewById(R.id.btnDel)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btn20: Button = findViewById(R.id.btn20Calls)
        val btnStiljonz: Button = findViewById(R.id.btnŠtiljonž)




        var pointsDirectionFlag = false // 0 = Mi || 1 = Vi


        txtMi.setOnClickListener{
            pointsDirectionFlag = false
            txtMi.setTextColor(Color.parseColor("#03DAC6"))
            txtVi.setTextColor(Color.WHITE)
        }
        txtVi.setOnClickListener{
            pointsDirectionFlag = true
            txtVi.setTextColor(Color.parseColor("#03DAC6"))
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
        btnDel.setOnClickListener {
            val editable: Editable? = if (!pointsDirectionFlag){
                txtMi.editableText
            } else {
                txtVi.editableText
            }
            var charCount = editable?.length
            if (charCount == null){
                charCount = if (!pointsDirectionFlag){ txtMi.length() } else { txtVi.length() }
                //Toast.makeText(this, charCount.toString(), Toast.LENGTH_SHORT).show()
            }
            if (charCount > 0) {
                editable?.delete(charCount - 1, charCount)
            }
            if (editable != null) {
                if (editable.isEmpty()) {
                    if (!pointsDirectionFlag) { txtMi.text = "0" } else { txtVi.text = "0" }
                }
            }
            if (!pointsDirectionFlag){
                val miPoints: Int = Integer.parseInt(txtMi.text.toString())
                val viPoints = 162 - miPoints
                if (viPoints < 0){ txtVi.text = "0" }
                else { txtVi.text = viPoints.toString() }
            }
            else {
                val viPoints: Int = Integer.parseInt(txtVi.text.toString())
                val miPoints = 162 - viPoints
                if (miPoints < 0){ txtMi.text = "0" }
                else { txtMi.text = miPoints.toString() }
            }
        }
        btnClear.setOnClickListener {
            txtMi.text = "0"
            txtVi.text = "0"
            btnStiljonz.setTextColor(Color.WHITE)
        }
        btnStiljonz.setOnClickListener {
            val (newMiPoints, newViPoints) = pad(pointsDirectionFlag)
            txtVi.text = newViPoints
            txtMi.text = newMiPoints

            btnStiljonz.setTextColor(Color.parseColor("#03DAC6"))
        }
    }

    private fun pad(pointsDirectionFlag: Boolean): Pair<String, String> {
        return if (!pointsDirectionFlag){
            Pair("0", "162")
        } else{
            Pair("162", "0")
        }
    }
}