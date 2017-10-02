package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Dmitrii Kniazev
 * @since 03.06.2014
 */

class Rules @JsonCreator constructor(
        @JsonProperty("lastname") val lastName: RuleGroup,
        @JsonProperty("firstname") val firstName: RuleGroup,
        @JsonProperty("middlename") val middleName: RuleGroup)
