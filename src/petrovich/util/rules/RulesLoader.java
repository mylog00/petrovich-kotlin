/*$Id:$*/
package petrovich.util.rules;

import org.ho.yaml.Yaml;
import petrovich.util.rules.data.Rules;

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
		//Map<String, Object> o = (Map<String, Object>) Yaml.load(rule);
		return Yaml.loadType(rule, Rules.class);
	}
}
