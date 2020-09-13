package kt.petrovich

import kt.petrovich.Commons.PETROVICH
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource


/**
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
class PetrovichLastNameTest {
    @Disabled
    @DisplayName("Last names test")
    @ParameterizedTest(name = "{index}: last name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/surnames.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun lastNamesTest(
            lastName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.lastName(lastName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }

    @DisplayName("Last name misc test")
    @ParameterizedTest(name = "{index}: last name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/surnames.misc.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun lastNamesMiscTest(
            lastName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.lastName(lastName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }
}
