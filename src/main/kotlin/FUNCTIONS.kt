package machine

import machine.model.CoffeeMachine
import machine.model.ExitException

// PROMPT ACTIONS
/**
 *  function prompting user for input: buy, fill, take, remaining, exit
 *  @param coffeeMachine data class housing coffee machine supply status
 *  @throws ExitException caught & handled when safely exiting the program
 *  @throws Exception caught & handled when receiving invalid inputs
 */
fun promptAction(coffeeMachine: CoffeeMachine) {
    println(ACTION_PROMPT)
    val input = readln().lowercase().apply { println() }
    when (input) {
        REMAINING -> coffeeMachine.printStatus()
        TAKE -> coffeeMachine.takeMoney()
        FILL -> coffeeMachine.fillResources()
        // TODO - buy - multi-steps
        BUY -> {  }
        EXIT -> throw ExitException()
        else -> throw Exception()
    }.apply { println() }
}

// PRINT EXCEPTION MESSAGE ERROR
/**
 *  function to print an Exception's message to screen
 *  @param e any exception inheriting from the Exception class
 */
fun printExceptionMessage(e: Exception) = println("\nInvalid entry - ${e.message}\n")

// BUY ACTION - BUY COFFEE
fun buyCoffee(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    println(ACTION_BUY)
    return when (readln().toInt()) {
        ONE -> makeEspresso(machineSupplyStatus)
        TWO -> makeLatte(machineSupplyStatus)
        THREE -> makeCappuccino(machineSupplyStatus)
        else -> throw Exception()
    }.apply { println() }
}

// MAKE ESPRESSO
fun makeEspresso(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    machineSupplyStatus.keys.forEach {
        when (it) {
            MACHINE_CUPS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - ONE
            MACHINE_WATER -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - ESPRESSO_WATER_COST
            MACHINE_BEANS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - ESPRESSO_BEANS_COST
            MACHINE_MONEY -> machineSupplyStatus[it] = machineSupplyStatus[it]!! + ESPRESSO_MONEY_COST
        }
    }
    return machineSupplyStatus
}

// MAKE LATTE
fun makeLatte(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    machineSupplyStatus.keys.forEach {
        when (it) {
            MACHINE_CUPS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - ONE
            MACHINE_WATER -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - LATTE_WATER_COST
            MACHINE_MILK -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - LATTE_MILK_COST
            MACHINE_BEANS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - LATTE_BEANS_COST
            MACHINE_MONEY -> machineSupplyStatus[it] = machineSupplyStatus[it]!! + LATTE_MONEY_COST
        }
    }
    return machineSupplyStatus
}

// MAKE CAPPUCCINO
fun makeCappuccino(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    machineSupplyStatus.keys.forEach {
        when (it) {
            MACHINE_CUPS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - ONE
            MACHINE_WATER -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - CAPPUCCINO_WATER_COST
            MACHINE_MILK -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - CAPPUCCINO_MILK_COST
            MACHINE_BEANS -> machineSupplyStatus[it] = machineSupplyStatus[it]!! - CAPPUCCINO_BEANS_COST
            MACHINE_MONEY -> machineSupplyStatus[it] = machineSupplyStatus[it]!! + CAPPUCCINO_MONEY_COST
        }
    }
    return machineSupplyStatus
}