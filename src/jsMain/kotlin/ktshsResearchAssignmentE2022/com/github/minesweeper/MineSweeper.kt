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
            Tile(row[i], i, numOfColumn)
        }
    }

    @Composable
    private fun Tile(tileLogic: TileLogic, row: Int, column: Int) {
        val isOpened = mutableStateOf(tileLogic.isOpened)
        val isFlagged = mutableStateOf(tileLogic.isFlagged)
        val text = mutableStateOf("")

        Div({
            classes(MinesweeperStyleSheet.tileStyle)
            style {
                backgroundColor(
                    when {
                        isOpened.value -> if(tileLogic.isMine) Color.red else Color.green
                        isFlagged.value -> Color.blue
                        else -> Color.brown
                    }
                )
                if (tileLogic.isMine) fontSize(5.vmin) else fontSize(3.vmin)
            }
            id("$row-$column")
            onContextMenu {
                //右クリ時の挙動
                if (!isOpened.value) {
                    isFlagged.value = !isFlagged.value
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            if (tileLogic.isMine) {
                // 地雷時の挙動
                classes("mine")
                onClick {
                    text.value = "💣"
                }
            } else {
                classes(tileLogic.numOfAroundMines.toString())
                // ただのマスの挙動
                onClick {
                    text.value = tileLogic.numOfAroundMines.toString()
                }
            }
            onClick {
                isOpened.value = true
            }
        }) {
            Text(text.value)
        }

    }
}
