package ktshsResearchAssignmentE2022.com.github.minesweeper.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppState {
    var isSidebarOpen by mutableStateOf(false)
    var isHelpOpen by mutableStateOf(false)
}