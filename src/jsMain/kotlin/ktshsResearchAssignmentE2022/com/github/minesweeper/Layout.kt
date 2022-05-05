package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.AppStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.MinesweeperStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.ResultStyleSheet
import org.jetbrains.compose.web.dom.Div

@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Div({
        classes(AppStyleSheet.mainContainer)
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
fun ScoreBoardContainerLayout(content: @Composable () -> Unit) {
    Div({
        classes(ResultStyleSheet.ResultContainerStyle)
    }) {
        content()
    }
}
