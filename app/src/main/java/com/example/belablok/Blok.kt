package com.example.belablok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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




        var pointsDirectionFlag = 0 // 0 = Mi || 1 = Vi


        txtMi.setOnClickListener{
            pointsDirectionFlag = 0
            txtMi.setBackgroundResource(com.google.android.material.R.color.design_default_color_secondary)
            txtVi.setBackgroundResource(0)
        }
        txtVi.setOnClickListener{
            pointsDirectionFlag = 1
            txtVi.setBackgroundResource(com.google.android.material.R.color.design_default_color_secondary)
            txtMi.setBackgroundResource(0)
        }

        btn0.setOnClickListener {
            if (pointsDirectionFlag == 0){
                txtMi.text = txtMi.text + 0.toString()
            }
        }

    }
}