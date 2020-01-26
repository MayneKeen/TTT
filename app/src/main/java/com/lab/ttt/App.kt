package com.lab.ttt

import android.app.Application
import androidx.room.Room
import com.lab.ttt.database.ScoreDatabase
import com.lab.ttt.game.Player


class App: Application() {
    private var instance: App? = null

    private var database: ScoreDatabase? = null

    var player1 = Player("X")
    var player2 = Player("O")

   /* fun resetPlayers() {
        var player1 = Player("X")
        var player2 = Player("O")
    }
*/
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder<ScoreDatabase>(this, ScoreDatabase::class.java, "database")
            .build()
    }

    fun getInstance(): App? {
        return instance
    }

    fun getDatabase(): ScoreDatabase? {
        return database
    }
}