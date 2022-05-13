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

class MineSweeperLogic(val xLength: Int, val yLength: Int, val numOfMines: Int, val seed: Int) {
    // y軸方向に各マスの情報を格納している
    val map: List<List<TileState>>
    private val coordinatesOfOpened = mutableSetOf<Pair<Int, Int>>()
    private val coordinatesWithoutMines: Set<Pair<Int, Int>>
    var isGameOver by mutableStateOf(false)
    var isGameClear by mutableStateOf(false)
    var isDevMode by mutableStateOf(false)

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

        map = connectedList.windowed(yLength, yLength)

        for (x in 0 until xLength) {
            for (y in 0 until yLength) {
                coordinatesOfPlane.add(Pair(x, y))
                if (map[x][y].isMine) {
                    coordinatesOfMines.add(Pair(x, y))
                    map.incAround(x, y)
                }
            }
        }
        coordinatesOfPlane.removeAll(coordinatesOfMines)
        coordinatesWithoutMines = coordinatesOfPlane
    }

    fun openTileWithAround(x: Int, y: Int) {
        if(map[x][y].isFlagged) return
        openTile(x, y)
        if (map[x][y].numOfAroundMines == 0) {
            map.openAround(x, y)
        }
    }

    fun toggleTileFlag(x: Int, y: Int) {
        map[x][y].isFlagged = !map[x][y].isFlagged
    }

    private fun openTile(x: Int, y: Int) {
        if(map[x][y].isFlagged) return
        map[x][y].isOpened = true
        coordinatesOfOpened.add(Pair(x, y))
        isGameClear = coordinatesOfOpened == coordinatesWithoutMines
        if (map[x][y].isMine && !isDevMode) isGameOver = true
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
