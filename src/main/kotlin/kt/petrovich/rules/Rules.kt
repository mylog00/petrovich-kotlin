package kt.petrovich.rules

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File
import java.io.FileNotFoundException

/**
 * @author Dmitrii Kniazev
 * @since 03.06.2014
 */

class Rules @JsonCreator private constructor(
        @JsonProperty("lastname") val lastName: RuleGroup,
        @JsonProperty("firstname") val firstName: RuleGroup,
        @JsonProperty("middlename") val middleName: RuleGroup) {

    companion object {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        private val defaultRulesPath = Thread
                .currentThread()
                .contextClassLoader
                .getResource("rules.yml")
                .path

        /**
         * Load rules from YAML file path
         *
         * @param path path to rules file
         * @return rules
         * @throws FileNotFoundException if file path not exist
         */
        @Throws(FileNotFoundException::class)
        @JvmOverloads
        fun loadRules(path: String = defaultRulesPath): Rules {
            return ObjectMapper(YAMLFactory()).readValue(File(path), Rules::class.java)
        }
    }
}
