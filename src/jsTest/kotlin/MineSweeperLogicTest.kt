import ktshsResearchAssignmentE2022.com.github.minesweeper.MineSweeperLogic
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ComposeWebExperimentalTestsApi::class)
class MineSweeperLogicTest {
    @Test
    fun testLogic() = runTest {
        var count = 0
        for (i in 2..20) {
            for (n in 1..(i * i)) {
                val logic = MineSweeperLogic(i, i, n, Random.nextInt())
                for (x in 0 until i) {
                    for (y in 0 until i) {
                        if (logic.map[x][y].isMine) count++
                    }
                }
                println("length: $i, mine:$n")
                assertEquals(n, count)
            }
            count = 0
        }
    }
}
