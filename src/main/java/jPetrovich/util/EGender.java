package jPetrovich.util;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public enum EGender
{
	male,       // мужской
	female,     // женский
	androgynous; // средний

	/**
	 * Converts the string representation of the name or numeric value of one or more enumerated constants
	 * to an equivalent enumerated object. A parameter specifies whether the operation is case-sensitive.
	 * The return value indicates whether the conversion succeeded.
	 * @param value The string representation of the enumeration name or underlying value to convert.
	 * @param result When this method returns, contains an object of type TEnum whose value is represented by value. This parameter is passed uninitialized.
	 * @return <b>true</b> if the value parameter was converted successfully; otherwise, <b>false</b>.
	 */
	public static boolean TryParse(String value, EGender result ) {
		String toLowString = value.toLowerCase();
		if(toLowString.equals(male.toString())) {
			result = male;
			return true;
		}
		if(toLowString.equals(female.toString())) {
			result = female;
			return true;
		}
		result = androgynous;
		return false;
	}
}
