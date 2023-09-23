package com.example.belablok

import android.content.Intent
import android.graphics.Color
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


        var maxYPoinrsValue = 0.0
        var maxDiffValue = 0.0
        var indexCounter = 0

        val seriesMiPPR = LineGraphSeries<DataPoint>()
        seriesMiPPR.color = Color.parseColor("#03dac6")
        val seriesViPPR = LineGraphSeries<DataPoint>()
        seriesViPPR.color = Color.parseColor("#da6b03")
        val seriesDiff = LineGraphSeries<DataPoint>()
        seriesDiff.color = Color.parseColor("#FF0000")

        var lastDiff = 0.0
        if (xValues != null && yValuesMi != null && yValuesVi != null) {
            for (xvalue in xValues) {
                indexCounter = xvalue.toInt()
                val dataPointMi = DataPoint(xvalue, yValuesMi[indexCounter])
                val dataPointVi = DataPoint(xvalue, yValuesVi[indexCounter])
                val dataPointDiff = DataPoint(xvalue, yValuesMi[indexCounter] - yValuesVi[indexCounter] + lastDiff)
                lastDiff = yValuesMi[indexCounter] - yValuesVi[indexCounter]
                seriesMiPPR.appendData(dataPointMi, true, 100)
                seriesViPPR.appendData(dataPointVi, true, 100)
                seriesDiff.appendData(dataPointDiff, true, 100)

                maxYPoinrsValue = if (yValuesMi[indexCounter] > yValuesVi[indexCounter]) yValuesMi[indexCounter] else yValuesVi[indexCounter]
                maxDiffValue = if (dataPointDiff.y > maxDiffValue) dataPointDiff.y else maxDiffValue
            }

            val pointsPerRound: GraphView = findViewById(R.id.pointsPerRound)
            pointsPerRound.gridLabelRenderer.verticalLabelsColor = Color.WHITE
            pointsPerRound.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
            pointsPerRound.gridLabelRenderer.gridColor = Color.WHITE

            pointsPerRound.viewport.isScalable = true;
            pointsPerRound.viewport.setScalableY(true);
            pointsPerRound.addSeries(seriesMiPPR)
            pointsPerRound.addSeries(seriesViPPR)

            pointsPerRound.viewport.setMaxY(maxYPoinrsValue + 100.0)
            pointsPerRound.viewport.setMaxX(indexCounter.toDouble())
            pointsPerRound.viewport.isXAxisBoundsManual = true
            pointsPerRound.viewport.isYAxisBoundsManual = true

            val horizontalLineSeries = LineGraphSeries<DataPoint>(
                arrayOf<DataPoint>(
                    DataPoint(0.0, 1000.0),
                    DataPoint(indexCounter.toDouble(), 1000.0)
                )
            )
            horizontalLineSeries.color = Color.RED
            horizontalLineSeries.isDrawDataPoints = false
            horizontalLineSeries.isDrawBackground = false

            pointsPerRound.addSeries(horizontalLineSeries)

            val diffPerRound: GraphView = findViewById(R.id.differencePerRound)
            diffPerRound.gridLabelRenderer.verticalLabelsColor = Color.WHITE
            diffPerRound.gridLabelRenderer.horizontalLabelsColor = Color.WHITE
            diffPerRound.gridLabelRenderer.gridColor = Color.WHITE

            diffPerRound.viewport.isScalable = true;
            diffPerRound.viewport.setScalableY(true);
            diffPerRound.addSeries(seriesDiff)

            diffPerRound.viewport.setMaxY(maxDiffValue + 50.0)
            diffPerRound.viewport.setMinY(-200.0)
            diffPerRound.viewport.setMaxX(indexCounter.toDouble())
            diffPerRound.viewport.isXAxisBoundsManual = true
            diffPerRound.viewport.isYAxisBoundsManual = true
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
