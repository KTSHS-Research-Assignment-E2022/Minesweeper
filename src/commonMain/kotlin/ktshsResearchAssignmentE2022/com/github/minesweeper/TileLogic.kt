package ktshsResearchAssignmentE2022.com.github.minesweeper

data class TileLogic(val isMine: Boolean) {
    var isOpened = false
    var isFlagged = false
    var numOfAroundMines: Int = if(isMine) -1 else  0
}
