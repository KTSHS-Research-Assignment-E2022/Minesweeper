package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.ScoreBoardStyleSheet
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun ScoreBoard() {
    Div({
        classes(ScoreBoardStyleSheet.ScoreBoardStyle)
    }) {
        P { Text("スコア") }
        if (mineSweeper.logic.isGameOver) Result("Game Over") else Result("スコアだす")
    }
}

@Composable
fun Result(text: String) {
    Div {
        H3 { Text(text) }
    }
}
