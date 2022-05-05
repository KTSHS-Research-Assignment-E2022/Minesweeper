package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.*
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)
        Style(ScoreBoardStyleSheet)
        Style(MinesweeperStyleSheet)
        Style(SidebarStyleSheet)
        Style(PhoneSidebarStyleSheet)

        val outerWidth = mutableStateOf(window.outerWidth)

        window.onresize = {
            outerWidth.value = window.outerWidth
            // なんかitが必要っぽい(なぜ？)
            it
        }

        if (MineSweeper.logic.isGameOver || MineSweeper.logic.isGameClear) ScoreBoardContainerLayout { ScoreBoard() }
        MainLayout {
            Sidebar(outerWidth.value)
            CenterLayout {
                MineSweeper.show()
            }
        }
    }
}
