package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

object SettingState {
    var column by mutableStateOf(9)
    var row by mutableStateOf(9)
    var numOfMines by mutableStateOf(12)
    var seed by mutableStateOf(Random.nextInt())
    var difficulty by mutableStateOf(Difficulty.Easy)

    fun reset() {
        column = 9
        row = 9
        numOfMines = 12
    }
}

enum class Difficulty {
    Easy, Normal, Hard, Manual
}
