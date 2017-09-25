package kt.petrovich.rules

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File
import java.io.FileNotFoundException

/**
 * @author Dmitrii Kniazev
 * @since 31.05.2014
 */
class RulesLoader private constructor() {
    companion object {
        private val defaultRulesPath = Thread.currentThread()
                .contextClassLoader
                .getResource("rules.yml").path

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
