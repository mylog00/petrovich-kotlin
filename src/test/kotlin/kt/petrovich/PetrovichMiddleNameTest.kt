package kt.petrovich

import kt.petrovich.Commons.PETROVICH
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource


/**
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
class PetrovichMiddleNameTest {
    @DisplayName("Middle names test")
    @ParameterizedTest(name = "{index}: middle name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/midnames.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun middleNamesTest(
            middleName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.middleName(middleName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }

    @DisplayName("Middle name misc test")
    @ParameterizedTest(name = "{index}: Middle name='{0}'; expected='{1}'; case='{2}';")
    @CsvFileSource(resources = ["/midnames.misc.tsv"], delimiter = '\t', numLinesToSkip = 1)
    fun middleNamesMiscTest(
            middleName: String,
            expected: String,
            case: String) {
        val testCase = Commons.parseTestCase(case)
        val actual = PETROVICH.middleName(middleName, testCase.gender, testCase.case)
        Assertions.assertEquals(expected.toLowerCase(), actual)
    }
}
