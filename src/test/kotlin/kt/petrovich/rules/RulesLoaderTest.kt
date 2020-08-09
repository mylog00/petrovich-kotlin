package kt.petrovich.rules

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

/**
 * @author Dmitrii Kniazev
 * @since 06.10.2017
 */
class RulesLoaderTest {
    @DisplayName("Wrong path to rules test")
    @Test
    fun wrongFilePathTest() {
        Assertions.assertThrows(
                FileNotFoundException::class.java
        ) { RulesLoader.loadRules("wrong_path/rules.yaml") }
    }
}
