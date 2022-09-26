package ktshsResearchAssignmentE2022.com.github.minesweeper.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.window

object WindowState {
    var width by mutableStateOf(window.outerWidth)
    var isPhone by mutableStateOf(false)
}