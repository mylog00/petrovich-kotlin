package kt.petrovich

import kt.petrovich.rules.Rule
import kt.petrovich.rules.RuleGroup
import kt.petrovich.rules.Rules
import java.io.FileNotFoundException
import java.util.ArrayList

/**
 * @author Dmitrii Kniazev
 * @since 31.05.2014
 */
class Petrovich(private val rules: Rules) {

    /**
     * Load rules from file path
     *
     * @param path file path
     * @throws FileNotFoundException if file path not exist
     */
    @Throws(FileNotFoundException::class)
    public constructor(path: String) : this(Rules.loadRules(path))

    /**
     * Load rules from default file from resources.
     */
    public constructor() : this(Rules.loadRules())

    /**
     * Convert first name from nominative case to specified case
     *
     * @param firstName  first name at nominative case
     * @param gender gender
     * @param case   case
     * @return converted first name
     */
    public fun firstName(firstName: String, gender: Gender, case: Case): String {
        return convertTo(rules.firstName, firstName, gender, case)
    }

    /**
     * Convert last name from nominative case to specified case
     *
     * @param lastName   last name at nominative case
     * @param gender gender
     * @param case   case
     * @return converted last name
     */
    public fun lastName(lastName: String, gender: Gender, case: Case): String {
        return convertTo(rules.lastName, lastName, gender, case)
    }

    /**
     * Convert middle name from nominative case to specified case
     *
     * @param middleName middle name at nominative case
     * @param gender gender
     * @param case   case
     * @return converted middle name
     */
    public fun middleName(middleName: String, gender: Gender, case: Case): String {
        return convertTo(rules.middleName, middleName, gender, case)
    }

    /**
     * Convert middle name from nominative case to specified case with auto detect gender
     *
     * @param middleName middle name at nominative case
     * @param case   case
     * @return converted middle name
     */
    public fun middleName(middleName: String, case: Case): String {
        val genderCase = Gender.detectGender(middleName)
        return middleName(middleName, genderCase, case)
    }

    private fun convertTo(ruleGroup: RuleGroup, name: String, gender: Gender, case: Case): String {
        val splittedName = name.trim().toLowerCase().split("-").toTypedArray()
        val result = ArrayList<String>(splittedName.size)
        var firstWord = splittedName.size > 1
        for (word in splittedName) {
            val rule: Rule? = ruleGroup.getRule(word, gender, firstWord)
            if (rule == null) {
                result.add(word)
            } else {
                result.add(rule.apply(word, case))
            }
            if (firstWord) {
                firstWord = false
            }
        }
        return result.joinToString("-")
    }
}
