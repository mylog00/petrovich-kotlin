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

import static jPetrovich.util.CommonUtils.isEmptyArray;

/**
 * Склонение падежей русских имён фамилий и отчеств. Вы задаёте начальное имя в именительном падеже,
 * а получаете в нужном вам.
 */
public class Petrovich {

	private EGender _gender;
	private Rules _rules;

	/**
	 * @param gender
	 * @param path
	 * @throws FileNotFoundException
	 */
	public Petrovich(EGender gender, String path) throws FileNotFoundException {
		this._gender = gender;
		this._rules = RulesLoader.loadRules(path);

	}

	/**
	 * @param gender
	 * @throws FileNotFoundException
	 */
	public Petrovich(EGender gender) throws FileNotFoundException {
		this(gender, null);
	}

	/**
	 * @throws FileNotFoundException
	 */
	public Petrovich() throws FileNotFoundException {
		this(EGender.androgynous);
	}

	public String lastName(String lastName, ECase nameCase) {
		return inflectTo(lastName, nameCase, getRules().lastname);
	}

	public String firstName(String firstName, ECase nameCase) {
		return inflectTo(firstName, nameCase, getRules().firstname);
	}

	public String middleName(String middleName, ECase nameCase) {
		if (middleName != null) setGender(CommonUtils.detectGender(middleName));
		return inflectTo(middleName, nameCase, getRules().middlename);
	}

	/**
	 * Склоняем имя
	 *
	 * @param sourceName имя в иминительном падеже
	 * @param nameCase   склонение
	 * @param ruleSet    набор правил для имени
	 * @return имя в нужном склонени
	 */
	//todo rename and rewrite this method
	private String inflectTo(String sourceName, ECase nameCase, RuleSet ruleSet) {
		String[] splittedName = sourceName.split("-");
		for (int index = 0; index < splittedName.length; index++) {
			final Boolean firstWord = index == 0 && splittedName.length > 1;
			//Map<String, Boolean> firstWordMap = new HashMap<String, Boolean>() {{ put("first_word", firstWord); }};
			splittedName[index] = findAndApply(splittedName[index], nameCase, ruleSet, new HashMap<String, Boolean>() {{
				put("first_word", firstWord);
			}});
		}
		return StringUtils.join(splittedName, "-");
	}

	private String findAndApply(String name, ECase nameCase, RuleSet ruleSet, Map<String, Boolean> features) {
		Rule rule = FindRulesFor(name, ruleSet, features);

		if (rule == null)
			return name;

		return apply(name, nameCase, rule);
	}

	private Rule FindRulesFor(String name, RuleSet ruleSet, Map<String, Boolean> features) {
		Set<String> tags = ExtractTags(features);

		Rule rule;
		if (ruleSet.exceptions != null) {
			rule = Find(name, ruleSet.exceptions, true, tags);
			if (rule != null)
				return rule;
		}

		return Find(name, ruleSet.suffixes, false, tags);
	}

	private Set<String> ExtractTags(Map<String, Boolean> features) {
		//return set key "first_word" if value a "true"
		Set<String> res = new HashSet<>();
		for (Map.Entry<String, Boolean> feature : features.entrySet()) {
			if (feature.getValue())
				res.add(feature.getKey());
		}
		return res;
	}

	private Rule Find(String name, Rule[] rules, Boolean matchWholeWord, Set<String> tags) {
		for (Rule rule : rules) {
			if (MatchRule(name, rule, matchWholeWord, tags))
				return rule;
		}
		//return rules.FirstOrDefault(rule => MatchRule(name, rule, matchWholeWord, tags));
		for (Rule rule : rules) {
			if (MatchRule(name, rule, matchWholeWord, tags))
				return rule;
		}
		return null;
	}

	private Boolean MatchRule(String name, Rule rule, Boolean matchWholeWord, Set<String> tags) {
		if (rule.tags == null || isEmptyArray(rule.tags))
			return false;

		EGender genderRule = EGender.tryParse(rule.gender);
		if (EGender.tryParse(rule.gender) != null &&
				((genderRule == EGender.male && getGender() == EGender.female) ||
						(genderRule == EGender.female && getGender() != EGender.female))) {
			return false;
		}

		name = name.toLowerCase();
		for (String chars : rule.test) {
			String test = matchWholeWord ? name : name.substring(0, name.length() - chars.length());
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


	public EGender getGender() {
		return _gender;
	}

	protected void setGender(EGender gender) {
		_gender = gender;
	}

	public Rules getRules() {
		return _rules;
	}

}
