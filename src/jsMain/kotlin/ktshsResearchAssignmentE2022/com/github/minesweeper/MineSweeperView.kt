package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSquareState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperViewState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.NormalSquareState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import kotlin.math.sqrt

@Composable
fun MineSweeper() {
    MinesweeperLayout {
        for (x in 0 until MineSweeperViewState.logic.xLength) {
            for (y in 0 until MineSweeperViewState.logic.yLength) {
                Square(MineSweeperViewState.logic, x, y)
            }
        }
    }
}

@Composable
private fun Square(logic: MineSweeperLogic, x: Int, y: Int) {
    val squareState = logic.board[x][y]
    Div({
        id("tile-$x-$y")
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
                    squareState.isOpened -> {
                        if (squareState is NormalSquareState) {
                            when (squareState.numOfAroundMines) {
                                // 色は安全→危険で　青→黄→赤
                                0 -> Color.whitesmoke
                                1 -> Color.cornflowerblue
                                2 -> Color.khaki
                                3 -> Color.lightcoral
                                else -> Color.mediumorchid
                            }
                        } else {
                            Color.crimson
                        }
                    }

                    squareState.isFlagged -> Color.mediumseagreen
                    else -> Color.white
                }
            )

            val mineFontSize = if (logic.xLength < 14) 5.vmin else 3.vmin
            val commonFontSize = (sqrt(20.0 - logic.xLength) + 2.2).vmin
            fontSize(
                if (squareState.isOpened && squareState is MineSquareState)
                    mineFontSize
                else
                    commonFontSize
            )

            if (!squareState.isOpened && !squareState.isFlagged) {
                border {
                    style = LineStyle.Solid
                    this.width = 90.vmin / logic.xLength / 30
                    color = rgb(180 - x * y * 5, 205 - x * y, 250 - x * y)
                }
            }
        }

        onContextMenu {
            //右クリ時の挙動
            if (!squareState.isOpened) {
                logic.toggleTileFlag(x, y)
                org.w3c.dom.Audio("./sounds/flag.mp3").play()
            }
            // 右クリメニューをキャンセル
            it.nativeEvent.preventDefault()
        }
        onClick {
            if (AppState.isFlagMode)
                logic.toggleTileFlag(x, y)
            else
                logic.openTileWithAround(x, y)

            if (squareState is MineSquareState) {
                org.w3c.dom.Audio("./sounds/gameover.mp3").play()
            } else {
                org.w3c.dom.Audio("./sounds/flag.mp3").play()
            }
        }
    }) {
        Text(
            if (squareState.isOpened) {
                if (squareState is NormalSquareState) {
                    if (squareState.numOfAroundMines == 0)
                        ""
                    else
                        squareState.numOfAroundMines.toString()
                } else {
                    "💣"
                }
            } else if (squareState.isFlagged) {
                "🚩"
            } else {
                ""
            }
        )
    }

}
