package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.OnHoverGrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.ResultStyleSheet
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
    Div({
        classes(ResultStyleSheet.ResultStyle)
    }) {
        if (MineSweeper.logic.isGameOver)
            ResultTitle("Game Over")
        else if (MineSweeper.logic.isGameClear) {
            ResultTitle("🎉Game Clear🎉")
            ResultTime("Clear Time: ${MineSweeper.logic.getElapsedSeconds()}秒")
        } else ResultTitle("Error: Is dev mode?")

        OnHoverGrowingButton("新しい盤面でプレイする") {
            SettingState.seed = Random.nextInt()
            MineSweeper.regenerate()
        }

        OnHoverGrowingButton("もう一度この盤面をプレイする") {
            MineSweeper.regenerate()
        }
    }
}

@Composable
fun ResultTitle(text: String) {
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
fun ResultTime(text: String) {
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
