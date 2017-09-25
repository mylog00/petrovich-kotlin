package kt.petrovich

import kt.petrovich.rules.Rule
import kt.petrovich.rules.RuleGroup
import kt.petrovich.rules.Rules
import kt.petrovich.rules.RulesLoader
import java.io.FileNotFoundException
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap

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
    constructor(path: String) : this(RulesLoader.loadRules(path))

    /**
     * Load rules from default file path.
     * Default path is "current_dir\rules.yml"
     *
     * @throws FileNotFoundException if file path not exist
     */
    @Throws(FileNotFoundException::class)
    constructor() : this(RulesLoader.loadRules())

    /**
     * Convert first name from nominative case to specified case
     *
     * @param firstName  first name at nominative case
     * @param genderCase gender
     * @param nameCase   case
     * @return converted first name
     */
    fun firstName(firstName: String, genderCase: Gender, nameCase: Case): String {
        return convertTo(firstName, genderCase, nameCase, rules.firstName)
    }

    /**
     * Convert last name from nominative case to specified case
     *
     * @param lastName   last name at nominative case
     * @param genderCase gender
     * @param nameCase   case
     * @return converted last name
     */
    fun lastName(lastName: String, genderCase: Gender, nameCase: Case): String {
        return convertTo(lastName, genderCase, nameCase, rules.lastName)
    }

    /**
     * Convert middle name from nominative case to specified case
     *
     * @param middleName middle name at nominative case
     * @param genderCase gender
     * @param nameCase   case
     * @return converted middle name
     */
    fun middleName(middleName: String, genderCase: Gender, nameCase: Case): String {
        return convertTo(middleName, genderCase, nameCase, rules.middleName)
    }

    /**
     * Convert middle name from nominative case to specified case with auto detect gender
     *
     * @param middleName middle name at nominative case
     * @param nameCase   case
     * @return converted middle name
     */
    fun middleName(middleName: String, nameCase: Case): String {
        val genderCase = Gender.detectGender(middleName)
        return middleName(middleName, genderCase, nameCase)
    }

    private fun convertTo(sourceName: String, genderCase: Gender, nameCase: Case, ruleGroup: RuleGroup?): String {
        val splittedName = sourceName.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<String>(splittedName.size)
        for (index in splittedName.indices) {
            val firstWord = index == 0 && splittedName.size > 1
            val features = object : HashMap<String, Boolean>() {
                init {
                    put("first_word", firstWord)
                }
            }
            val modifiedWord = findAndApply(splittedName[index], genderCase, nameCase, ruleGroup, features)
            result.add(modifiedWord)
        }
        return result.joinToString("-")
    }

    private fun findAndApply(name: String, genderCase: Gender, nameCase: Case, ruleGroup: RuleGroup?, features: Map<String, Boolean>): String {
        val rule = findRule(name, genderCase, ruleGroup, features) ?: return name

        return apply(name, nameCase, rule)
    }

    private fun findRule(name: String, genderCase: Gender, ruleGroup: RuleGroup?, features: Map<String, Boolean>): Rule? {
        val tags = features.keys

        val rule: Rule?
        if (!ruleGroup!!.exceptions.isEmpty()) {
            rule = findRule(name, genderCase, ruleGroup.exceptions, true, tags)
            if (rule != null)
                return rule
        }
        return findRule(name, genderCase, ruleGroup.suffixes, false, tags)
    }

    private fun findRule(name: String, genderCase: Gender, rules: Array<Rule>?, matchWholeWord: Boolean, tags: Set<String>): Rule? {
        for (rule in rules!!) {
            if (matchRule(name, genderCase, rule, matchWholeWord, tags))
                return rule
        }
        return null
    }

    private fun matchRule(name: String, genderCase: Gender, rule: Rule, matchWholeWord: Boolean, tags: Set<String>): Boolean {
        var name = name
        //todo simplify
        val ex = ArrayList<String>()
        if (!rule.tags.isEmpty()) ex.addAll(Arrays.asList(*rule.tags))
        ex.removeAll(tags)

        if (!ex.isEmpty())
            return false

        val genderRule = Gender.valueOf(rule.gender.toUpperCase())
        if ((genderRule == Gender.MALE && genderCase == Gender.FEMALE)
                || (genderRule == Gender.FEMALE && genderCase != Gender.FEMALE)) {
            return false
        }

        name = name.toLowerCase()
        for (chars in rule.test) {
            val test = if (matchWholeWord) name else name.substring(Math.max(0, name.length - chars.length))
            if (test == chars)
                return true
        }
        return false
    }

    private fun apply(name: String, nameCase: Case, rule: Rule): String {
        var name = name
        for (str in findCaseModifier(nameCase, rule).toCharArray()) {
            when (str) {
                '.' -> {
                }
                '-' -> name = name.substring(0, name.length - 1)
                else -> name += str
            }
        }
        return name
    }

    private fun findCaseModifier(nameCase: Case, rule: Rule): String {
        when (nameCase) {
            Case.NOMINATIVE -> return ""
            Case.GENITIVE -> return rule.mods[0]
            Case.DATIVE -> return rule.mods[1]
            Case.ACCUSATIVE -> return rule.mods[2]
            Case.INSTRUMENTAL -> return rule.mods[3]
            Case.PREPOSITIONAL -> return rule.mods[4]
            else -> throw IllegalArgumentException(String.format("Unknown grammatical case: %s", nameCase))
        }
    }

}
