package kt.petrovich

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * @author Dmitrii Kniazev
 * @since 06.10.2017
 */
class GenderTest {
    @DisplayName("Gender detection test")
    @Test
    fun detectGenderTest() {
        val male = Gender.detectGender("Алексеевич")
        val female = Gender.detectGender("Алексеевна")
        val androgynous = Gender.detectGender("Бильжо")

        Assertions.assertEquals(Gender.MALE, male)
        Assertions.assertEquals(Gender.FEMALE, female)
        Assertions.assertEquals(Gender.ANDROGYNOUS, androgynous)
    }
}
