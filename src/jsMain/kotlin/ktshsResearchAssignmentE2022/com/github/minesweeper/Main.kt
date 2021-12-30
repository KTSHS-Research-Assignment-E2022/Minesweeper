package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.AppStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PcSidebarStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)
        Style(MinesweeperStyleSheet)
        Style(PcSidebarStyleSheet)
        Style(PhoneSidebarStyleSheet)

        val outerWidth = mutableStateOf(window.outerWidth)

        window.onresize = {
            outerWidth.value = window.outerWidth
            // なんかitが必要っぽい(なぜ？)
            it
        }

        MainLayout {
            Sidebar(outerWidth.value)
            CenterLayout {
                MineSweeper(9, 9, 9).Content()
            }
        }
    }
}
