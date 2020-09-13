package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kt.petrovich.Gender

/**
 * @author Dmitrii Kniazev
 * @since 08.06.2014
 */
class RuleGroup @JsonCreator constructor(
        @JsonProperty("exceptions") val exceptions: List<Rule>,
        @JsonProperty("suffixes") val suffixes: List<Rule>) {

    fun getRule(word: String, gender: Gender, firstWord: Boolean): Rule? {
        var rule: Rule? = findRule(exceptions, word, gender, firstWord)
        if (rule == null) {
            rule = findRule(suffixes, word, gender, firstWord)
        }
        return rule
    }

    private fun findRule(rules: List<Rule>, word: String, gender: Gender, firstWord: Boolean): Rule? {
        for (rule in rules) {
            if (rule.matchRule(word, gender, firstWord)) {
                return rule
            }
        }
        return null
    }
}
