package machine.model

import machine.*
import kotlin.math.absoluteValue

/**
 *  data class housing coffee machine supply status
 *  @param water list of resource status, current total ml, and add water strings
 *  @param milk list of resource status, current total ml, and add milk strings
 *  @param beans list of resource status, current total g, and add bean strings
 *  @param cups list of resource status, current total, and add cup strings
 *  @param money list of resource status & current total currency strings
 */
data class CoffeeMachine(
    val water: MutableList<String> = mutableListOf(MACHINE_WATER, "$MACHINE_WATER_DEFAULT", ADD_WATER),
    val milk: MutableList<String> = mutableListOf(MACHINE_MILK, "$MACHINE_MILK_DEFAULT", ADD_MILK),
    val beans: MutableList<String> = mutableListOf(MACHINE_BEANS, "$MACHINE_BEANS_DEFAULT", ADD_BEANS),
    val cups: MutableList<String> = mutableListOf(MACHINE_CUPS, "$MACHINE_CUPS_DEFAULT", ADD_CUPS),
    val money: MutableList<String> = mutableListOf(MACHINE_MONEY, "$MACHINE_MONEY_DEFAULT")
) {
    /**
     *  prints status of all resources in coffee machine to screen
     *  @see CoffeeMachine
     */
    fun printStatus() {
        """
            ${println(MACHINE_STATUS)}
            ${printResource(water)}
            ${printResource(milk)}
            ${printResource(beans)}
            ${printResource(cups)}
            ${printResource(money)}
        """.trimIndent()
    }

    /**
     *  removes money from coffee machine & sets currency total to 0
     *  @see CoffeeMachine
     */
    fun takeMoney() {
        println(ACTION_TAKE.replace(ASTERISK, money[ONE]))
        money[ONE] = "$ZERO"
    }

    /**
     *  prompts user for input & increases resource total by provided input.
     *  catches & handles invalid entries & loops until valid entry is entered
     *  @see CoffeeMachine
     */
    fun fillResources() {
        listOf(water, milk, beans, cups).forEach { resource ->
            when (resource) {
                water,
                milk,
                beans,
                cups -> {
                    while (true) {  // loop until valid entry entered
                        try {
                            println(resource.last())                    // prompt input
                            val input = readln().toInt().absoluteValue  // read positive value
                            val total = resource[ONE].toInt()           // get current resource value
                            resource[ONE] = "${input + total}"          // update resource total
                            throw ExitException()
                        } catch (e: Exception) {
                            if (e is ExitException) break               // exit loop
                            else printExceptionMessage(e)               // print error
                        }
                    }
                }
            }
        }
    }

    /**
     *  prints resource string & replaces Asterisk with current resource value
     *  @param resource list of strings related to provided resource
     */
    private fun printResource(resource: List<String>) = println(resource.first().replace(ASTERISK, resource[ONE]))
}