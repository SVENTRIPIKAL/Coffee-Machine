package machine.model

import machine.ASTERISK
import machine.MACHINE_STATUS
import machine.ONE

/**
 *  data class housing coffee machine supply status
 *  @param water list of resource status, default total ml, and add water strings
 *  @param milk list of resource status, default total ml, and add milk strings
 *  @param beans list of resource status, default total g, and add bean strings
 *  @param cups list of resource status, default total, and add cup strings
 *  @param money list of resource status & default total currency strings
 */
data class CoffeeMachine(
    val water: MutableList<String>,
    val milk: MutableList<String>,
    val beans: MutableList<String>,
    val cups: MutableList<String>,
    val money: MutableList<String>
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
     *  prints resource string & replaces Asterisk with current resource value
     *  @param list list of strings related to provided resource
     */
    private fun printResource(list: List<String>) = println(list.first().replace(ASTERISK, list[ONE]))
}