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
            onClick {
                showAdvancedSettings = !showAdvancedSettings
                if (showAdvancedSettings) SettingState.difficulty = Difficulty.Manual else {
                    SettingState.difficulty = when {
                        SettingState.column == Difficulty.Easy.column &&
                                SettingState.row == Difficulty.Easy.row &&
                                SettingState.numOfMines == Difficulty.Easy.numOfMines -> Difficulty.Easy

                        SettingState.column == Difficulty.Normal.column &&
                                SettingState.row == Difficulty.Normal.row &&
                                SettingState.numOfMines == Difficulty.Normal.numOfMines -> Difficulty.Normal

                        SettingState.column == Difficulty.Hard.column &&
                                SettingState.row == Difficulty.Hard.row &&
                                SettingState.numOfMines == Difficulty.Hard.numOfMines -> Difficulty.Hard

                        else -> Difficulty.Manual
                    }
                }
            }
        }) {
            Text("詳細設定を${if (showAdvancedSettings) "とじる" else "ひらく"}")
        }
    }
}

@Composable
private fun SimpleSettings() {
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

        GrowingButton("かんたん", SettingState.difficulty == Difficulty.Easy) {
            SettingState.setWithDifficulty(Difficulty.Easy)
            MineSweeper.regenerate()
        }

        GrowingButton("ふつう", SettingState.difficulty == Difficulty.Normal) {
            SettingState.setWithDifficulty(Difficulty.Normal)
            MineSweeper.regenerate()
        }

        GrowingButton("むずかしい", SettingState.difficulty == Difficulty.Hard) {
            SettingState.setWithDifficulty(Difficulty.Hard)
            MineSweeper.regenerate()
        }
    }
}

@Composable
private fun AdvancedSettings() {
    val notIsDiff = !(SettingState.column == MineSweeper.logic.yLength &&
            SettingState.row == MineSweeper.logic.xLength &&
            SettingState.numOfMines == MineSweeper.logic.numOfMines &&
            SettingState.seed == MineSweeper.logic.seed)

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
        GrowingButton("再生成する", notIsDiff) {
            if (notIsDiff) MineSweeper.regenerate()
        }
        GrowingButton("現在の設定にもどす", notIsDiff, 10.percent) {
            if (notIsDiff) {
                SettingState.setAll(
                    MineSweeper.logic.yLength,
                    MineSweeper.logic.xLength,
                    MineSweeper.logic.numOfMines,
                    MineSweeper.logic.seed
                )
            }
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
                            SettingState.column * SettingState.row - 1
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
                            SettingState.column * SettingState.row - 1
                    }
                }
            }

            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
                P { Text("地雷の数: ${SettingState.numOfMines}個") }
                Input(InputType.Range) {
                    style {
                        max((SettingState.column * SettingState.row - 1).toString())
                        min("1")
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
