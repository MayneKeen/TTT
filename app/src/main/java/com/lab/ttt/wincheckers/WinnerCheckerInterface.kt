package com.lab.ttt.wincheckers

import com.lab.ttt.game.Player

interface WinnerCheckerInterface {
    fun checkWinner(): Player?
}
