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
        BUY -> coffeeMachine.buyCoffee()
        EXIT -> throw ExitException()
        else -> throw Exception()
    }.apply { println() }
}

/**
 *  function to print an Exception's message to screen
 *  @param e any exception inheriting from the Exception class
 */
fun printExceptionMessage(e: Exception) = println(INVALID_ENTRY.plus("${e.message}\n"))