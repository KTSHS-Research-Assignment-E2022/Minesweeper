package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var column by mutableStateOf(9)
    var row by mutableStateOf(9)
    var numOfMines by mutableStateOf(12)
    var seed by mutableStateOf(Random.nextInt())

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
                    mineSweeper = MineSweeper(column, row, numOfMines, seed)
                }
            }) {
                Text("再生成")
            }
        }

        Div {
            P { Text("縦の幅: $column") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(column)
                onInput {
                    column = it.value as Int
                    if (numOfMines > column * row) numOfMines = column * row
                }
            }
        }

        Div {
            P { Text("横の幅: $row") }
            Input(InputType.Range) {
                style {
                    max("20")
                    min("2")
                    width(90.percent)
                }
                value(row)
                onInput {
                    row = it.value as Int
                    if (numOfMines > column * row) numOfMines = column * row
                }
            }
        }

        Div {
            P { Text("爆弾の個数: ${numOfMines}個") }
            Input(InputType.Range) {
                style {
                    max((column * row).toString())
                    min("0")
                    width(90.percent)
                }
                value(numOfMines)
                onInput {
                    numOfMines = it.value as Int
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
                    seed = Random.nextInt()
                }
            }) {
                Text("ランダムな値")
            }
            Input(InputType.Number) {
                style {
                    width(90.percent)
                }
                value(seed)
                onInput {
                    seed = when {
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
