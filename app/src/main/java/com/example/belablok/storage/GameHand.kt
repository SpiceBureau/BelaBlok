package com.example.belablok.storage

class GameHand{

    var matchPointsListItemFlag = false
    var matchWin = ""
    var miMatchPoints = 0
    var viMatchPoints = 0

    var miPoints: String = "0"
    var viPoints: String = "0"
    var adut: String = "null"
    var shuffler: String = ""
    var caller: String = ""

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
        return Integer.parseInt(miPoints) + numOf20CallsMi*20 + numOf50CallsMi*50 + numOf100CallsMi*100 + numOf150CallsMi*150 + numOf200CallsMi*200 + stiljaMi*90
    }
    fun getViPointsSum(): Int {
        return Integer.parseInt(viPoints) + numOf20CallsVi*20 + numOf50CallsVi*50 + numOf100CallsVi*100 + numOf150CallsVi*150 + numOf200CallsVi*200 + stiljaVi*90
    }

    fun geMiPointsFromCalls(): Int{
        return numOf20CallsMi*20 + numOf50CallsMi*50 + numOf100CallsMi*100 + numOf150CallsMi*150 + numOf200CallsMi*200 + stiljaMi*90
    }

    fun getViPointsFromCalls(): Int{
        return  numOf20CallsVi*20 + numOf50CallsVi*50 + numOf100CallsVi*100 + numOf150CallsVi*150 + numOf200CallsVi*200 + stiljaVi*90
    }
}