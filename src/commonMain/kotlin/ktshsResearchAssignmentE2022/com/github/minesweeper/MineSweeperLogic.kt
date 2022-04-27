package ktshsResearchAssignmentE2022.com.github.minesweeper

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
    // 真上
    if (x != 0) {
        if (this[x - 1][y].numOfAroundMines == 0 && !this[x - 1][y].isOpened.value) {
            this[x - 1][y].isOpened.value = true
            this.openAround(x - 1, y)
        }
    }
    // 左
    if (y != 0) {
        if (this[x][y - 1].numOfAroundMines == 0 && !this[x][y - 1].isOpened.value) {
            this[x][y - 1].isOpened.value = true
            this.openAround(x, y - 1)
        }
    }
    // 右
    if (this[x].size > y + 1) {
        if (this[x][y + 1].numOfAroundMines == 0 && !this[x][y + 1].isOpened.value) {
            this[x][y + 1].isOpened.value = true
            this.openAround(x, y + 1)
        }
    }
    // 真下
    if (this.size > x + 1) {
        if (this[x + 1][y].numOfAroundMines == 0 && !this[x + 1][y].isOpened.value) {
            this[x + 1][y].isOpened.value = true
            this.openAround(x + 1, y)
        }
    }
}

class MineSweeperLogic(column: Int, row: Int, ratio: Int, seed: Int) {
    val map: List<List<TileState>>

    init {
        val connectedList = mutableListOf<TileState>()
        for (i in 1..ratio) {
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
        map[x][y].isOpened.value = true
        if (map[x][y].numOfAroundMines == 0) {
            map.openAround(x, y)
        }
    }
}
