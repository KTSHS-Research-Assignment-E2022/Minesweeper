package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
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
            Text("???????????????${if (showAdvancedSettings) "?????????" else "?????????"}")
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
        if (hasUpdate) {
            // ????????????????????????
            GrowingButton("?????????????????????????????????", true) {
                window.navigator.serviceWorker.getRegistrations().then {
                    for (registration in it) {
                        registration.unregister()
                        registration.update()
                    }
                    hasUpdate = false
                }
                window.location.href = window.location.href
            }
        }

        H2 {
            Text("???????????????")
        }

        GrowingButton("????????????", SettingState.difficulty == Difficulty.Easy) {
            SettingState.setWithDifficulty(Difficulty.Easy)
            MineSweeper.regenerate()
        }

        GrowingButton("?????????", SettingState.difficulty == Difficulty.Normal) {
            SettingState.setWithDifficulty(Difficulty.Normal)
            MineSweeper.regenerate()
        }

        GrowingButton("???????????????", SettingState.difficulty == Difficulty.Hard) {
            SettingState.setWithDifficulty(Difficulty.Hard)
            MineSweeper.regenerate()
        }
    }
}

@Composable
private fun AdvancedSettings() {
    val notIsDiff = !(SettingState.yLength == MineSweeper.logic.yLength &&
            SettingState.xLength == MineSweeper.logic.xLength &&
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
            Text("????????????")
        }
        GrowingButton("???????????????", notIsDiff) {
            if (notIsDiff) MineSweeper.regenerate()
        }
        GrowingButton("???????????????????????????", notIsDiff, 10.percent) {
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
                P { Text("????????????: ${SettingState.yLength}") }
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

// ???????????????????????????????????????
//            Div({
//                classes(SidebarStyleSheet.settingElementStyle)
//            }) {
//                P { Text("?????????: ${SettingState.yLength}") }
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
//                P { Text("?????????: ${SettingState.xLength}") }
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
                P { Text("????????????: ${SettingState.numOfMines}???") }
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
                }) { Text("????????????") }
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
                    Text("????????????")
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
                            // XXX_VALUE???Int?????????????????????????????????????????????
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
