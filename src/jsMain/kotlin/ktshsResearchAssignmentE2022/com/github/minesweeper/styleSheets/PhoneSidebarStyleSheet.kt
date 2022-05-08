package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object PhoneSidebarStyleSheet : StyleSheet() {
    val SettingMenuButtonContainerStyle by style {
        position(Position.Absolute)
        width(100.vw)
        height(100.vh)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Left)
        alignItems(AlignItems.Start)

        property("user-select", "none")
    }
}
