package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

class MineSweeper(private val column: Int, private val row: Int, ratio: Int, seed: Int) {
    private val logic = MineSweeperLogic(column, row, ratio, seed)

    private val map = logic.map

    @Composable
    fun show() {
        MinesweeperLayout {
            for (i in 0 until row) {
                // 配列は0から!!!!!
                TileRow(map[i], i)
            }
        }
    }

    @Composable
    private fun TileRow(row: List<TileLogic>, numOfColumn: Int) {
        for (i in 0 until column) {
            Tile(row[i].numOfAroundMines, row[i].isMine,i, numOfColumn)
        }
    }

    @Composable
    private fun Tile(numOfAroundMines: Int, isMine: Boolean, row: Int, column: Int) {
        val color = mutableStateOf(Color.brown)
        val text = mutableStateOf("")
        val flag = mutableStateOf(false)
        val isClicked = mutableStateOf(false)

        Div({
            classes(MinesweeperStyleSheet.tileStyle)
            style {
                backgroundColor(color.value)
                if (isMine) fontSize(5.vmin) else fontSize(3.vmin)
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
            if (isMine) {
                // 地雷時の挙動
                classes("mine")
                onClick {
                    color.value = Color.red
                    text.value = "💣"
                }
            } else {
                classes(numOfAroundMines.toString())
                // ただのマスの挙動
                onClick {
                    color.value = Color.green
                    text.value = numOfAroundMines.toString()
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
