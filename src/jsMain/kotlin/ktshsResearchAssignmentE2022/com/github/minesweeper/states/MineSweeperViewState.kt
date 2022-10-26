package ktshsResearchAssignmentE2022.com.github.minesweeper.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ktshsResearchAssignmentE2022.com.github.minesweeper.MineSweeperLogic

object MineSweeperViewState {
    var logic by mutableStateOf(
        MineSweeperLogic(
            SettingState.xLength,
            SettingState.yLength,
            SettingState.numOfMines,
            SettingState.seed
        )
    )

    fun regenerate() {
        logic = MineSweeperLogic(SettingState.xLength, SettingState.yLength, SettingState.numOfMines, SettingState.seed)
    }
}
