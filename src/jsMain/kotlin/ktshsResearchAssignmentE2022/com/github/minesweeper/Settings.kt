package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.max
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.*
import kotlin.random.Random

@Composable
fun Settings() {
    Div({
        classes(SidebarStyleSheet.settingStyle)
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
                onClick {
                    MineSweeper.regenerate()
                }
            }) {
                Text("再生成")
            }
        }

        Div {
            P { Text("縦の幅: ${SettingState.column}") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(SettingState.column)
                onInput {
                    SettingState.column = it.value as Int
                    if (SettingState.numOfMines > SettingState.column * SettingState.row) SettingState.numOfMines =
                        SettingState.column * SettingState.row
                }
            }
        }

        Div {
            P { Text("横の幅: ${SettingState.row}") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(SettingState.row)
                onInput {
                    SettingState.row = it.value as Int
                    if (SettingState.numOfMines > SettingState.column * SettingState.row) SettingState.numOfMines =
                        SettingState.column * SettingState.row
                }
            }
        }

        Div {
            P { Text("爆弾の個数: ${SettingState.numOfMines}個") }
            Input(InputType.Range) {
                style {
                    max((SettingState.column * SettingState.row).toString())
                    min("0")
                    width(90.percent)
                }
                value(SettingState.numOfMines)
                onInput {
                    SettingState.numOfMines = it.value as Int
                }
            }
        }

        Div {
            Text("シード値")
            Button({
                style {
                    width(50.percent)
                    backgroundColor(Color.whitesmoke)
                }
                onClick {
                    SettingState.seed = Random.nextInt()
                }
            }) {
                Text("ランダムな値")
            }
            Input(InputType.Number) {
                style {
                    width(90.percent)
                }
                value(SettingState.seed)
                onInput {
                    SettingState.seed = when {
                        // XXX_VALUEはIntの最大値と最小値を保持する定数
                        it.value as Int >= Int.MAX_VALUE -> Int.MAX_VALUE
                        it.value as Int <= Int.MIN_VALUE -> Int.MIN_VALUE
                        else -> it.value as Int
                    }
                }
            }
        }
    }
}
