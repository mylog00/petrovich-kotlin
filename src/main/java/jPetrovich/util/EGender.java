package jPetrovich.util;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public enum EGender {
	male,        // мужской
	female,      // женский
	androgynous; // средний

	public static final String MALE = "male";
	public static final String FEMALE = "female";
	public static final String ANDROGYNOUS = "androgynous";

	/**
	 * Converts the string representation of the name or numeric value of one or more enumerated constants
	 * to an equivalent enumerated object. A parameter specifies whether the operation is case-sensitive.
	 * The return value indicates whether the conversion succeeded.
	 *
	 * @param value The string representation of the enumeration name or underlying value to convert.
	 * @return gender if the value parameter was converted successfully; otherwise, {@code null}.
	 */
	public static EGender tryParse(String value) {
		String toLowString = value.toLowerCase();

		switch (toLowString) {
			case MALE: {
				return male;
			}
			case FEMALE: {
				return female;
			}
			case ANDROGYNOUS: {
				return androgynous;
			}
			default:
				return null;
		}
	}
}
