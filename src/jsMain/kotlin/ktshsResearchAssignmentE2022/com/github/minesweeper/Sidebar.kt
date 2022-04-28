package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.PhoneSidebarStyleSheet
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.max
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.*
import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE
import kotlin.random.Random

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
            H1 { Text("まいんすいーぱー") }
            Settings()
            H3 { Text("Made by 神奈川工業高校電気科") }
        }
    }
}

@Composable
private fun PhoneSidebar() {
    Div({
        classes(PhoneSidebarStyleSheet.menuButtonStyle)
    }) { Text("スマホは横画面でプレイしてね") }
}

@Composable
private fun Settings() {
    val column = mutableStateOf(9)
    val row = mutableStateOf(9)
    val numOfMines = mutableStateOf(12)
    val seed = mutableStateOf(Random.nextInt())

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
                    mineSweeper.value =
                        MineSweeper(
                            column.value, row.value,
                            numOfMines.value,
                            seed.value
                        )
                }
            }) {
                Text("再生成")
            }
        }

        Div {
            P { Text("縦の幅: ${column.value}") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(column.value)
                onInput {
                    column.value = it.value as Int
                }
            }
        }

        Div {
            P { Text("横の幅: ${row.value}") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(row.value)
                onInput {
                    row.value = it.value as Int
                }
            }
        }

        Div {
            P { Text("爆弾の個数: ${numOfMines.value}個") }
            Input(InputType.Range) {
                style {
                    max("100")
                    min("0")
                    width(90.percent)
                }
                value(numOfMines.value)
                onInput {
                    numOfMines.value = it.value as Int
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
                    seed.value = Random.nextInt()
                }
            }) {
                Text("ランダムな値")
            }
            Input(InputType.Number) {
                style {
                    width(90.percent)
                }
                value(seed.value)
                onInput {
                    seed.value = when {
                        // XXX_VALUEはIntの最大値と最小値を保持する定数
                        it.value as Int >= MAX_VALUE -> MAX_VALUE
                        it.value as Int <= MIN_VALUE -> MIN_VALUE
                        else -> it.value as Int
                    }
                }
            }
        }
    }
}
