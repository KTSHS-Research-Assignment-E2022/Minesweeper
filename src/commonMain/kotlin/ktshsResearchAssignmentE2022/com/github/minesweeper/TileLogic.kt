package ktshsResearchAssignmentE2022.com.github.minesweeper

data class TileLogic(val isMine: Boolean) {
    var numOfAroundMines: Int = if(isMine) -1 else  0
}
