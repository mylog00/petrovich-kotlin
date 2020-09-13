package kt.petrovich

/**
 * @param value gender in type string
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
enum class Gender(private val value: String) {
    MALE("male"),
    FEMALE("female"),
    ANDROGYNOUS("androgynous");

    fun eq(gender: Gender): Boolean {
        if (this == ANDROGYNOUS) {
            return true
        }
        return when (gender) {
            ANDROGYNOUS -> true
            else -> this == gender
        }
    }

    companion object {
        fun of(gender: String): Gender {
            return when (gender) {
                MALE.value -> MALE
                FEMALE.value -> FEMALE
                else -> ANDROGYNOUS
            }
        }

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
