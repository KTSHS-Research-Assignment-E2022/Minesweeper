package ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets

import org.jetbrains.compose.web.css.*

object PhoneSidebarStyleSheet : StyleSheet() {
    val menuButtonStyle by style{
        width(100.vw)
        height(5.vh)

        borderRadius(10.px)
        backgroundColor(Color.whitesmoke)
        property("box-shadow", "0px 0px 12px 0px #848484")
    }
}
