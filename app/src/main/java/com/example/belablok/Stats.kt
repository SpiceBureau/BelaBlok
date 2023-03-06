package com.example.belablok

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintSet.Layout
import com.example.belablok.ui.theme.BelaBlokTheme
import com.google.gson.Gson

class Stats : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { R.layout.stats_layout }

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