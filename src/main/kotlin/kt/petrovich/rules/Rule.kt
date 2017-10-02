package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import kt.petrovich.Gender
import kt.petrovich.exceptoins.RulesCreationException

/**
 * @author Dmitrii Kniazev
 * @since 08.06.2014
 */

class Rule constructor(
        val gender: Gender,
        val test: List<String>,
        val mods: List<String>,
        val tags: List<String>) {

    @Throws(RulesCreationException::class)
    @JsonCreator constructor(props: Map<String, Any>) : this(
            gender = getGender(props["gender"]),
            test = getStringList(props["test"]),
            mods = getStringList(props["mods"]),
            tags = getStringList(props.getOrDefault("tags", emptyList<String>())))

    private companion object {
        @Throws(RulesCreationException::class)
        private fun getGender(genderStr: Any?): Gender =
                if (genderStr is String) {
                    try {
                        Gender.valueOf(genderStr.toUpperCase())
                    } catch (iae: IllegalArgumentException) {
                        val message = "Can't find gender with the specified name:\'$genderStr\'"
                        throw RulesCreationException(message, iae)
                    }
                } else {
                    throw RulesCreationException("Gender is not string:$genderStr")
                }

        @Throws(RulesCreationException::class)
        private fun getStringList(obj: Any?): List<String> {
            if (obj is List<*> && obj.all { it is String }) {
                @Suppress("UNCHECKED_CAST")
                return obj as List<String>
            } else {
                throw RulesCreationException("This object is not a list of string:\'$obj\'")
            }
        }
    }

}


