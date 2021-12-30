package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.AppStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PcSidebarStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
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
        classes(PcSidebarStyleSheet.pcSidebarStyle)
    }) {
        Style({
            classes(PcSidebarStyleSheet.elementStyle)
        }) {
            H1 { Text("まいんすいーぱー") }
            Settings()
            H3 { Text("Made by 神奈川工業高校電気科") }
        }
    }
}

@Composable
private fun PhoneSidebar() {
    Div ({
        classes(PhoneSidebarStyleSheet.menuButtonStyle)
    }){ Text("スマホは横画面でプレイしてね") }
}

@Composable
private fun Settings() {
    Div({
        classes(PcSidebarStyleSheet.settingStyle)
    }) {
        H2 {
            Text("設定")
        }

        Div {
            Button({
                style {
                    width(100.percent)
                    backgroundColor(Color.whitesmoke)
                }
            }) {
                Text("再生成")
            }
        }

        Div {
            Text("縦の幅")
            Input(InputType.Range)
        }

        Div {
            Text("横の幅")
            Input(InputType.Range)
        }

        Div {
            Text("爆弾の数")
            Input(InputType.Number,
                attrs = {
                    style {
                        backgroundColor(Color.whitesmoke)
                    }
                })
        }
    }
}
