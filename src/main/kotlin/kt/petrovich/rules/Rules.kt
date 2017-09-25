package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonAlias

/**
 * @author Dmitrii Kniazev
 * @since 03.06.2014
 */
class Rules {
    var lastName: RuleGroup? = null
        @JsonAlias("lastname")
        set(value) {
            if (value == null) {
                throw NullPointerException("Rule for \"lastname\" is 'null'")
            }
            field = value
        }

    var firstName: RuleGroup? = null
        @JsonAlias("firstname")
        set(value) {
            if (value == null) {
                throw NullPointerException("Rule for \"firstname\" is 'null'")
            }
            field = value
        }
    var middleName: RuleGroup? = null
        @JsonAlias("middlename")
        set(value) {
            if (value == null) {
                throw NullPointerException("Rule for \"middlename\" is 'null'")
            }
            field = value
        }
}
