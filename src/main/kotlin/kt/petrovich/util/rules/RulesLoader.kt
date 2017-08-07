package kt.petrovich.util.rules

import kt.petrovich.util.rules.data.Rules
import org.ho.yaml.Yaml

import java.io.File
import java.io.FileNotFoundException

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
class RulesLoader private constructor() {
    init {
        throw AssertionError("It helper class. Create instance forbidden")
    }

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
            return Yaml.loadType(File(path), Rules::class.java)
        }
    }
}
