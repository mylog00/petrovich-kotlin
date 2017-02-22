/*$Id:$*/
package jPetrovich.util.rules;

import jPetrovich.util.rules.data.Rules;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public final class RulesLoader {
	private RulesLoader() {
		throw new AssertionError("It helper class. Create instance");
	}

	/**
	 * Load rules from JAML file by default path
	 *
	 * @return rules
	 * @throws FileNotFoundException if file path not exist
	 */
	public static Rules loadRules() throws FileNotFoundException {
		return loadRules("rules.yml");
	}

	/**
	 * Load rules from JAML file path
	 *
	 * @param path path to rules file
	 * @return rules
	 * @throws FileNotFoundException if file path not exist
	 */
	public static Rules loadRules(String path) throws FileNotFoundException {
		return Yaml.loadType(new File(path), Rules.class);
	}
}
