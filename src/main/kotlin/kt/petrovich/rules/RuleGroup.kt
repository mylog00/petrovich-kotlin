package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Dmitrii Kniazev
 * @since 08.06.2014
 */
class RuleGroup @JsonCreator constructor(
        @JsonProperty("exceptions") val exceptions: List<Rule>,
        @JsonProperty("suffixes") val suffixes: List<Rule>)
