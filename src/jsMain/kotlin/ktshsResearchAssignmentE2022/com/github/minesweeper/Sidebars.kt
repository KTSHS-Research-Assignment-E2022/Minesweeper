package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.OnHoverGrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperViewState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun PCSidebar() {
    Div({
        classes(SidebarStyleSheet.pcSidebarStyle)
    }) {
        Style({
            classes(SidebarStyleSheet.elementStyle)
        }) {
            H1 { Text(if (!MineSweeperViewState.logic.isDevMode) "まいんすいーぱー" else "Dev Mode") }
            Settings()
            OnHoverGrowingButton("遊び方", 10.percent, 90.percent, true) {
                AppState.isHelpOpen = true
            }
            Button({
                onClick {
                    MineSweeperViewState.logic.isDevMode = !MineSweeperViewState.logic.isDevMode
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
                    fontSize(4.8.vw)
                }
            }) {
                Text(if (!MineSweeperViewState.logic.isDevMode) "まいんすいーぱー" else "Dev Mode")
            }

            Settings()

            OnHoverGrowingButton("閉じる", 10.percent, 90.percent, true) {
                AppState.isSidebarOpen = false
            }

            Button({
                onClick {
                    MineSweeperViewState.logic.isDevMode = !MineSweeperViewState.logic.isDevMode
                }
            }) {
                P({
                    style {
                        width(100.percent)
                    }
                }) {
                    Text("Made by")
                }
                P({
                    style {
                        width(100.percent)
                    }
                }) {
                    Text("神奈川工業高校電気科")
                }
            }
        }
    }
}
