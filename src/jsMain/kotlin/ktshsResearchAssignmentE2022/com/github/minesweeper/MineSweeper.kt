package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

class MineSweeper(private val column: Int, private val row: Int, ratio: Int) {
    private val logic = MineSweeperLogic(column, row, ratio)
    private val map = logic.map

    @Composable
    fun Content() {
        MinesweeperLayout {
            for (i in 0 until row) {
                // 配列は0から!!!!!
                TileRow(map[i], i)
            }
        }
    }

    @Composable
    private fun TileRow(row: MutableList<Int>, numOfColumn: Int) {
        for (i in 0 until column) {
            Tile(row[i], i, numOfColumn)
        }
    }

    @Composable
    private fun Tile(nearbyMines: Int, row: Int, column: Int) {
        val color = mutableStateOf(Color.brown)
        val text = mutableStateOf("")
        val flag = mutableStateOf(false)
        val isClicked = mutableStateOf(false)

        Div({
            classes(AppStyleSheet.tileStyle)
            style {
                // 合計のwidth = 100/column
                // 合計のheight = 100/row
                margin(0.55.percent)
                width(10.percent)
                height(10.percent)

                borderRadius(30.percent)
                backgroundColor(color.value)

                if (nearbyMines == -1) fontSize(5.vmin) else fontSize(3.vmin)
            }
            id("$row-$column")
            onContextMenu {
                //右クリ時の挙動
                if (!isClicked.value) {
                    flag.value = !flag.value
                    color.value = if (flag.value) Color.blue else Color.brown
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            if (nearbyMines == -1) {
                // 地雷時の挙動
                classes("mine")
                onClick {
                    color.value = Color.red
                    text.value = "💣"
                }
            } else {
                classes(nearbyMines.toString())
                // ただのマスの挙動
                onClick {
                    color.value = Color.green
                    text.value = nearbyMines.toString()
                }
            }
            onClick {
                isClicked.value = true
            }
        }) {
            Text(text.value)
        }

    }
}
