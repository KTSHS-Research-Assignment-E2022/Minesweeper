package ktshsResearchAssignmentE2022.com.github.minesweeper

import kotlin.random.Random

private fun List<List<TileLogic>>.incAround(int: Int, int2: Int) {
    // 左上
    if (int != 0 && int2 != 0) {
        if (! this[int - 1][int2 - 1].isBomb) this[int - 1][int2 - 1].nearbyMines++
    }
    // 真上
    if (int != 0) {
        if (!this[int - 1][int2].isBomb) this[int - 1][int2].nearbyMines++
    }
    // 右上
    if (int != 0 && this[int - 1].size > int2 + 1) {
        if (!this[int - 1][int2 + 1].isBomb) this[int - 1][int2 + 1].nearbyMines++
    }

    // 左
    if (int2 != 0) {
        if (!this[int][int2 - 1].isBomb) this[int][int2 - 1].nearbyMines++
    }
    // 右
    if (this[int].size > int2 + 1) {
        if (!this[int][int2 + 1].isBomb) this[int][int2 + 1].nearbyMines++
    }

    // 左下
    if (this.size > int + 1 && int2 != 0) {
        if (!this[int + 1][int2 - 1].isBomb) this[int + 1][int2 - 1].nearbyMines++
    }
    // 真下
    if (this.size > int + 1) {
        if (!this[int + 1][int2].isBomb) this[int + 1][int2].nearbyMines++
    }
    // 右下
    if (this.size > int + 1 && this[int + 1].size > int2 + 1) {
        if (!this[int + 1][int2 + 1].isBomb) this[int + 1][int2 + 1].nearbyMines++
    }
}

class MineSweeperLogic(column: Int, row: Int, ratio: Int, seed: Int) {
    val map: List<List<TileLogic>>

    init {
        val connectedList = mutableListOf<TileLogic>()
        for (i in 1 .. ratio) {
            connectedList.add(TileLogic(true))
        }

        while (connectedList.size <= column * row) {
            connectedList.add(TileLogic(false))
        }

        connectedList.shuffle(Random(seed))
        map = connectedList.windowed(column, column)

        for (x in 0 until row) {
            for (y in 0 until column) {
                if (map[x][y].isBomb) {
                    map.incAround(x, y)
                }
            }
        }
    }
}
