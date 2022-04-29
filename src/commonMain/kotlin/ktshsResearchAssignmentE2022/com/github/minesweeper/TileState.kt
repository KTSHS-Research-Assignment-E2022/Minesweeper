package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class TileState(val isMine: Boolean) {
    var isOpened by mutableStateOf(false)
    var isFlagged by mutableStateOf(false)
    var numOfAroundMines: Int = if (isMine) -1 else 0
}
