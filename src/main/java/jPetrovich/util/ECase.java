package jPetrovich.util;

/**
 * @author DMITRY KNYAZEV
 * @since 31.05.2014
 */
public enum ECase {
	nominative("именительный"),     //именительный
	genitive("родительный"),        // родительный
	dative("дательный"),            // дательный
	accusative("винительный"),      // винительный
	instrumental("творительный"),   // творительный
	prepositional("предложный");    // предложный


	private final String _rusTitle;

	ECase(String rusTitle) {
		this._rusTitle = rusTitle;
	}

	public String getRusTitle() {
		return _rusTitle;
	}
}
