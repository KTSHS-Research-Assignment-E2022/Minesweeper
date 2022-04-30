package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object ScoreBoardStyleSheet : StyleSheet() {
    val ScoreBoardStyle by style {
        position(Position.Fixed)
        top(50.percent)
        left(50.percent)
        backgroundColor(Color.cyan)
    }
}
