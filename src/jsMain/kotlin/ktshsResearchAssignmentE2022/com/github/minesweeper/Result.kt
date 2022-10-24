package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.OnHoverGrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.BlackOutOverlayStyleSheet
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import kotlin.random.Random

@Composable
fun Result() {
    BlackOutOverlayLayout {
        Div({
            classes(BlackOutOverlayStyleSheet.ResultStyle)
        }) {
            if (MineSweeperState.logic.isGameOver)
                ResultTitle("Game Over")
            else if (MineSweeperState.logic.isGameClear) {
                ResultTitle("🎉Game Clear🎉")
                ResultTime("Clear Time: ${MineSweeperState.logic.getElapsedSeconds()}秒")
            } else ResultTitle("Error: Is it dev mode?")

            OnHoverGrowingButton("新しい盤面でプレイする") {
                SettingState.seed = Random.nextInt()
                MineSweeperState.regenerate()
            }

            OnHoverGrowingButton("もう一度この盤面をプレイする") {
                MineSweeperState.regenerate()
            }
        }
    }
}

@Composable
private fun ResultTitle(text: String) {
    Div({
        style {
            width(100.percent)
        }
    }) {
        H1({
            style {
                textAlign("center")
            }
        }) { Text(text) }
    }
}

@Composable
private fun ResultTime(text: String) {
    Div({
        style {
            width(100.percent)
        }
    }) {
        H2({
            style {
                textAlign("center")
            }
        }) { Text(text) }
    }
}
