/*$Id:$*/
package jPetrovich.util.rules;

import org.ho.yaml.Yaml;
import jPetrovich.util.rules.data.Rules;

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
	 * Загрузка правил из JAML файла по умолчанию
	 *
	 * @return набор правил
	 * @throws FileNotFoundException
	 */
	public static Rules loadRules() throws FileNotFoundException {
		return loadRules(null);
	}

	/**
	 * Загрузка правил из JAML указанного файла
	 *
	 * @param path путь до файла с правилами
	 * @return набор правил
	 * @throws FileNotFoundException
	 */
	public static Rules loadRules(String path) throws FileNotFoundException {
		File rule;
		if (path == null || path.isEmpty())
			path = "rules.yml";

		rule = new File(path);
		return Yaml.loadType(rule, Rules.class);
	}
}
