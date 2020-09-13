package kt.petrovich

/**
 * @author Dmitrii Kniazev
 * @since 9/13/2020
 */
class NameTestCase(val case: Case, val gender: Gender)
object Commons {
    val PETROVICH: Petrovich = Petrovich()

    private fun getCase(case: String): Case {
        return when (case) {
            "рд" -> Case.GENITIVE
            "дт" -> Case.DATIVE
            "вн" -> Case.ACCUSATIVE
            "тв" -> Case.INSTRUMENTAL
            "пр" -> Case.PREPOSITIONAL
            else -> Case.NOMINATIVE
        }
    }

    fun getGender(case: String): Gender {
        return when (case) {
            "мр" -> Gender.MALE
            "жр" -> Gender.FEMALE
            else -> Gender.ANDROGYNOUS
        }
    }

    fun parseTestCase(str: String): NameTestCase {
        val split = str.split(',', limit = 3)
        val gender = Commons.getGender(split[0])
        val case = Commons.getCase(split[2])
        return NameTestCase(case, gender)
    }
}
