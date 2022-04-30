package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.ScoreBoardStyleSheet
import org.jetbrains.compose.web.dom.*
import kotlin.random.Random

@Composable
fun ScoreBoard() {
    Div({
        classes(ScoreBoardStyleSheet.ScoreBoardStyle)
    }) {
        P { Text("スコア") }
        if (mineSweeper.logic.isGameOver) Result("Game Over") else Result("スコアだす")
        Button({
            onClick {
                SettingState.seed = Random.nextInt()
                mineSweeper =
                    MineSweeper(SettingState.column, SettingState.row, SettingState.numOfMines, SettingState.seed)
            }
        }) {
            Text("もう一度プレイする")
        }
    }
}

@Composable
fun Result(text: String) {
    Div {
        H3 { Text(text) }
    }
}
