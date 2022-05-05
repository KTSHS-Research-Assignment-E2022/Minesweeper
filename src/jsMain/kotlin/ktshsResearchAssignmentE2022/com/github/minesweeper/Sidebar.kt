package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.dom.*

@Composable
fun Sidebar(width: Int) {
    if (width > 600) {
        // PC
        PcSidebar()
    } else {
        // 画面幅 749px以下の端末
        PhoneSidebar()
    }
}

@Composable
private fun PcSidebar() {
    Div({
        classes(SidebarStyleSheet.pcSidebarStyle)
    }) {
        Style({
            classes(SidebarStyleSheet.elementStyle)
        }) {
            H1 { Text(if (!MineSweeper.logic.isDevMode) "まいんすいーぱー" else "Dev Mode") }
            Settings()
            Button({
                onClick {
                    MineSweeper.logic.isDevMode = !MineSweeper.logic.isDevMode
                }
            }) {
                H3({
                    style {
                        textAlign("left")
                    }
                }) { Text("Made by 神奈川工業高校電気科") }
            }
        }
    }
}

@Composable
private fun PhoneSidebar() {
    Div({
        classes(PhoneSidebarStyleSheet.menuButtonStyle)
    }) { Text("スマホは横画面でプレイしてね") }
}
