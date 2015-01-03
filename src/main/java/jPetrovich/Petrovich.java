package jPetrovich;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */

import jPetrovich.util.CommonUtils;
import jPetrovich.util.ECase;
import jPetrovich.util.EGender;
import jPetrovich.util.rules.RulesLoader;
import jPetrovich.util.rules.data.Rule;
import jPetrovich.util.rules.data.RuleSet;
import jPetrovich.util.rules.data.Rules;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class Petrovich {

	private final Rules _rules;

	/**
	 * Use specified rules
	 *
	 * @param rules you rules.
	 * @throws NullPointerException if rules is {@code null}
	 */
	public Petrovich(Rules rules) {
		if (rules == null) throw new NullPointerException("Rules must not be null");
		this._rules = rules;
	}

	/**
	 * Load rules from file path
	 *
	 * @param path file path
	 * @throws FileNotFoundException if file path not exist
	 */
	public Petrovich(String path) throws FileNotFoundException {
		this(RulesLoader.loadRules(path));

	}

	/**
	 * Load rules from default file path.
	 * Default path is "current_dir\rules.yml"
	 *
	 * @throws FileNotFoundException if file path not exist
	 */
	public Petrovich() throws FileNotFoundException {
		this(RulesLoader.loadRules());
	}

	/**
	 * Convert first name from nominative case to specified case
	 *
	 * @param firstName  first name at nominative case
	 * @param genderCase gender
	 * @param nameCase   case
	 * @return converted first name
	 */
	public String firstName(String firstName, EGender genderCase, ECase nameCase) {
		return convertTo(firstName, genderCase, nameCase, getRules().firstname);
	}

	/**
	 * Convert last name from nominative case to specified case
	 *
	 * @param lastName   last name at nominative case
	 * @param genderCase gender
	 * @param nameCase   case
	 * @return converted last name
	 */
	public String lastName(String lastName, EGender genderCase, ECase nameCase) {
		return convertTo(lastName, genderCase, nameCase, getRules().lastname);
	}

	/**
	 * Convert middle name from nominative case to specified case
	 *
	 * @param middleName middle name at nominative case
	 * @param genderCase gender
	 * @param nameCase   case
	 * @return converted middle name
	 */
	public String middleName(String middleName, EGender genderCase, ECase nameCase) {
		return convertTo(middleName, genderCase, nameCase, getRules().middlename);
	}

	/**
	 * Convert middle name from nominative case to specified case with auto detect gender
	 *
	 * @param middleName middle name at nominative case
	 * @param nameCase   case
	 * @return converted middle name
	 */
	public String middleName(String middleName, ECase nameCase) {
		EGender genderCase = CommonUtils.detectGender(middleName);
		return middleName(middleName, genderCase, nameCase);
	}

	private String convertTo(String sourceName, EGender genderCase, ECase nameCase, RuleSet ruleSet) {
		String[] splittedName = sourceName.split("-");
		List<String> result = new ArrayList<>(splittedName.length);
		for (int index = 0; index < splittedName.length; index++) {
			final boolean firstWord = index == 0 && splittedName.length > 1;
			Map<String, Boolean> features = new HashMap<String, Boolean>() {{
				put("first_word", firstWord);
			}};
			String modifiedWord = findAndApply(splittedName[index], genderCase, nameCase, ruleSet, features);
			result.add(modifiedWord);
		}
		return StringUtils.join(result, "-");
	}

	private String findAndApply(String name, EGender genderCase, ECase nameCase, RuleSet ruleSet, Map<String, Boolean> features) {
		Rule rule = findRule(name, genderCase, ruleSet, features);

		if (rule == null)
			return name;

		return apply(name, nameCase, rule);
	}

	private Rule findRule(String name, EGender genderCase, RuleSet ruleSet, Map<String, Boolean> features) {
		Set<String> tags = features.keySet();

		Rule rule;
		if (ruleSet.exceptions != null) {
			rule = findRule(name, genderCase, ruleSet.exceptions, true, tags);
			if (rule != null)
				return rule;
		}
		return findRule(name, genderCase, ruleSet.suffixes, false, tags);
	}

	private Rule findRule(String name, EGender genderCase, Rule[] rules, Boolean matchWholeWord, Set<String> tags) {
		for (Rule rule : rules) {
			if (matchRule(name, genderCase, rule, matchWholeWord, tags))
				return rule;
		}
		return null;
	}

	private boolean matchRule(String name, EGender genderCase, Rule rule, Boolean matchWholeWord, Set<String> tags) {
		//todo simplify
		List<String> ex = new ArrayList<>();
		if (rule.tags != null) ex.addAll(Arrays.asList(rule.tags));
		ex.removeAll(tags);

		if (!ex.isEmpty())
			return false;

		EGender genderRule = EGender.getGender(rule.gender);
		if (EGender.getGender(rule.gender) != null &&
				((genderRule == EGender.male && genderCase == EGender.female) ||
						(genderRule == EGender.female && genderCase != EGender.female))) {
			return false;
		}

		name = name.toLowerCase();
		for (String chars : rule.test) {
			String test = matchWholeWord ? name : name.substring(Math.max(0, name.length() - chars.length()));
			if (test.equals(chars))
				return true;
		}
		return false;
	}

	private String apply(String name, ECase nameCase, Rule rule) {
		for (char str : FindCaseModifier(nameCase, rule).toCharArray()) {
			switch (str) {
				case '.':
					break;
				case '-':
					name = name.substring(0, name.length() - 1);
					break;
				default:
					name += str;
					break;
			}
		}
		return name;
	}

	private String FindCaseModifier(ECase nameCase, Rule rule) {
		switch (nameCase) {
			case nominative:
				return "";
			case genitive:
				return rule.mods[0];
			case dative:
				return rule.mods[1];
			case accusative:
				return rule.mods[2];
			case instrumental:
				return rule.mods[3];
			case prepositional:
				return rule.mods[4];
			default:
				throw new IllegalArgumentException(String.format("Unknown grammatical case: %s", nameCase));
		}
	}

	//getters/setters
	public Rules getRules() {
		return _rules;
	}

}
