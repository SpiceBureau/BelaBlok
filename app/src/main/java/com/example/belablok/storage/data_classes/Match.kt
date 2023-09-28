package com.example.belablok.storage.data_classes

class Match(var gameRounds: MutableList<GameRound>?, var player1: User?, var player2: User?, var player3: User?, var player4: User?) {
    var miMatchPoints = 0
    var viMatchPoints = 0
}