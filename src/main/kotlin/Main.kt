package machine

import machine.model.CoffeeMachine
import machine.model.ExitException

fun main() {
    // coffee machine data class holding default resource values
    val coffeeMachine = CoffeeMachine()
    // loop while program running
    while (true) {
        try {
            // prompt action
            promptAction(coffeeMachine)
            // catch exceptions
        } catch (e: Exception) {
            if (e is ExitException) break   // exit loop
            else printExceptionMessage(e)   // print error
        }
    }
}