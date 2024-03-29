package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object BlackOutOverlayStyleSheet : StyleSheet() {
    val BlackOutOverlayContainerStyle by style {
        position(Position.Absolute)
        width(100.vw)
        height(100.vh)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)

        backgroundColor(rgba(0, 0, 0, 0.7))
        property("user-select", "none")
    }

    val ResultStyle by style {
        height(50.vmin)
        width(80.vmin)
        borderRadius(5.vmin)
        backgroundColor(Color.white)
        fontFamily("M PLUS 2", "serif")

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        flexWrap(FlexWrap.Wrap)
    }

    val HelpStyle by style {
        height(70.vmin)
        width(90.vmin)
        borderRadius(5.vmin)
        backgroundColor(Color.white)
        fontFamily("M PLUS 2", "serif")

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.SpaceEvenly)
        flexWrap(FlexWrap.Wrap)
        flexDirection(FlexDirection.Column)
        alignItems(AlignItems.Center)
    }
}
