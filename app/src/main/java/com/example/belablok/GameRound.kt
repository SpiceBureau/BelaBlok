package com.example.belablok

import java.lang.Math.abs

class GameRound {
    var miPoints: Int = 0
    var viPoints: Int = 0
    var adut: String = "null"
    val delta = kotlin.math.abs(miPoints - viPoints)

    var numOf20CallsMi = 0
    var numOf50CallsMi = 0
    var numOf100CallsMi = 0
    var numOf150CallsMi = 0
    var numOf200CallsMi = 0
    var numOf20CallsVi = 0
    var numOf50CallsVi = 0
    var numOf100CallsVi = 0
    var numOf150CallsVi = 0
    var numOf200CallsVi = 0
    var stiljaMi = 0
    var stiljaVi = 0

    var padMi = 0
    var padVi = 0

    fun getMiPointsSum(): Int {
        return miPoints + numOf20CallsMi*20 + numOf50CallsMi*50 + numOf100CallsMi*100 + numOf150CallsMi*150 + numOf200CallsMi*200 + stiljaMi*90
    }
    fun getViPointsSum(): Int {
        return viPoints + numOf20CallsVi*20 + numOf50CallsVi*50 + numOf100CallsVi*100 + numOf150CallsVi*150 + numOf200CallsVi*200 + stiljaVi*90
    }
}