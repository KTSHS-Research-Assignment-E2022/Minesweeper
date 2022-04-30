package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.*
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import kotlin.random.Random

var mineSweeper by mutableStateOf(MineSweeper(9, 9, 12, Random.nextInt()))
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

        MainLayout {
            if(mineSweeper.logic.isGameOver) ScoreBoard()
            Sidebar(outerWidth.value)
            CenterLayout {
                mineSweeper.show()
            }
        }
    }
}
