package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.js.Date
import kotlin.random.Random

private fun List<List<TileState>>.incAround(x: Int, y: Int) {
    // 左上
    if (x != 0 && y != 0) {
        if (!this[x - 1][y - 1].isMine) this[x - 1][y - 1].numOfAroundMines++
    }
    // 真上
    if (x != 0) {
        if (!this[x - 1][y].isMine) this[x - 1][y].numOfAroundMines++
    }
    // 右上
    if (x != 0 && this[x - 1].size > y + 1) {
        if (!this[x - 1][y + 1].isMine) this[x - 1][y + 1].numOfAroundMines++
    }

    // 左
    if (y != 0) {
        if (!this[x][y - 1].isMine) this[x][y - 1].numOfAroundMines++
    }
    // 右
    if (this[x].size > y + 1) {
        if (!this[x][y + 1].isMine) this[x][y + 1].numOfAroundMines++
    }

    // 左下
    if (this.size > x + 1 && y != 0) {
        if (!this[x + 1][y - 1].isMine) this[x + 1][y - 1].numOfAroundMines++
    }
    // 真下
    if (this.size > x + 1) {
        if (!this[x + 1][y].isMine) this[x + 1][y].numOfAroundMines++
    }
    // 右下
    if (this.size > x + 1 && this[x + 1].size > y + 1) {
        if (!this[x + 1][y + 1].isMine) this[x + 1][y + 1].numOfAroundMines++
    }
}

class MineSweeperLogic(val xLength: Int, val yLength: Int, val numOfMines: Int, val seed: Int) {
    // y軸方向に各マスの情報を格納している
    val board: List<List<TileState>>
    var gameState by mutableStateOf(GameState.BeforeStarts)
        private set
    private val coordinatesOfOpened = mutableSetOf<Pair<Int, Int>>()
    private val coordinatesWithoutMines: Set<Pair<Int, Int>>

    var isDevMode by mutableStateOf(false)
    private var startTime = 0.0

    init {
        val connectedList = mutableListOf<TileState>()
        val coordinatesOfMines = mutableSetOf<Pair<Int, Int>>()
        val coordinatesOfPlane = mutableSetOf<Pair<Int, Int>>()

        for (i in 1..numOfMines) {
            connectedList.add(TileState(true))
        }

        while (connectedList.size <= xLength * yLength) {
            connectedList.add(TileState(false))
        }

        connectedList.shuffle(Random(seed))

        board = connectedList.windowed(yLength, yLength)

        for (x in 0 until xLength) {
            for (y in 0 until yLength) {
                coordinatesOfPlane.add(Pair(x, y))
                if (board[x][y].isMine) {
                    coordinatesOfMines.add(Pair(x, y))
                    board.incAround(x, y)
                }
            }
        }
        coordinatesOfPlane.removeAll(coordinatesOfMines)
        coordinatesWithoutMines = coordinatesOfPlane
    }

    fun openTileWithAround(x: Int, y: Int) {
        if (gameState == GameState.BeforeStarts) firstTimeAction()
        if (board[x][y].isFlagged) return
        openTile(x, y)
        if (board[x][y].numOfAroundMines == 0) {
            board.openAround(x, y)
        }
    }

    fun toggleTileFlag(x: Int, y: Int) {
        if (gameState == GameState.BeforeStarts) firstTimeAction()
        board[x][y].isFlagged = !board[x][y].isFlagged
    }

    fun getElapsedSeconds(): Double {
        return (Date.now() - startTime) / 1000
    }

    private fun firstTimeAction() {
        gameState = GameState.Started
        startTime = Date.now()
    }

    private fun openTile(x: Int, y: Int) {
        if (board[x][y].isFlagged) toggleTileFlag(x, y)
        board[x][y].isOpened = true
        coordinatesOfOpened.add(Pair(x, y))
        if (coordinatesOfOpened == coordinatesWithoutMines) {
            gameState = GameState.GameClear
        } else if (board[x][y].isMine && !isDevMode) {
            gameState = GameState.GameOver
        }
    }


    private fun List<List<TileState>>.openAround(x: Int, y: Int) {
        // 左上
        if (x != 0 && y != 0 && !this[x - 1][y - 1].isOpened) {
            openTile(x - 1, y - 1)
            if (this[x - 1][y - 1].numOfAroundMines == 0) {
                this.openAround(x - 1, y - 1)
            }
        }
        // 真上
        if (x != 0 && !this[x - 1][y].isOpened) {
            openTile(x - 1, y)
            if (this[x - 1][y].numOfAroundMines == 0) {
                this.openAround(x - 1, y)
            }
        }
        // 右上
        if (x != 0 && this[x - 1].size > y + 1 && !this[x - 1][y + 1].isOpened) {
            openTile(x - 1, y + 1)
            if (this[x - 1][y + 1].numOfAroundMines == 0) {
                this.openAround(x - 1, y + 1)
            }
        }
        // 左
        if (y != 0 && !this[x][y - 1].isOpened) {
            openTile(x, y - 1)
            if (this[x][y - 1].numOfAroundMines == 0) {
                this.openAround(x, y - 1)
            }
        }
        // 右
        if (this[x].size > y + 1 && !this[x][y + 1].isOpened) {
            openTile(x, y + 1)
            if (this[x][y + 1].numOfAroundMines == 0) {
                this.openAround(x, y + 1)
            }
        }
        // 左下
        if (this.size > x + 1 && y != 0 && !this[x + 1][y - 1].isOpened) {
            openTile(x + 1, y - 1)
            if (this[x + 1][y - 1].numOfAroundMines == 0) {
                this.openAround(x + 1, y - 1)
            }
        }
        // 真下
        if (this.size > x + 1 && !this[x + 1][y].isOpened) {
            openTile(x + 1, y)
            if (this[x + 1][y].numOfAroundMines == 0) {
                this.openAround(x + 1, y)
            }
        }
        // 右下
        if (this.size > x + 1 && this[x + 1].size > y + 1 && !this[x + 1][y + 1].isOpened) {
            openTile(x + 1, y + 1)
            if (this[x + 1][y + 1].numOfAroundMines == 0) {
                this.openAround(x + 1, y + 1)
            }
        }
    }
}
