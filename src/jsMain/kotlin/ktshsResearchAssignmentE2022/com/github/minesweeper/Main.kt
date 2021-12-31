package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.AppStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import kotlin.random.Random

val mineSweeper = mutableStateOf(MineSweeper(9, 9, 9, Random.nextInt()))
fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)
        Style(MinesweeperStyleSheet)
        Style(SidebarStyleSheet)
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
                mineSweeper.value.show()
            }
        }
    }
}
