package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

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
        // 合計のwidth = 100/column
        // 合計のheight = 100/row
        margin(0.55.percent)
        width(10.percent)
        height(10.percent)

        borderRadius(30.percent)
        property("box-shadow", "0px 0px 13px 0px #848484")
    }
}
