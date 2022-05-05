package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object ResultStyleSheet : StyleSheet() {
    val ResultContainerStyle by style {
        position(Position.Absolute)
        width(100.vw)
        height(100.vh)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)

        backgroundColor(rgba(0, 0, 0, 0.7))
    }

    val ResultStyle by style {
        height(50.vmin)
        width(80.vmin)
        borderRadius(5.vmin)
        backgroundColor(Color.white)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        flexWrap(FlexWrap.Wrap)
    }
}
