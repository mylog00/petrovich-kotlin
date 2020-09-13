package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kt.petrovich.Case
import kt.petrovich.Gender

/**
 * @author Dmitrii Kniazev
 * @since 08.06.2014
 */

class Rule @JsonCreator constructor(
        @JsonProperty("gender") gender: String,
        @JsonProperty("test") val test: List<String>,
        @JsonProperty("mods") val mods: List<String>,
        @JsonProperty("tags") tags: List<String>?) {
    val gender: Gender = Gender.of(gender)
    private val firstWord: Boolean = (tags != null) && tags.isNotEmpty()

    fun matchRule(word: String, gender: Gender, firstWord: Boolean): Boolean {
        if (!gender.eq(this.gender)) {
            return false
        }
        if (this.firstWord && !firstWord) {
            return false
        }
        for (v in this.test) {
            if (word.endsWith(v)) {
                return true
            }
        }
        return false
    }

    fun apply(name: String, case: Case): String {
        val modifier = getModifier(case)
        return applyModifier(name, modifier)
    }

    private fun getModifier(case: Case): String {
        return when (case) {
            Case.NOMINATIVE -> EMPTY
            Case.GENITIVE -> mods[0]
            Case.DATIVE -> mods[1]
            Case.ACCUSATIVE -> mods[2]
            Case.INSTRUMENTAL -> mods[3]
            Case.PREPOSITIONAL -> mods[4]
        }
    }

    private fun applyModifier(name: String, modifier: String): String {
        val result = StringBuilder(name)
        val postfix = StringBuilder()
        for (c in modifier.toCharArray()) {
            when (c) {
                '-' -> result.remove()
                '.' -> {
                }
                else -> postfix.append(c)
            }
        }
        return result.append(postfix).toString()
    }

    /**
     * Remove last character.
     */
    private fun StringBuilder.remove() {
        val len = this.length - 1
        if (len >= 0) {
            this.deleteAt(len)
        }
    }

    companion object {
        private const val EMPTY = ""
    }
}


