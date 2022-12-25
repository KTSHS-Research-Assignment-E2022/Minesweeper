package ktshsResearchAssignmentE2022.com.github.minesweeper

import androidx.compose.runtime.Composable
import ktshsResearchAssignmentE2022.com.github.minesweeper.components.OnHoverGrowingButton
import ktshsResearchAssignmentE2022.com.github.minesweeper.states.AppState
import ktshsResearchAssignmentE2022.com.github.minesweeper.styleSheets.BlackOutOverlayStyleSheet
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun Help() {
    BlackOutOverlayLayout {
        Div({
            classes(BlackOutOverlayStyleSheet.HelpStyle)
        }) {
            H1 { Text("遊び方") }
            P { Text("0. 空白マス・数字マス・地雷マスがあります") }
            P { Text("1. 好きなマスをクリックしてください") }
            P { Text("2. 数字マスと接する8マスにはその数字個の地雷があります") }
            P { Text("3. 地雷だと思った場所には右クリックで旗を立てましょう") }
            P { Text("4. 地雷以外のマスをすべて開いたらクリアです！") }
            OnHoverGrowingButton("閉じる", height = 15.percent, playSound = true) {
                AppState.isHelpOpen = false
            }
        }
    }
}