package com.example.belablok.storage.data_classes

class Player(var name: String?) {
    var gamesPlayed = 0
    var gamesWon = 0
    var numOfTimesAsCaller = 0
    var numOfTimesAsMus = 0
    var padovi = 0
    var adutCounts = mutableMapOf<String, Int>("tref" to 0, "kara" to 0, "herc" to 0, "pik" to 0)
    var pointsEarnedWhenCalling = 0
    var roundsPlayed = 0
}