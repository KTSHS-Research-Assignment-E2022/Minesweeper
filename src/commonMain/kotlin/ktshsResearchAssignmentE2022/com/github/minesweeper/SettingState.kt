package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

object SettingState {
    var column by mutableStateOf(Difficulty.Easy.column)
    var row by mutableStateOf(Difficulty.Easy.row)
    var numOfMines by mutableStateOf(Difficulty.Easy.numOfMines)
    var seed by mutableStateOf(Random.nextInt())
    var difficulty by mutableStateOf(Difficulty.Easy)

    fun setWithDifficulty(difficulty: Difficulty, seed: Int = Random.nextInt()) {
        setAll(difficulty.column, difficulty.row, difficulty.numOfMines, seed, difficulty)
    }

    fun setAll(
        column: Int,
        row: Int,
        numOfMines: Int,
        seed: Int = Random.nextInt(),
        difficulty: Difficulty = Difficulty.Manual
    ) {
        this.difficulty = difficulty
        this.column = column
        this.row = row
        this.numOfMines = numOfMines
        this.seed = seed
    }
}

enum class Difficulty {
    Easy, Normal, Hard, Manual;

    val column: Int
        get() {
            return when (this) {
                Easy -> 9
                Normal -> 9
                Hard -> 9
                Manual -> SettingState.column
            }
        }

    val row: Int
        get() {
            return when (this) {
                Easy -> 9
                Normal -> 9
                Hard -> 9
                Manual -> SettingState.row
            }
        }

    val numOfMines: Int
        get() {
            return when (this) {
                Easy -> 8
                Normal -> 12
                Hard -> 16
                Manual -> SettingState.numOfMines
            }
        }
}
