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
                water, milk, beans, cups -> {
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
     *  prompts user for input (1 - espresso, 2 - latte, 3 - cappuccino, back - main menu)
     *  & loops until a valid input in entered
     *  @throws Exception when invalid input is entered
     *  @throws ExitException to exit while loop
     *  @throws InsufficientResourceException not enough resources for chosen coffee
     */
    fun buyCoffee() {
        while (true) {
            try {
                println(ACTION_BUY)
                when (val input = readln().lowercase()) {   // loop until valid entry
                    "$ONE", "$TWO", "$THREE" -> makeCoffee(input)
                    BACK -> throw ExitException()
                    else -> throw Exception()
                }
            } catch (e: Exception) {
                when (e) {
                    is ExitException -> break
                    is InsufficientResourceException -> {
                        println(INSUFFICIENT_RESOURCES.replace(ASTERISK, e.localizedMessage))
                        break
                    }
                    else -> printExceptionMessage(e)
                }
            }
        }
    }

    /**
     *  prints resource string & replaces Asterisk with current resource value
     *  @param resource list of strings related to provided resource
     */
    private fun printResource(resource: List<String>) = println(resource.first().replace(ASTERISK, resource[ONE]))

    /**
     *  creates map of coffee resources & costs, checks if coffee machine
     *  has enough resources to make the chosen coffee (throws an
     *  InsufficientResourceException if not), then decreases coffee machine
     *  resources according to chosen coffee resource cost
     *  @param chosenCoffee the chosen coffee (1 - espresso, 2 - latte, 3 - cappuccino)
     *  @throws ExitException exits while loop after coffee is made
     *  @throws InsufficientResourceException not enough resources for chosen coffee
     */
    private fun makeCoffee(chosenCoffee: String) {
        // create resource map for chosen coffee
        val coffeeMap = when (chosenCoffee) {
            "$ONE" -> mapOf(
                CUPS to ONE,
                WATER to ESPRESSO_WATER_COST,
                BEANS to ESPRESSO_BEANS_COST,
                MONEY to ESPRESSO_MONEY_COST
            )
            "$TWO" -> mapOf(
                CUPS to ONE,
                WATER to LATTE_WATER_COST,
                MILK to LATTE_MILK_COST,
                BEANS to LATTE_BEANS_COST,
                MONEY to LATTE_MONEY_COST
            )
            else -> mapOf(
                CUPS to ONE,
                WATER to CAPPUCCINO_WATER_COST,
                MILK to CAPPUCCINO_MILK_COST,
                BEANS to CAPPUCCINO_BEANS_COST,
                MONEY to CAPPUCCINO_MONEY_COST
            )
        }

        // check to see if enough resources available else throw InsufficientResourceException
        sufficientResourceCheck(coffeeMap)

        // print 'making coffee' to screen
        println(SUFFICIENT_RESOURCES)

        // decrease/increase coffee machine resources according to chosen coffee cost
        coffeeMap.forEach { (resource, cost) ->
            when (resource) {
                CUPS -> decreaseCupsTotal(cost)
                WATER -> decreaseWaterTotal(cost)
                MILK -> decreaseMilkTotal(cost)
                BEANS -> decreaseBeansTotal(cost)
                MONEY -> increaseMoneyTotal(cost)
            }
        }

        // exit while loop
        throw ExitException()
    }

    /**
     *  checks coffee machine resource totals & throws
     *  InsufficientResourceException if totals are lower than cost
     *  @param coffeeMap map of chosen coffee resources & costs
     *  @throws InsufficientResourceException not enough resources for chosen coffee
     */
    private fun sufficientResourceCheck(coffeeMap: Map<String, Int>) {
        coffeeMap.forEach { (resource, cost) ->
            val enoughSupply = when (resource) {
                WATER -> water[ONE].toInt() >= cost
                MILK -> milk[ONE].toInt() >= cost
                BEANS -> beans[ONE].toInt() >= cost
                CUPS -> cups[ONE].toInt() >= cost
                MONEY -> true
                else -> false
            }
            if (!enoughSupply) throw InsufficientResourceException(resource)
        }
    }

    /**
     *  decreases coffee machine cup total by 1
     *  @param cupsCost cups needed to make coffee
     */
    private fun decreaseCupsTotal(cupsCost: Int) {
        cups[ONE] = "${cups[ONE].toInt() - cupsCost}"
    }

    /**
     *  decreases coffee machine water total by provided water cost
     *  @param waterCost water needed to make coffee
     */
    private fun decreaseWaterTotal(waterCost: Int) {
        water[ONE] = "${water[ONE].toInt() - waterCost}"
    }

    /**
     *  decreases coffee machine milk total by provided milk cost
     *  @param milkCost milk needed to make coffee
     */
    private fun decreaseMilkTotal(milkCost: Int) {
        milk[ONE] = "${milk[ONE].toInt() - milkCost}"
    }

    /**
     *  decreases coffee machine beans total by provided beans cost
     *  @param beansCost beans needed to make coffee
     */
    private fun decreaseBeansTotal(beansCost: Int) {
        beans[ONE] = "${beans[ONE].toInt() - beansCost}"
    }

    /**
     *  increases coffee machine money total by provided coffee cost
     *  @param coffeeCost the price of the chosen coffee
     */
    private fun increaseMoneyTotal(coffeeCost: Int) {
        money[ONE] = "${money[ONE].toInt() + coffeeCost}"
    }
}