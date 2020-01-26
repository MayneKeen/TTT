package com.lab.ttt.game

open class Player {

    var name: String? = null   //= "Computer";

    constructor() {
        name = "Bot"
    }

    fun equals(player: Player?): Boolean {
        return if(player!=null){
            this.name == player.name
        } else false
    }

    constructor(name: String) {
        this.name = name
    }

}

