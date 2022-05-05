package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.vmin
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
                        tileState.isOpened -> if (tileState.isMine) Color.crimson else
                            when (tileState.numOfAroundMines) {
                                // 色は安全→危険で　青→黄→赤
                                0 -> Color.paleturquoise
                                1 -> Color.cornflowerblue
                                2 -> Color.khaki
                                3 -> Color.lightcoral
                                else -> Color.mediumorchid
                            }
                        tileState.isFlagged -> Color.mediumseagreen
                        else -> Color.whitesmoke
                    }
                )
                fontSize(
                    if (tileState.isOpened) {
                        if (tileState.isMine) 5.vmin else 3.vmin
                    } else 3.vmin
                )

            }
            onContextMenu {
                //右クリ時の挙動
                if (!tileState.isOpened) {
                    logic.toggleTileFlag(column,row)
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            onClick {
                if (tileState.isFlagged) return@onClick
                logic.openTileWithAround(column, row)
            }
        }) {
            Text(
                if (tileState.isOpened && tileState.numOfAroundMines != 0) {
                    if (tileState.isMine) "💣" else tileState.numOfAroundMines.toString()
                } else if (tileState.isFlagged) {
                    "🚩"
                } else {
                    ""
                }
            )
        }

    }
}
