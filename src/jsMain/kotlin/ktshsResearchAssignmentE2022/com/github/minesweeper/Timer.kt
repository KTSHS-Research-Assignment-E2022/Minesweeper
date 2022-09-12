package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

var time by mutableStateOf("0.000")

@Composable
fun Timer() {
    window.setInterval(
        {
            time = MineSweeper.logic.getElapsedSeconds().toInt().toString()
        }, 1
    )

    Div({
        style {
            height(10.percent)
            width(90.percent)
            borderRadius(20.px)
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            justifyContent(JustifyContent.Center)
            alignItems(AlignItems.Center)
            border {
                style = LineStyle.Solid
            }
        }
    }) {
        Text(if (MineSweeper.logic.isStarted) "$time 秒" else "")
    }
}