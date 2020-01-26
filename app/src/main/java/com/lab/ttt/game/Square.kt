package com.lab.ttt.game

class Square {
    var player: Player? = null
        private set

    val isFilled: Boolean
        get() = player != null

    fun fill(player: Player?) {
        this.player = player
    }
}

