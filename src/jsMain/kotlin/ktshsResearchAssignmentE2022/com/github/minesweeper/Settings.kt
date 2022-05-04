package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.GrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.SidebarStyleSheet
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.max
import org.jetbrains.compose.web.attributes.min
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlin.random.Random

@Composable
fun Settings() {
    var showAdvancedSettings by mutableStateOf(false)
    Div({
        classes(SidebarStyleSheet.settingContainerStyle)
    }) {
        if (showAdvancedSettings) {
            AdvancedSettings()
        } else {
            SimpleSettings()
        }
        Button({
            style {
                width(100.percent)
                backgroundColor(Color.white)
            }
            onClick { showAdvancedSettings = !showAdvancedSettings }
        }) {
            Text("詳細設定を${if (showAdvancedSettings) "とじる" else "ひらく"}")
        }
    }
}

@Composable
fun SimpleSettings() {
    var difficulty by mutableStateOf(Difficulty.Easy)
    Div({
        style {
            height(100.percent)
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            alignContent(AlignContent.SpaceBetween)
        }
    }) {
        H2 {
            Text("むずかしさ")
        }

        GrowingButton("かんたん", difficulty == Difficulty.Easy) {
            difficulty = Difficulty.Easy
            SettingState.reset()
            MineSweeper.regenerate()
        }

        GrowingButton("ふつう", difficulty == Difficulty.Normal) {
            difficulty = Difficulty.Normal
            MineSweeper.regenerate()
        }

        GrowingButton("むずかしい", difficulty == Difficulty.Hard) {
            difficulty = Difficulty.Hard
            MineSweeper.regenerate()
        }
    }
}

private enum class Difficulty {
    Easy, Normal, Hard
}

@Composable
fun AdvancedSettings() {
    Div({
        style {
            height(100.percent)
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            alignContent(AlignContent.SpaceBetween)
        }
    }) {
        H2 {
            Text("詳細設定")
        }
        GrowingButton("再生成する", true) {
            MineSweeper.regenerate()
        }
        GrowingButton(
            "デフォルトにもどす",
            !(SettingState.column == 9 && SettingState.row == 9 && SettingState.numOfMines == 12),
            10.percent
        ) {
            SettingState.reset()
        }

        Div({
            style {
                height(50.percent)
                width(90.percent)
                backgroundColor(Color.gainsboro)
                borderRadius(20.px)
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
            }
        }) {
            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
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

            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
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

            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
                P { Text("地雷の数: ${SettingState.numOfMines}個") }
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

            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
                P({
                    style {
                        width(100.percent)
                        textAlign("center")
                    }
                }) { Text("シード値") }
                Button({
                    style {
                        width(30.percent)
                        backgroundColor(Color.white)
                        borderRadius(10.px)
                    }
                    onClick {
                        SettingState.seed = Random.nextInt()
                    }
                }) {
                    Text("ランダム")
                }
                Input(InputType.Number) {
                    style {
                        width(60.percent)
                        backgroundColor(Color.white)
                        borderRadius(10.px)
                        textAlign("center")
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
}
