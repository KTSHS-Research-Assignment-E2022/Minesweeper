package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.GrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.Timer
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.Difficulty
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.MineSweeperViewState
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.SettingState
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
                        SettingState.yLength == Difficulty.Easy.yLength &&
                                SettingState.xLength == Difficulty.Easy.xLength &&
                                SettingState.numOfMines == Difficulty.Easy.numOfMines -> Difficulty.Easy

                        SettingState.yLength == Difficulty.Normal.yLength &&
                                SettingState.xLength == Difficulty.Normal.xLength &&
                                SettingState.numOfMines == Difficulty.Normal.numOfMines -> Difficulty.Normal

                        SettingState.yLength == Difficulty.Hard.yLength &&
                                SettingState.xLength == Difficulty.Hard.xLength &&
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
            Text("経過時間")
        }

        Timer()

        H2 {
            Text("むずかしさ")
        }

        GrowingButton("かんたん", SettingState.difficulty == Difficulty.Easy) {
            SettingState.setWithDifficulty(Difficulty.Easy)
            MineSweeperViewState.regenerate()
        }

        GrowingButton("ふつう", SettingState.difficulty == Difficulty.Normal) {
            SettingState.setWithDifficulty(Difficulty.Normal)
            MineSweeperViewState.regenerate()
        }

        GrowingButton("むずかしい", SettingState.difficulty == Difficulty.Hard) {
            SettingState.setWithDifficulty(Difficulty.Hard)
            MineSweeperViewState.regenerate()
        }
    }
}

@Composable
private fun AdvancedSettings() {
    val notIsDiff = !(SettingState.yLength == MineSweeperViewState.logic.yLength &&
            SettingState.xLength == MineSweeperViewState.logic.xLength &&
            SettingState.numOfMines == MineSweeperViewState.logic.numOfMines &&
            SettingState.seed == MineSweeperViewState.logic.seed)

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
            if (notIsDiff) MineSweeperViewState.regenerate()
        }
        GrowingButton("現在の設定にもどす", notIsDiff, 10.percent) {
            if (notIsDiff) {
                SettingState.setAll(
                    MineSweeperViewState.logic.yLength,
                    MineSweeperViewState.logic.xLength,
                    MineSweeperViewState.logic.numOfMines,
                    MineSweeperViewState.logic.seed
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
                P { Text("盤面の幅: ${SettingState.yLength}") }
                Input(InputType.Range) {
                    max("20")
                    min("2")
                    style {
                        width(90.percent)
                    }
                    value(SettingState.yLength)
                    onInput {
                        SettingState.yLength = it.value as Int
                        SettingState.xLength = it.value as Int
                        if (SettingState.numOfMines > SettingState.yLength * SettingState.xLength) SettingState.numOfMines =
                            SettingState.yLength * SettingState.xLength - 1
                    }
                }
            }

// 暫定的に正方形のみにしてる
//            Div({
//                classes(SidebarStyleSheet.settingElementStyle)
//            }) {
//                P { Text("縦の幅: ${SettingState.yLength}") }
//                Input(InputType.Range) {
//                    style {
//                        max("20")
//                        min("2")
//                        width(90.percent)
//                    }
//                    value(SettingState.yLength)
//                    onInput {
//                        SettingState.yLength = it.value as Int
//                        if (SettingState.numOfMines > SettingState.yLength * SettingState.xLength) SettingState.numOfMines =
//                            SettingState.yLength * SettingState.xLength - 1
//                    }
//                }
//            }
//
//            Div({
//                classes(SidebarStyleSheet.settingElementStyle)
//            }) {
//                P { Text("横の幅: ${SettingState.xLength}") }
//                Input(InputType.Range) {
//                    style {
//                        max("20")
//                        min("2")
//                        width(90.percent)
//                    }
//                    value(SettingState.xLength)
//                    onInput {
//                        SettingState.xLength = it.value as Int
//                        if (SettingState.numOfMines > SettingState.yLength * SettingState.xLength) SettingState.numOfMines =
//                            SettingState.yLength * SettingState.xLength - 1
//                    }
//                }
//            }

            Div({
                classes(SidebarStyleSheet.settingElementStyle)
            }) {
                P { Text("地雷の数: ${SettingState.numOfMines}個") }
                Input(InputType.Range) {
                    max((SettingState.yLength * SettingState.xLength - 1).toString())
                    min("1")
                    style {
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
