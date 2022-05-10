package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

object SettingState {
    var xLength by mutableStateOf(Difficulty.Easy.xLength)
    var yLength by mutableStateOf(Difficulty.Easy.yLength)
    var numOfMines by mutableStateOf(Difficulty.Easy.numOfMines)
    var seed by mutableStateOf(Random.nextInt())
    var difficulty by mutableStateOf(Difficulty.Easy)

    fun setWithDifficulty(difficulty: Difficulty, seed: Int = Random.nextInt()) {
        setAll(difficulty.xLength, difficulty.yLength, difficulty.numOfMines, seed, difficulty)
    }

    fun setAll(
        x: Int,
        y: Int,
        numOfMines: Int,
        seed: Int = Random.nextInt(),
        difficulty: Difficulty = Difficulty.Manual
    ) {
        this.difficulty = difficulty
        this.xLength = x
        this.yLength = y
        this.numOfMines = numOfMines
        this.seed = seed
    }
}

enum class Difficulty {
    Easy, Normal, Hard, Manual;

    val xLength: Int
        get() {
            return when (this) {
                Easy -> 9
                Normal -> 9
                Hard -> 9
                Manual -> SettingState.xLength
            }
        }

    val yLength: Int
        get() {
            return when (this) {
                Easy -> 9
                Normal -> 9
                Hard -> 9
                Manual -> SettingState.yLength
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
