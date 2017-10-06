package kt.petrovich

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource


/**
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
class PetrovichTest {
    companion object {
        private val PETROVICH: Petrovich = Petrovich()
    }

    @DisplayName("First name declension test")
    @ParameterizedTest(name = "{index}: first name=''{0}''; gender=''{1}''; case=''{2}''; result=''{3}''")
    @CsvFileSource(resources = arrayOf("/FirstNames.csv"))
    fun firstNameTest(
            firstName: String,
            gender: Gender,
            case: Case,
            expected: String) {
        val result = PETROVICH.firstName(firstName, gender, case)
        Assertions.assertEquals(expected, result)
    }

    @DisplayName("Last name declension test")
    @ParameterizedTest(name = "{index}: last name=''{0}''; gender=''{1}''; case=''{2}''; result=''{3}''")
    @CsvFileSource(resources = arrayOf("/LastNames.csv"))
    fun lastNameTest(
            lastName: String,
            gender: Gender,
            case: Case,
            expected: String) {
        val result = PETROVICH.lastName(lastName, gender, case)
        Assertions.assertEquals(expected, result)
    }

    @DisplayName("Middle name declension test")
    @ParameterizedTest(name = "{index}: middle name=''{0}''; gender=''{1}''; case=''{2}''; result=''{3}''")
    @CsvFileSource(resources = arrayOf("/MiddleNames.csv"))
    fun middleNameTest(
            middleName: String,
            gender: Gender,
            case: Case,
            expected: String) {
        val result = PETROVICH.middleName(middleName, gender, case)
        Assertions.assertEquals(expected, result)
    }
}
