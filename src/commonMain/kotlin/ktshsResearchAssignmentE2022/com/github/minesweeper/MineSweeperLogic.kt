package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

private fun List<List<TileState>>.openAround(x: Int, y: Int) {
    // 左上
    if (x != 0 && y != 0 && !this[x - 1][y - 1].isOpened) {
        this[x - 1][y - 1].isOpened = true
    }
    // 真上
    if (x != 0 && !this[x - 1][y].isOpened) {
        this[x - 1][y].isOpened = true
        if (this[x - 1][y].numOfAroundMines == 0) {
            this.openAround(x - 1, y)
        }
    }
    // 右上
    if (x != 0 && this[x - 1].size > y + 1 && !this[x - 1][y + 1].isOpened) {
        this[x - 1][y + 1].isOpened = true
    }
    // 左
    if (y != 0 && !this[x][y - 1].isOpened) {
        this[x][y - 1].isOpened = true
        if (this[x][y - 1].numOfAroundMines == 0) {
            this.openAround(x, y - 1)
        }
    }
    // 右
    if (this[x].size > y + 1 && !this[x][y + 1].isOpened) {
        this[x][y + 1].isOpened = true
        if (this[x][y + 1].numOfAroundMines == 0) {
            this.openAround(x, y + 1)
        }
    }
    // 左下
    if (this.size > x + 1 && y != 0 && !this[x + 1][y - 1].isOpened) {
        this[x + 1][y - 1].isOpened = true
    }
    // 真下
    if (this.size > x + 1 && !this[x + 1][y].isOpened) {
        this[x + 1][y].isOpened = true
        if (this[x + 1][y].numOfAroundMines == 0) {
            this.openAround(x + 1, y)
        }
    }
    // 右下
    if (this.size > x + 1 && this[x + 1].size > y + 1 && !this[x + 1][y + 1].isOpened) {
        this[x + 1][y + 1].isOpened = true
    }
}

class MineSweeperLogic(val column: Int, val row: Int, numOfMines: Int, seed: Int) {
    val map: List<List<TileState>>
    var isGameOver by mutableStateOf(false)
    var isDevMode by mutableStateOf(false)

    init {
        val connectedList = mutableListOf<TileState>()
        for (i in 1..numOfMines) {
            connectedList.add(TileState(true))
        }

        while (connectedList.size <= column * row) {
            connectedList.add(TileState(false))
        }

        connectedList.shuffle(Random(seed))
        map = connectedList.windowed(column, column)

        for (x in 0 until row) {
            for (y in 0 until column) {
                if (map[x][y].isMine) {
                    map.incAround(x, y)
                }
            }
        }
    }

    fun openTile(x: Int, y: Int) {
        map[x][y].isOpened = true
        if (map[x][y].isMine && !isDevMode) isGameOver = true
        if (map[x][y].numOfAroundMines == 0) {
            map.openAround(x, y)
        }
    }
}
