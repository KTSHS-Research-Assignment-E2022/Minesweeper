package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun MineSweeper() {
    MinesweeperLayout {
        for (x in 0 until MineSweeperState.logic.xLength) {
            for (y in 0 until MineSweeperState.logic.yLength) {
                Tile(MineSweeperState.logic, x, y)
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
                    tileState.isOpened -> {
                        if (tileState.isMine)
                            Color.crimson
                        else
                            when (tileState.numOfAroundMines) {
                                // 色は安全→危険で　青→黄→赤
                                0 -> Color.whitesmoke
                                1 -> Color.cornflowerblue
                                2 -> Color.khaki
                                3 -> Color.lightcoral
                                else -> Color.mediumorchid
                            }
                    }

                    tileState.isFlagged -> Color.mediumseagreen
                    else -> Color.white
                }
            )

            val mineFontSize = if (logic.xLength < 14) 5.vmin else 3.vmin
            val commonFontSize = if (logic.xLength > 5) 3.vmin else 4.vmin
            fontSize(
                if (tileState.isOpened && tileState.isMine)
                    mineFontSize
                else
                    commonFontSize
            )

            if (!tileState.isOpened && !tileState.isFlagged) {
                border {
                    style = LineStyle.Solid
                    this.width = if (logic.xLength < 13) 3.px else 2.px
                    color = rgb(180 - x * y * 5, 205 - x * y, 250 - x * y)
                }
            }
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