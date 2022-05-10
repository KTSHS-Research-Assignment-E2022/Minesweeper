import ktshsResearchAssignmentE2022.com.github.minesweeper.MineSweeperLogic
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ComposeWebExperimentalTestsApi::class)
class MineSweeperLogicTest {
    @Test
    fun testLogic() = runTest {
        val logic = MineSweeperLogic(2,2,1,1)
        assertEquals(1, logic.numOfMines)
    }
}
