package com.lab.ttt.game

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import com.lab.ttt.App
import com.lab.ttt.R
import com.lab.ttt.database.ScoreDao

import com.lab.ttt.database.ScoreEntry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    var database: ScoreDao? = null
    private val game: Game
    private var tablelayout: TableLayout? = null // экземпляр нашего табличного макета

    private val buttons = Array<Array<Button?>>(3) { arrayOfNulls(3) }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tablelayout = findViewById<View>(R.id.main_l) as TableLayout
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        buildGameField() //creation of a field

        database = (this.application as App).getDatabase()!!.scoreDao()
    }

    init {
        game = Game()
        game.start()

    }

    private fun buildGameField() {

        val display = windowManager.defaultDisplay
        val width = display.width
        val height = display.height
        val min = min(width, height)


        val field = game.field
        var i = 0
        val lenI = field.size
        while (i < lenI) {
            val row = TableRow(this) // создание строки таблицы
            var j = 0
            val lenJ = field[i].size
            while (j < lenJ) {
                val button = Button(this)
                buttons[i][j] = button
                button.setOnClickListener(Listener(i, j))
                row.addView(button, TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)) // adding a button to a table row
                button.width = min / 3
                button.height = min / 3
                button.textSize = 35.0F
                j++
            }
            tablelayout!!.addView(row, TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)) // adding a row to a table
            i++
        }
    }

    inner class Listener(x: Int, y: Int) : View.OnClickListener {
        private var x = 0
        private var y = 0

        init {
            this.x = x
            this.y = y
        }

        override fun onClick(view: View) {
            val button = view as Button
            val g = game


            val player = g.currentActivePlayer
            if (makeTurn(x, y)) {
                button.text = player!!.name
            }
            val winner = g.checkWinner()
            if (winner != null) {
                gameOver(winner)
            }
            if (g.isFieldFilled) {  // in a case field's filled
                gameOver()
            }
        }
    }

    private fun gameOver(player: Player) {

        val pl1:Player = (this.application as App).player1
        val pl2:Player = (this.application as App).player2

        val winner:Player = if(player.name == "X") pl1 else pl2

        val text = "Player \"" + winner.name + "\" won!"

        GlobalScope.launch {

            val oldResult = database!!.getByName(winner.name.toString())
            if (oldResult == null) {
                database!!.insert(
                    ScoreEntry(
                        playerName = winner.name,
                        score = 10
                    )
                )
            } else {
                database!!.updateScore(winner.name.toString(), oldResult.score + 10)
            }

        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        game.reset()
        refresh()
    }

    private fun gameOver() {
        val text = "Draw"
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        game.reset()
        refresh()
    }


    private fun makeTurn(x: Int, y: Int): Boolean {
        return game.makeTurn(x, y)
    }

    private fun refresh() {
        val field = game.field

        var i = 0
        val len = field.size
        while (i < len) {
            var j = 0
            val len2 = field[i].size
            while (j < len2) {
                if (field[i][j].player == null) {
                    buttons[i][j]!!.text = ""
                } else {
                    buttons[i][j]!!.text = field[i][j].player!!.name
                }
                j++
            }
            i++
        }
    }


}
