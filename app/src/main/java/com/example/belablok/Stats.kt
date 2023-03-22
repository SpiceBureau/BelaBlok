package com.example.belablok

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


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

        val graphView: GraphView = findViewById(R.id.graph)

        graphView.gridLabelRenderer.verticalLabelsColor = Color.WHITE
        graphView.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
        graphView.gridLabelRenderer.gridColor = Color.WHITE
        val series = LineGraphSeries(
            arrayOf(
                DataPoint(0.0, 1.0),
                DataPoint(1.0, 5.0),
                DataPoint(2.0, 3.0),
                DataPoint(3.0, 2.0),
                DataPoint(4.0, 6.0)
            )
        )
        graphView.addSeries(series)
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