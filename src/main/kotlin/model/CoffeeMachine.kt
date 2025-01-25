package machine.model

import machine.*

/**
 *  data class housing coffee machine supply status
 *  @param water list of resource status, default total ml, and add water strings
 *  @param milk list of resource status, default total ml, and add milk strings
 *  @param beans list of resource status, default total g, and add bean strings
 *  @param cups list of resource status, default total, and add cup strings
 *  @param money list of resource status & default total currency strings
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
            ${println()}
            $MACHINE_STATUS
            ${printResource(water)}
            ${printResource(milk)}
            ${printResource(beans)}
            ${printResource(cups)}
            ${printResource(money)}
        """.trimIndent()
    }

    /**
     *  removes money from coffee machine & updates currency status to 0
     *  @see CoffeeMachine
     */
    fun takeMoney() {
        println("\n${ACTION_TAKE.replace(ASTERISK, money[ONE])}")
        money[ONE] = "$ZERO"
    }

    /**
     *  prints resource string & replaces Asterisk with current resource value
     *  @param list list of strings related to provided resource
     */
    private fun printResource(list: List<String>) = println(list.first().replace(ASTERISK, list[ONE]))
}