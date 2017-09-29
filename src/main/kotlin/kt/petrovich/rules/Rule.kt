package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import kt.petrovich.Gender

/**
 * @author Dmitrii Kniazev
 * @since 08.06.2014
 */
class Rule @JsonCreator constructor(props: Map<String, Any>) {
    val gender: Gender = Gender.valueOf((props["gender"] as String).toUpperCase())

    @Suppress("UNCHECKED_CAST")
    val test: List<String> = props["test"] as List<String>

    @Suppress("UNCHECKED_CAST")
    val mods: List<String> = props["mods"] as List<String>

    @Suppress("UNCHECKED_CAST")
    val tags: List<String>
            = props.getOrDefault("tags", emptyList<String>()) as List<String>
}

