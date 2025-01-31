package machine.model

/**
 *  custom exception used to exit from while loop
 *  @throws ExitException
 */
class ExitException: Exception()

/**
 *  custom exception used to notify user of lack of resources
 *  @param resource the String value of the insufficient resource
 *  @throws InsufficientResourceException
 */
class InsufficientResourceException(resource: String): Exception(resource)