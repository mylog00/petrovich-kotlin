/*$Id:$*/
package jPetrovich.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author DMITRY KNYAZEV
 * @since 08.06.2014
 */
public final class CommonUtils {

	private CommonUtils() {
		throw new AssertionError("It helper class. Create instance");
	}

	/**
	 * Определение пола по отчеству
	 * Если пол не был определён, метод возвращает значение "androgynous"
	 *
	 * @param middleName отчество
	 * @return пол
	 */
	public static EGender detectGender(String middleName) {
		int strLen = middleName.length();
		if (strLen > 2) {
			String suffix = middleName.toLowerCase().substring(strLen - 2, strLen);
			if (suffix.equals("ич") || suffix.equals("ыч")) return EGender.male;
			if (suffix.equals("на")) return EGender.female;
		}
		return EGender.androgynous;
	}

	/**
	 * Проверяет является массив пустым
	 *
	 * @param str массив для проверки
	 * @return {@code true} если массив пустой, иначе {@code false}
	 */
	public static boolean isEmptyArray(String[] str) {
		return str != null && str.length > 0;
	}
}
