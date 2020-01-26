package com.lab.ttt.wincheckers

import com.lab.ttt.game.Game
import com.lab.ttt.game.Player

class WinnerCheckerDiagonalRight(private val game: Game) :
    WinnerCheckerInterface {

    override fun checkWinner(): Player? {
        val field = game.field
        var currPlayer: Player?
        var lastPlayer: Player? = null
        var successCounter = 1
        var i = 0
        val len = field.size
        while (i < len) {
            currPlayer = field[i][len - (i + 1)].player
            if (currPlayer != null) {
                if (lastPlayer === currPlayer) {
                    successCounter++
                    if (successCounter == len) {
                        return currPlayer
                    }
                }
            }
            lastPlayer = currPlayer
            i++
        }
        return null
    }
}