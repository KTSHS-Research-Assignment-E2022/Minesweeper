package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object AppStyleSheet : StyleSheet() {

    val mainContainer by style {
        display(DisplayStyle.Flex)
        flexWrap(FlexWrap.Nowrap)

        fontFamily("M PLUS 2", "serif")
        backgroundColor(Color.whitesmoke)
        width(100.vw)
        height(100.vh)

        justifyContent(JustifyContent.SpaceAround)

        // 文字列がドラッグできてしまうのを阻止
        property("user-select", "none")
    }

    val centerContainer by style {
        flexBasis(80.percent)
        height(100.vh)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
    }
}
