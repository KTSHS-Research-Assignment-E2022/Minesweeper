package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object SidebarStyleSheet : StyleSheet() {

    val pcSidebarStyle by style {
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

    val elementStyle by style {
        width(100.percent)
    }

    val settingStyle by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        justifyContent(JustifyContent.SpaceBetween)

        height(30.vh)
        backgroundColor(Color.gray)
    }
}
