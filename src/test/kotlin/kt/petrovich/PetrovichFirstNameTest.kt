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
class PetrovichFirstNameTest {
    @Disabled
    @DisplayName("First names test")
    @ParameterizedTest(name = "{index}: first name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/firstnames.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun firstNamesTest(
            firstName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.firstName(firstName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }

    @DisplayName("First name misc test")
    @ParameterizedTest(name = "{index}: first name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/firstnames.misc.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun firstNamesMiscTest(
            firstName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.firstName(firstName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }
}
