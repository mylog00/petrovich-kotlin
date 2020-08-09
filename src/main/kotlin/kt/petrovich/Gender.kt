package kt.petrovich

/**
 * @param rusName gender in russian language
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
enum class Gender(private val rusName: String) {
    MALE("мужской"),
    FEMALE("женский"),
    ANDROGYNOUS("средний");

    companion object {
        /**
         * Detect gender by middle name.
         *
         * @param middleName not empty middle name
         * @return gender if detected, else return "androgynous"
         */
        fun detectGender(middleName: String): Gender {
            val length = middleName.length
            if (length >= 2) {
                val suffix = middleName.substring(length - 2, length).toLowerCase()
                if (suffix == "ич" || suffix == "ыч") return MALE
                if (suffix == "на") return FEMALE
            }
            return ANDROGYNOUS
        }
    }
}
