package com.example.belablok.storage.data_classes

class Match(var gameRounds: MutableList<GameRound>?, var player1: Player?, var player2: Player?, var player3: Player?, var player4: Player?) {
    var miMatchPoints = 0
    var viMatchPoints = 0
    var startTime = System.currentTimeMillis()
    var hoursPlayed = 0
    var minutesPlayed = 0
    var year = 0
    var month = 0
    var day = 0
    fun caluclatetimePlayed(endTime: Long){
        val timeElapsedMillis = endTime - startTime

        hoursPlayed = (timeElapsedMillis / (1000 * 60 * 60)).toInt()
        minutesPlayed = ((timeElapsedMillis / (1000 * 60)) % 60).toInt()
    }
}