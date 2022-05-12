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
            SettingState.xLength,
            SettingState.yLength,
            SettingState.numOfMines,
            SettingState.seed
        )
    )

    fun regenerate() {
        logic = MineSweeperLogic(SettingState.xLength, SettingState.yLength, SettingState.numOfMines, SettingState.seed)
    }

    @Composable
    fun show() {
        MinesweeperLayout {
            for (x in 0 until logic.xLength) {
                for (y in 0 until logic.yLength) {
                    Tile(logic, x, y)
                }
            }
        }
    }

    @Composable
    private fun Tile(logic: MineSweeperLogic, x: Int, y: Int) {
        val tileState = logic.map[x][y]
        Div({
            classes(MinesweeperStyleSheet.tileStyle)
            style {
                // 合計の width = 100/(x軸方向の長さ + マージン)
                // 合計の height = 100/(y軸方向の長さ + マージン)
                val width: Double = 100.0 / (logic.xLength + 1)
                val height: Double = 100.0 / (logic.yLength + 1)
                width(width.percent)
                height(height.percent)
                marginLeft((width / (logic.xLength + 1)).percent)
                marginTop((height / ((logic.yLength + 1) * 2)).percent)
                marginBottom((height / ((logic.yLength + 1) * 2)).percent)

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
                    logic.toggleTileFlag(x, y)
                }
                // 右クリメニューをキャンセル
                it.nativeEvent.preventDefault()
            }
            onClick {
                logic.openTileWithAround(x, y)
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
