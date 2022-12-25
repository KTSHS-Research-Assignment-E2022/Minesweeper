package ktshsResearchAssignmentE2022.com.github.minesweeper.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text

@Composable
fun GrowingButton(
    string: String,
    isGrowing: Boolean,
    height: CSSNumeric = 20.percent,
    playSound: Boolean = false,
    onClick: () -> Unit
) {
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
            if (playSound) org.w3c.dom.Audio("./sounds/click.mp3").play()
        }
    }) {
        Text(string)
    }
}

@Composable
fun OnHoverGrowingButton(
    string: String,
    height: CSSNumeric = 20.percent,
    width: CSSNumeric = 70.percent,
    playSound: Boolean = false,
    onClick: () -> Unit
) {
    val defaultColor = Color.white
    val growColor = Color.lightskyblue
    var bgColor by mutableStateOf(defaultColor)
    Button({
        style {
            height(height)
            width(width)
            borderRadius(2.vmin)
            border {
                style = LineStyle.Solid
            }
            backgroundColor(bgColor)
            outlineColor(Color.black)
        }
        onMouseOver {
            bgColor = growColor
        }
        onMouseLeave {
            bgColor = defaultColor
        }
        onClick {
            if (playSound) org.w3c.dom.Audio("./sounds/click.mp3").play()
            onClick()
        }
    }) {
        Text(string)
    }
}

@Composable
fun SettingMenuButton(onClick: () -> Unit) {
    CircleButton(string = "⚙", onClick = onClick)
}

@Composable
fun CircleButton(string: String = "", isGrowing: Boolean = false, onClick: () -> Unit) {
    Button({
        style {
            height(10.vmin)
            width(10.vmin)
            borderRadius(5.vmin)
            backgroundColor(Color.white)
            marginTop(2.vmin)
            marginLeft(2.vmin)
            property("pointer-events", "auto")
            if (isGrowing) backgroundColor(Color.lightskyblue)
        }
        onClick {
            onClick()
            org.w3c.dom.Audio("./sounds/click.mp3").play()
        }
    }) {
        H2 { Text(string) }
    }
}