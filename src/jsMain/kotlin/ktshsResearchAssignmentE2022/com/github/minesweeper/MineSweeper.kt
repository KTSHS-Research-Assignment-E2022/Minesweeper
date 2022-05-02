package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

object MineSweeper {
    var logic by mutableStateOf(
        MineSweeperLogic(
            SettingState.column,
            SettingState.row,
            SettingState.numOfMines,
            SettingState.seed
        )
    )

    fun regenerate() {
        logic = MineSweeperLogic(SettingState.column, SettingState.row, SettingState.numOfMines, SettingState.seed)
    }

    @Composable
    fun show() {
        MinesweeperLayout {
            for (i in 0 until logic.row) {
                // 配列は0から!!!!!
                TileRow(logic.map[i], i)
            }
        }
    }

    @Composable
    private fun TileRow(row: List<TileState>, numOfColumn: Int) {
        for (i in 0 until logic.column) {
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
            onContextMenu {
                //右クリ時の挙動
                if (!tileState.isOpened) {
                    tileState.isFlagged = !tileState.isFlagged
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            onClick {
                if(tileState.isFlagged) return@onClick
                logic.openTile(column, row)
                if (tileState.isMine) logic.isGameOver = true
            }
        }) {
            Text(
                if (tileState.isOpened && tileState.numOfAroundMines != 0) {
                    if (tileState.isMine) "💣" else tileState.numOfAroundMines.toString()
                } else {
                    ""
                }
            )
        }

    }
}
