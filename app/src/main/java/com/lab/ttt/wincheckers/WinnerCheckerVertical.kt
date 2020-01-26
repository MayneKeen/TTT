package com.lab.ttt.wincheckers

import com.lab.ttt.game.Game
import com.lab.ttt.game.Player

class WinnerCheckerVertical(private val game: Game) :
    WinnerCheckerInterface {

    override fun checkWinner(): Player? {
        val field = game.field
        var currPlayer: Player?
        var lastPlayer: Player? = null
        var i = 0
        val len = field.size
        while (i < len) {
            lastPlayer = null
            var successCounter = 1
            var j = 0
            val len2 = field[i].size
            while (j < len2) {
                currPlayer = field[j][i].player
                if (currPlayer === lastPlayer && currPlayer != null && lastPlayer != null) {
                    successCounter++
                    if (successCounter == len2) {
                        return currPlayer
                    }
                }
                lastPlayer = currPlayer
                j++
            }
            i++
        }
        return null
    }
}
