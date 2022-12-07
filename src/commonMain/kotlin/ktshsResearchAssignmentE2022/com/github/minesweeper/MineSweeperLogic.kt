package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.ISquareState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSquareState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.NormalSquareState
import kotlin.js.Date
import kotlin.random.Random

class MineSweeperLogic(val xLength: Int, val yLength: Int, val numOfMines: Int, val seed: Int) {
    // y軸方向に各マスの情報を格納している
    var board by mutableStateOf(listOf(emptyList<ISquareState>()))
        private set
    var gameStatus by mutableStateOf(GameStatus.BeforeAction)
        private set

    var numOfFlags by mutableStateOf(0)
        private set

    private val coordinatesOfMines = mutableSetOf<Pair<Int, Int>>()
    private val coordinatesOfOpened = mutableSetOf<Pair<Int, Int>>()
    private var coordinatesWithoutMines = mutableSetOf<Pair<Int, Int>>()

    var isDevMode by mutableStateOf(false)
    private var startTime = 0.0

    init {
        board = MutableList(xLength) { x ->
            MutableList(yLength) { y ->
                NormalSquareState(x, y, 0)
            }
        }
    }

    fun openTileWithAround(x: Int, y: Int) {
        if (gameStatus == GameStatus.BeforeAction || gameStatus == GameStatus.BeforeClick) firstTimeOpen(x, y)
        if (board[x][y].isFlagged) return
        openTile(x, y)
        if (board[x][y] is NormalSquareState && (board[x][y] as NormalSquareState).numOfAroundMines == 0) {
            board.openAround(x, y)
        }
    }

    fun toggleTileFlag(x: Int, y: Int) {
        if (gameStatus == GameStatus.BeforeAction) firstTimeAction()
        if (!board[x][y].isFlagged) {
            if (numOfFlags < numOfMines) {
                board[x][y].isFlagged = true
                numOfFlags++
            }
        } else {
            board[x][y].isFlagged = false
            numOfFlags--
        }
    }

    fun getElapsedSeconds(): Double {
        return (Date.now() - startTime) / 1000
    }

    private fun firstTimeAction() {
        gameStatus = GameStatus.BeforeClick
        startTime = Date.now()
    }

    private fun firstTimeOpen(clickedX: Int, clickedY: Int) {
        firstTimeAction()
        gameStatus = GameStatus.Started
        // 地雷の座標を決定
        val rnd = Random(seed)
        while (coordinatesOfMines.size < numOfMines) {
            val x = rnd.nextInt(xLength)
            val y = rnd.nextInt(yLength)
            if (!(x == clickedX && y == clickedY)) {
                coordinatesOfMines.add(Pair(x, y))
            }
        }

        val coordinatesOfPlane = mutableSetOf<Pair<Int, Int>>()
        for (x in 0 until xLength) {
            for (y in 0 until yLength) {
                coordinatesOfPlane.add(Pair(x, y))
            }
        }
        coordinatesOfPlane.removeAll(coordinatesOfMines)

        coordinatesWithoutMines = coordinatesOfPlane

        val flaggedSquareSet = mutableSetOf<Pair<Int, Int>>()
        board.forEachIndexed { x, yList ->
            yList.forEachIndexed { y, iSquareState ->
                if (iSquareState.isFlagged)
                    flaggedSquareSet.add(Pair(x, y))
            }
        }

        board = MutableList(xLength) { x ->
            MutableList(yLength) { y ->
                if (coordinatesOfMines.contains(Pair(x, y))) {
                    MineSquareState(x, y)
                } else {
                    NormalSquareState(x, y, 0)
                }
            }
        }

        board.forEachIndexed { x, yList ->
            yList.forEachIndexed { y, iSquareState ->
                if (iSquareState is NormalSquareState)
                    iSquareState.numOfAroundMines = calcNumOfAroundMine(board, x, y)

                if (flaggedSquareSet.contains(Pair(x, y)))
                    iSquareState.isFlagged = true
            }
        }

        openTileWithAround(clickedX, clickedY)
    }

    private fun openTile(x: Int, y: Int) {
        if (board[x][y].isFlagged) toggleTileFlag(x, y)
        board[x][y].isOpened = true
        coordinatesOfOpened.add(Pair(x, y))
        if (coordinatesOfOpened == coordinatesWithoutMines) {
            gameStatus = GameStatus.GameClear
        } else if (board[x][y] is MineSquareState && !isDevMode) {
            gameStatus = GameStatus.GameOver
        }
    }


    private fun List<List<ISquareState>>.openAround(x: Int, y: Int) {
        for (cx in x - 1..x + 1) {
            if (0 <= cx && cx < this.size) {
                for (cy in y - 1..y + 1) {
                    if (0 <= cy && cy < this[cx].size && !this[cx][cy].isOpened) {
                        openTile(cx, cy)
                        if (board[cx][cy] is NormalSquareState && (this[cx][cy] as NormalSquareState).numOfAroundMines == 0)
                            this.openAround(cx, cy)
                    }
                }
            }
        }
    }
}

private fun calcNumOfAroundMine(board: List<List<ISquareState>>, x: Int, y: Int): Int {
    var num = 0
    for (cx in x - 1..x + 1) {
        if (0 <= cx && cx < board.size) {
            for (cy in y - 1..y + 1) {
                if (0 <= cy && cy < board[cx].size && board[cx][cy] is MineSquareState) {
                    num++
                }
            }
        }
    }
    return num
}
