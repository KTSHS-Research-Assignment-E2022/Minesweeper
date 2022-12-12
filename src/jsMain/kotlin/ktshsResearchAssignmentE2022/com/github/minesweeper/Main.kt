package ktshsResearchAssignmentE2022.com.github.minesweeper

import kotlinx.browser.window
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.CircleButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.SettingMenuButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperViewState
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

        WindowState.width = window.outerWidth
        WindowState.isPhone = window.outerWidth < 850

        window.onresize = {
            WindowState.width = window.outerWidth
            WindowState.isPhone = window.outerWidth < 850
            it
        }

        WindowState.isPhone = window.outerWidth < 850

        if (MineSweeperViewState.logic.gameStatus == GameStatus.GameOver || MineSweeperViewState.logic.gameStatus == GameStatus.GameClear)
            Result()

        if (AppState.isHelpOpen)
            Help()

        if (WindowState.isPhone)
            PhoneMenuButtonLayout {
                if (AppState.isSidebarOpen) {
                    PhoneSidebar()
                } else {
                    SettingMenuButton {
                        AppState.isSidebarOpen = true
                    }
                    CircleButton("🚩", AppState.isFlagMode) {
                        AppState.isFlagMode = !AppState.isFlagMode
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
