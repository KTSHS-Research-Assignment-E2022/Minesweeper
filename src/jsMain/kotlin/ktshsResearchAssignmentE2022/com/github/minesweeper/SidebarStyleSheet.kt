package ktshsResearchAssignmentE2022.com.github.minesweeper

import org.jetbrains.compose.web.css.*

object SidebarStyleSheet : StyleSheet() {
    val elementStyle by style {
        width(100.percent)
    }

    val settingStyle by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.SpaceBetween)
    }

    val settingElementStyle by style {
        margin(50.percent)
    }
}
