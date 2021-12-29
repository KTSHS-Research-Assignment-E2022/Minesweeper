package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun Sidebar() {
    Div({
        classes(AppStyleSheet.sidebarPCStyle)
    }) {
        Style({
            classes(SidebarStyleSheet.elementStyle)
        }) {
            H1 { Text("まいんすいーぱー") }
            Settings()
            H3 { Text("Made by 神奈川工業高校電気科") }
        }
    }
}

@Composable
private fun Settings() {
    val elemStyle = SidebarStyleSheet.settingElementStyle
    Div({
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            justifyContent(JustifyContent.SpaceBetween)

            height(30.vh)
            backgroundColor(Color.gray)
        }
        // classes(SidebarStyleSheet.settingStyle)
    }) {
        H2 {
            Text("設定")
        }

        Div({
            classes(elemStyle)
        }) {
            Button({
                style {
                    width(100.percent)
                    backgroundColor(Color.whitesmoke)
                }
            }) {
                Text("再生成")
            }
        }

        Div({
            classes(elemStyle)
        }) {
            Text("縦の幅")
            Input(InputType.Range)
        }

        Div({
            classes(elemStyle)
        }) {
            Text("横の幅")
            Input(InputType.Range)
        }

        Div({
            classes(elemStyle)
        }) {
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
