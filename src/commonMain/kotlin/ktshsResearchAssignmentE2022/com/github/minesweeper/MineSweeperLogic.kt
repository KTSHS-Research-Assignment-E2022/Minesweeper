package ktshsResearchAssignmentE2022.com.github.minesweeper

import kotlin.random.Random

class MineSweeperLogic(private val column: Int, private val row: Int, ratio: Int,seed: Int) {
    private val originalMap: List<List<Int>>

    init {
        val connectedList = mutableListOf<Int>()
        for (i in 1 until ratio) {
            connectedList.add(-1)
        }

        while (connectedList.size <= column * row) {
            connectedList.add(0)
        }

        connectedList.shuffle(Random(seed))
        originalMap = connectedList.windowed(column, column)
    }

    private fun List<MutableList<Int>>.incAround(int: Int, int2: Int) {
        // 左上
        if (int != 0 && int2 != 0) {
            if (this[int - 1][int2 - 1] != -1) this[int - 1][int2 - 1]++
        }
        // 真上
        if (int != 0) {
            if (this[int - 1][int2] != -1) this[int - 1][int2]++
        }
        // 右上
        if (int != 0 && this[int - 1].size > int2 + 1) {
            if (this[int - 1][int2 + 1] != -1) this[int - 1][int2 + 1]++
        }

        // 左
        if (int2 != 0) {
            if (this[int][int2 - 1] != -1) this[int][int2 - 1]++
        }
        // 右
        if (this[int].size > int2 + 1) {
            if (this[int][int2 + 1] != -1) this[int][int2 + 1]++
        }

        // 左下
        if (this.size > int + 1 && int2 != 0) {
            if (this[int + 1][int2 - 1] != -1) this[int + 1][int2 - 1]++
        }
        // 真下
        if (this.size > int + 1) {
            if (this[int + 1][int2] != -1) this[int + 1][int2]++
        }
        // 右下
        if (this.size > int + 1 && this[int + 1].size > int2 + 1) {
            if (this[int + 1][int2 + 1] != -1) this[int + 1][int2 + 1]++
        }
    }

    val map: List<MutableList<Int>>
        get() {
            val gameMap = originalMap as List<MutableList<Int>>
            for (row in 0 until row) {
                for (column in 0 until column) {
                    if (gameMap[row][column] == -1) {
                        gameMap.incAround(row, column)
                    }
                }
            }
            println(gameMap)
            return gameMap
        }
}
