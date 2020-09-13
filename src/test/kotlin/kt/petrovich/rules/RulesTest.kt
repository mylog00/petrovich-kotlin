package kt.petrovich.rules

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

/**
 * @author Dmitrii Kniazev
 * @since 06.10.2017
 */
class RulesTest {
    @DisplayName("Wrong path to rules test")
    @Test
    fun wrongFilePathTest() {
        Assertions.assertThrows(
                FileNotFoundException::class.java
        ) { Rules.loadRules("wrong_path/rules.yaml") }
    }

    @DisplayName("Success test")
    @Test
    fun successTest() {
        Assertions.assertNotNull(Rules.loadRules())
    }
}
