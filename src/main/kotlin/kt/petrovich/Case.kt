package kt.petrovich

/**
 * @param rusName case in russian language
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
enum class Case(private val rusName: String) {
    NOMINATIVE("именительный"),
    GENITIVE("родительный"),
    DATIVE("дательный"),
    ACCUSATIVE("винительный"),
    INSTRUMENTAL("творительный"),
    PREPOSITIONAL("предложный");
}
