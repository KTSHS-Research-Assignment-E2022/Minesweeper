package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

class MineSweeper(private val column: Int, private val row: Int, ratio: Int, seed: Int) {
    val logic = MineSweeperLogic(column, row, ratio, seed)

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
    private fun TileRow(row: List<TileState>, numOfColumn: Int) {
        for (i in 0 until column) {
            Tile(row[i], i, numOfColumn)
        }
    }

    @Composable
    private fun Tile(tileState: TileState, row: Int, column: Int) {
        Div({
            classes(MinesweeperStyleSheet.tileStyle)
            style {
                backgroundColor(
                    when {
                        tileState.isOpened -> if (tileState.isMine) Color.red else Color.green
                        tileState.isFlagged -> Color.blue
                        else -> Color.brown
                    }
                )
                if (tileState.isMine) fontSize(5.vmin) else fontSize(3.vmin)
            }
            id("$row-$column")
            onContextMenu {
                //右クリ時の挙動
                if (!tileState.isOpened) {
                    tileState.isFlagged = !tileState.isFlagged
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            onClick {
                logic.openTile(column, row)
                if(tileState.isMine) logic.isGameOver = true
            }
        }) {
            Text(
                if (tileState.isOpened) {
                    if (tileState.isMine) "💣" else tileState.numOfAroundMines.toString()
                } else {
                    ""
                }
            )
        }

    }
}
