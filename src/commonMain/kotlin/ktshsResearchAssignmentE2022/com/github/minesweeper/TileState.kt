package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.mutableStateOf

data class TileState(val isMine: Boolean) {
    val isOpened = mutableStateOf(false)
    val isFlagged = mutableStateOf(false)
    var numOfAroundMines: Int = if(isMine) -1 else  0
}
