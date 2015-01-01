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
public abstract class RulesLoader implements IRulesLoader
{
	public static Rules load() throws FileNotFoundException
	{
		return load(null);
	}

	public static Rules load( String path ) throws FileNotFoundException
	{
		File rule;
		if( path == null || path.isEmpty() )
			rule = new File("rules.yml");
		else
			rule = new File(path);
		return Yaml.loadType(rule, Rules.class);
	}
}
