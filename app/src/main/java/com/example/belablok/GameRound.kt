package com.example.belablok

import java.lang.Math.abs

class GameRound(val miPoints: String, val viPoints: String) {
    val delta = kotlin.math.abs(Integer.parseInt(miPoints) - Integer.parseInt(viPoints))
}