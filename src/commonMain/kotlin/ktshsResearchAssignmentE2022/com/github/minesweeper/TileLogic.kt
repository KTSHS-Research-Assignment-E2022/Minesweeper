package ktshsResearchAssignmentE2022.com.github.minesweeper

data class TileLogic(val isBomb: Boolean) {
    var nearbyMines: Int = if(isBomb) -1 else  0
}
