package kt.petrovich.exceptoins

/**
 * @author Dmitrii Kniazev
 * @since 30.09.2017
 */
class RulesCreationException : RuntimeException {
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(message: String) : super(message)
}
