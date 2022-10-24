package ktshsResearchAssignmentE2022.com.github.minesweeper

import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.SettingMenuButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.WindowState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.*
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)
        Style(BlackOutOverlayStyleSheet)
        Style(MinesweeperStyleSheet)
        Style(SidebarStyleSheet)
        Style(PhoneSidebarStyleSheet)

        window.onresize = {
            WindowState.width = window.outerWidth
            WindowState.isPhone = WindowState.width < 850
            it
        }

        if (MineSweeperState.logic.isGameOver || MineSweeperState.logic.isGameClear)
            Result()

        if (AppState.isHelpOpen)
            Help()

        if (WindowState.isPhone)
            PhoneMenuButtonLayout {
                if (AppState.isOpen) {
                    PhoneSidebar()
                } else {
                    SettingMenuButton {
                        AppState.isOpen = true
                    }
                }
            }

        MainLayout {
            if (!WindowState.isPhone) PCSidebar()
            CenterLayout {
                MineSweeper()
            }
        }
    }
}
