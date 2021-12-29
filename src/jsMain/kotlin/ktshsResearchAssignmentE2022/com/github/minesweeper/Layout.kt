package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div

@Composable
fun MainLayout(content: @Composable () -> Unit) {
    Div(attrs = {
        classes(AppStyleSheet.mainContainer)
    }) {
        content()
    }
}

@Composable
fun CenterLayout(content: @Composable () -> Unit) {
    Div ({
        classes(AppStyleSheet.centerContainer)
    }){
        content()
    }
}

@Composable
fun MinesweeperLayout(content: @Composable () -> Unit) {
    Div(attrs = {
        classes(MinesweeperStyleSheet.minesweeperContainer)
    }) {
        content()
    }
}
