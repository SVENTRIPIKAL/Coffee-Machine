package machine

import machine.model.CoffeeMachine
import machine.model.ExitException

fun main() {
    // coffee machine data class holding respective resource values
    val coffeeMachine = CoffeeMachine(
        mutableListOf(MACHINE_WATER, MACHINE_WATER_DEFAULT.toString(), ADD_WATER),
        mutableListOf(MACHINE_MILK, MACHINE_MILK_DEFAULT.toString(), ADD_MILK),
        mutableListOf(MACHINE_BEANS, MACHINE_BEANS_DEFAULT.toString(), ADD_BEANS),
        mutableListOf(MACHINE_CUPS, MACHINE_CUPS_DEFAULT.toString(), ADD_CUPS),
        mutableListOf(MACHINE_MONEY, MACHINE_MONEY_DEFAULT.toString())
    )
    // loop while program running
    while (true) {
        try {
            // prompt action
            promptAction(coffeeMachine)
            // catch exceptions
        } catch (e: Exception) {
            if (e is ExitException) break                       // exit loop
            else println("\nInvalid entry - ${e.message}\n")    // print error
        }
    }
}
