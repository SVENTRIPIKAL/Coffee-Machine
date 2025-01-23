package machine

import machine.model.ExitException

fun main() {
    while (true) {
        try {
            // map of machine supply status
            val machineSupplyStatus = mutableMapOf(
                MACHINE_WATER to MACHINE_WATER_DEFAULT,
                MACHINE_MILK to MACHINE_MILK_DEFAULT,
                MACHINE_BEANS to MACHINE_BEANS_DEFAULT,
                MACHINE_CUPS to MACHINE_CUPS_DEFAULT,
                MACHINE_MONEY to MACHINE_MONEY_DEFAULT
            )
            // prompt action
            promptAction(machineSupplyStatus)
            // catch exceptions
        } catch (e: Exception) {
            if (e is ExitException) break                       // exit loop
            else println("\nInvalid entry - ${e.message}\n")    // print error
        }
    }
}