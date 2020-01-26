package com.lab.ttt.game

import com.lab.ttt.wincheckers.*



class Game {

    private val players: Array<Player?>

    val field: Array<Array<Square>>

    // is the game started?
    private var started: Boolean = false

    var currentActivePlayer: Player? = null
        private set

    // number of filled squares
    private var filled: Int = 0

    // number of squares
    private var squareCount: Int = 0


    // "Referees". they check for a winner after each turn
    private val winnerCheckers: Array<WinnerCheckerInterface?>

    val isFieldFilled: Boolean
        get() = squareCount == filled

    // constructor
    init {
        var a:Array<Array<Square>> = Array(3) { arrayOf(
            Square(),
            Square(),
            Square()
        )}

        field = a
        squareCount = 0

        // filling a field

        var i = 0
        val l = field.size
        while (i < l) {
            var j = 0
            val l2 = field[i].size
            while (j < l2) {
                field[i][j] = Square()
                squareCount++
                j++
            }
            i++
        }
        players = arrayOfNulls(2)
        started = false
        currentActivePlayer = null
        filled = 0

        // "referees" init
        winnerCheckers = arrayOfNulls(4)
        winnerCheckers[0] = WinnerCheckerHorizontal(this)
        winnerCheckers[1] = WinnerCheckerVertical(this)
        winnerCheckers[2] =
            WinnerCheckerDiagonalLeft(this)
        winnerCheckers[3] =
            WinnerCheckerDiagonalRight(this)

    }


    fun checkWinner(): Player? {
        for (winChecker in winnerCheckers) {
            val winner = winChecker!!.checkWinner()
            if (winner != null) {

                return winner
            }
        }
        return null
    }


    fun start() {
        resetPlayers()
        started = true
    }

    private fun resetPlayers() {
        // crutch ))))))))))))))))))))
        players[0] = Player("X")
        players[1] = Player("O")
        currentActivePlayer = players[0]
    }

    fun makeTurn(x: Int, y: Int): Boolean {
        if (field[x][y].isFilled) {
            return false
        }
        field[x][y].fill(currentActivePlayer)
        filled++
        switchPlayers()
        return true
    }

    private fun switchPlayers() {
        currentActivePlayer = if (currentActivePlayer === players[0]) players[1] else players[0]
    }

    fun reset() {

        resetField()
        resetPlayers()
    }

    private fun resetField() {
        var i = 0
        val l = field.size
        while (i < l) {
            var j = 0
            val l2 = field[i].size
            while (j < l2) {
                field[i][j].fill(null)
                j++
            }
            i++
        }
        filled = 0
    }

}