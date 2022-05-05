package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.ScoreBoardStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import kotlin.random.Random

@Composable
fun ScoreBoard() {
    Div({
        classes(ScoreBoardStyleSheet.ScoreBoardStyle)
    }) {
        if (MineSweeper.logic.isGameOver)
            Result("Game Over")
        else if (MineSweeper.logic.isGameClear)
            Result("Game Clear")
        else Result("Error: Is dev mode?")
        Button({
            style {
                height(20.percent)
                width(70.percent)
                borderRadius(2.vmin)
                backgroundColor(Color.white)
                outlineColor(Color.black)
            }
            onClick {
                SettingState.seed = Random.nextInt()
                MineSweeper.regenerate()
            }
        }) {
            Text("新しい盤面でプレイする")
        }

        Button({
            style {
                height(20.percent)
                width(70.percent)
                borderRadius(2.vmin)
                backgroundColor(Color.white)
                outlineColor(Color.black)
            }
            onClick {
                MineSweeper.regenerate()
            }
        }) {
            Text("もう一度この盤面をプレイする")
        }
    }
}

@Composable
fun Result(text: String) {
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
