package jPetrovich.util;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public enum EGender {
	male("мужской"),        // мужской
	female("женский"),      // женский
	androgynous("средний"); // средний

	private final String _rusTitle;

	EGender(String rusTitle) {
		this._rusTitle = rusTitle;
	}

	public String getRusTitle() {
		return _rusTitle;
	}

	/**
	 * Converts the string representation of the name or numeric value of one or more enumerated constants
	 * to an equivalent enumerated object.
	 * A parameter specifies whether the operation is case-sensitive.
	 *
	 * @param value The string representation of the enumeration name or underlying value to convert.
	 * @return gender if the value parameter was converted successfully; otherwise, {@code null}.
	 */
	public static EGender getGender(String value) {
		try {
			return valueOf(value.toLowerCase());
                }
                catch (IllegalArgumentException e) {
			return null;
                }
	}
}
