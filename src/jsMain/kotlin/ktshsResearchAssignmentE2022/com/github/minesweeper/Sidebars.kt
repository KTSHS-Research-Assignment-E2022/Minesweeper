package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.OnHoverGrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.dom.*

@Composable
fun PCSidebar() {
    Div({
        classes(SidebarStyleSheet.pcSidebarStyle)
    }) {
        Style({
            classes(SidebarStyleSheet.elementStyle)
        }) {
            H1 { Text(if (!MineSweeperState.logic.isDevMode) "まいんすいーぱー" else "Dev Mode") }
            Settings()
            OnHoverGrowingButton("遊び方", height = 10.percent, width = 90.percent) {
                AppState.isHelpOpen = true
            }
            Button({
                onClick {
                    MineSweeperState.logic.isDevMode = !MineSweeperState.logic.isDevMode
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
fun PhoneSidebar() {
    Div({
        classes(SidebarStyleSheet.phoneSidebarStyle)
    }) {
        Style({
            classes(SidebarStyleSheet.elementStyle)
        }) {
            H1({
                style {
                    textAlign("center")
                }
            }) {
                Text(if (!MineSweeperState.logic.isDevMode) "まいんすいーぱー" else "Dev Mode")
            }

            Settings()

            OnHoverGrowingButton("閉じる", height = 10.percent, width = 90.percent) {
                AppState.isOpen = false
            }

            Button({
                onClick {
                    MineSweeperState.logic.isDevMode = !MineSweeperState.logic.isDevMode
                }
            }) {
                H3 { Text("Made by 神奈川工業高校電気科") }
            }
        }
    }
}
