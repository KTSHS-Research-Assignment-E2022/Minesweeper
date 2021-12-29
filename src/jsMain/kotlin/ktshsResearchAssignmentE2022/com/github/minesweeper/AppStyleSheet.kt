package ktshsResearchAssignmentE2022.com.github.minesweeper

import org.jetbrains.compose.web.css.*

object AppStyleSheet : StyleSheet() {

    val mainContainer by style {
        display(DisplayStyle.Flex)
        flexWrap(FlexWrap.Nowrap)

        fontFamily("M PLUS Rounded 1c", "sans-serif")
        background("#F1F1F1")
        width(100.vw)
        height(100.vh)
    }

    val centerContainer by style {
        flexBasis(80.percent)
        height(100.vh)

        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
    }

    val minesweeperContainer by style {
        display(DisplayStyle.Flex) // Flexを指定
        flexDirection(FlexDirection.Row) // 横並び
        flexWrap(FlexWrap.Wrap) // 折り返し下
        alignItems(AlignItems.FlexStart)

        backgroundColor(Color.sandybrown)
        width(90.vmin)
        height(90.vmin)
    }

    val sidebarPCStyle by style {
        flexBasis(20.percent) // 子要素としてのプロパティ

        // Flex box 親要素プロパティ
        display(DisplayStyle.Flex)
        flexFlow(FlexDirection.Column, FlexWrap.Wrap)
        justifyContent(JustifyContent.SpaceBetween)

        // 自分自身へのプロパティ
        backgroundColor(Color.bisque)
        width(10.vw)
        height(100.vh)
        borderRadius(0.1.px, 20.px, 20.px, 0.1.px)
        property("box-shadow", "5px 0px 24px -2px #848484") // 影
    }

    val tileStyle by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
    }

}
