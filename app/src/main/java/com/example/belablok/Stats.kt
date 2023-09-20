package com.example.belablok

import android.content.Intent
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class Stats : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

        val xValues = intent.getSerializableExtra("xValues") as ArrayList<Double>?
        val yValuesMi = intent.getSerializableExtra("yValuesMi") as ArrayList<Double>?
        val yValuesVi = intent.getSerializableExtra("yValuesVi") as ArrayList<Double>?

        val miVSvi = findViewById<TextView>(R.id.miVSviTv)
        miVSvi.text = HtmlCompat.fromHtml("<font color=${Color.parseColor("#03dac6")}>MI </font>" + "vs" +
                "<font color=${Color.parseColor("#da6b03")}> VI</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)


        var maxYValue = 0.0
        var indexCounter = 0

        val seriesMi = LineGraphSeries<DataPoint>()
        seriesMi.color = Color.parseColor("#03dac6")
        val seriesVi = LineGraphSeries<DataPoint>()
        seriesVi.color = Color.parseColor("#da6b03")
        if (xValues != null && yValuesMi != null && yValuesVi != null) {
            for (xvalue in xValues) {
                indexCounter = xvalue.toInt()
                val dataPointMi = DataPoint(xvalue, yValuesMi[indexCounter])
                val dataPointVi = DataPoint(xvalue, yValuesVi[indexCounter])
                seriesMi.appendData(dataPointMi, true, 100)
                seriesVi.appendData(dataPointVi, true, 100)

                maxYValue = if (yValuesMi[indexCounter] > yValuesVi[indexCounter]) yValuesMi[indexCounter] else yValuesVi[indexCounter]
            }

            val graphView: GraphView = findViewById(R.id.graph)
            graphView.gridLabelRenderer.verticalLabelsColor = Color.WHITE
            graphView.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
            graphView.gridLabelRenderer.gridColor = Color.WHITE


            graphView.viewport.isScalable = true;
            graphView.addSeries(seriesMi)
            graphView.addSeries(seriesVi)

            graphView.viewport.setMaxY(maxYValue + 100.0)
            graphView.viewport.setMaxX(indexCounter.toDouble())
            graphView.viewport.isXAxisBoundsManual = true
            graphView.viewport.isYAxisBoundsManual = true

/*            val horizontalLineSeries = LineGraphSeries<DataPoint>(
                arrayOf<DataPoint>(
                    DataPoint(0.0, 1000.0),
                    DataPoint(indexCounter.toDouble() - 1.0, 1000.0)
                )
            )
            horizontalLineSeries.color = Color.RED
            horizontalLineSeries.isDrawDataPoints = false
            horizontalLineSeries.isDrawBackground = false

            graphView.addSeries(horizontalLineSeries);*/
        }
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
