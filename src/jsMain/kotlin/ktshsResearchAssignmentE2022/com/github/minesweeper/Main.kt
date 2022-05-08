package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.SettingMenuButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.*
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

var isSidebarOpen by mutableStateOf(false)

fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)
        Style(ResultStyleSheet)
        Style(MinesweeperStyleSheet)
        Style(SidebarStyleSheet)
        Style(PhoneSidebarStyleSheet)

        val outerWidth = mutableStateOf(window.outerWidth)
        val visitorIsPhone = outerWidth.value < 850

        window.onresize = {
            outerWidth.value = window.outerWidth
            // なんかitが必要っぽい(なぜ？)
            it
        }

        if (MineSweeper.logic.isGameOver || MineSweeper.logic.isGameClear) ResultContainerLayout { Result() }

        if (visitorIsPhone) PhoneHamburgerButtonLayout {
            if (isSidebarOpen) {
                PhoneSidebar()
            } else {
                SettingMenuButton {
                    isSidebarOpen = true
                }
            }
        }

        MainLayout {
            if (!visitorIsPhone) PcSidebar()
            CenterLayout {
                MineSweeper.show()
            }
        }
    }
}
