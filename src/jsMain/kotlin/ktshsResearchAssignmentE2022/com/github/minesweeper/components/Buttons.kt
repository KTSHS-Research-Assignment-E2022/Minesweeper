package ktshsResearchAssignmentE2022.com.github.minesweeper.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun GrowingButton(string: String, isGrowing: Boolean, height: CSSNumeric = 20.percent, onClick: () -> Unit) {
    Button({
        style {
            height(height)
            width(90.percent)
            backgroundColor(Color.white)
            borderRadius(20.px)
            border {
                style = LineStyle.Solid
            }
            if (isGrowing) backgroundColor(Color.lightskyblue)
        }
        onClick {
            onClick()
        }
    }) {
        Text(string)
    }
}
