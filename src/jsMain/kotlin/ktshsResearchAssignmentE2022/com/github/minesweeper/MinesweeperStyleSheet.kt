package ktshsResearchAssignmentE2022.com.github.minesweeper

import org.jetbrains.compose.web.css.*

object MinesweeperStyleSheet : StyleSheet() {

    val minesweeperContainer by style {
        display(DisplayStyle.Flex) // Flexを指定
        flexDirection(FlexDirection.Row) // 横並び
        flexWrap(FlexWrap.Wrap) // 折り返し下
        alignItems(AlignItems.FlexStart)

        backgroundColor(Color.whitesmoke)
        width(90.vmin)
        height(90.vmin)

        borderRadius(2.percent)
        property("box-shadow", "0px 0px 20px 0px #848484")
    }

    val tileStyle by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)

        property("box-shadow", "0px 0px 13px 0px #848484")
    }
}
