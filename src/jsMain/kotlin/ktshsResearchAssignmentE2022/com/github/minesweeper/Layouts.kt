package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.AppStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.BlackOutOverlayStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import org.jetbrains.compose.web.dom.Div

@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Div({
        classes(AppStyleSheet.mainContainer)
        onContextMenu {
            // キャンセル
            it.preventDefault()
        }
    }) {
        content()
    }
}

@Composable
fun CenterLayout(content: @Composable () -> Unit) {
    Div({
        classes(AppStyleSheet.centerContainer)
    }) {
        content()
    }
}

@Composable
fun MinesweeperLayout(content: @Composable () -> Unit) {
    Div({
        classes(MinesweeperStyleSheet.minesweeperContainer)
    }) {
        content()
    }
}

@Composable
fun PhoneMenuButtonLayout(content: @Composable () -> Unit) {
    Div({
        classes(PhoneSidebarStyleSheet.SettingMenuButtonContainerStyle)
        onContextMenu {
            it.preventDefault()
        }
    }) {
        content()
    }
}

@Composable
fun BlackOutOverlayLayout(content: @Composable () -> Unit) {
    Div({
        classes(BlackOutOverlayStyleSheet.BlackOutOverlayContainerStyle)
        onContextMenu {
            // キャンセル
            it.preventDefault()
        }
    }) {
        content()
    }
}
