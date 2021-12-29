package ktshsResearchAssignmentE2022.com.github.minesweeper

import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable("root") {
        Style(AppStyleSheet)

        MainLayout {
            Sidebar()
            CenterLayout {
                MineSweeper(9, 9, 9).Content()
            }
        }
    }
}
