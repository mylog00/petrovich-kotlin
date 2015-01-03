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
		return loadRules("");
	}

	/**
	 * Load rules from JAML file path
	 *
	 * @param path path to rules file
	 * @return rules
	 * @throws FileNotFoundException if file path not exist
	 */
	public static Rules loadRules(String path) throws FileNotFoundException {
		File rule;
		if (path == null || path.isEmpty())
			path = "rules.yml";

		rule = new File(path);
		return Yaml.loadType(rule, Rules.class);
	}
}
