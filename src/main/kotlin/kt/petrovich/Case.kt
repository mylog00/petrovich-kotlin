package kt.petrovich;

/**
 * @param rusName case in russian language
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
enum class Case(val rusName: String) {
    NOMINATIVE("ИМЕНИТЕЛЬНЫЙ"),
    GENITIVE("РОДИТЕЛЬНЫЙ"),
    DATIVE("ДАТЕЛЬНЫЙ"),
    ACCUSATIVE("ВИНИТЕЛЬНЫЙ"),
    INSTRUMENTAL("ТВОРИТЕЛЬНЫЙ"),
    PREPOSITIONAL("ПРЕДЛОЖНЫЙ");
}
