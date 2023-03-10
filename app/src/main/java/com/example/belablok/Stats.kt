package com.example.belablok

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent

class Stats : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.stats_layout)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent()
                setResult(RESULT_CANCELED, intent)
                finish()
                overridePendingTransition(R.anim.nothing, R.anim.down_up)
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val intent = Intent()
                setResult(RESULT_CANCELED, intent)
                finish()
                overridePendingTransition(R.anim.nothing, R.anim.down_up)
                true
            }
            else -> super.onTouchEvent(event)
        }
    }



}