package machine

import machine.model.ExitException

// PROMPT ACTIONS
fun promptAction(machineSupplyStatus: MutableMap<String, Int>) {
    println(ACTION_PROMPT)
    printMachineSupplyStatus(
        when (readln().lowercase()) {
            REMAINING -> machineSupplyStatus
            BUY -> buyCoffee(machineSupplyStatus)
            FILL -> updateMachineSupply(machineSupplyStatus)
            TAKE -> takeMachineMoney(machineSupplyStatus)
            EXIT -> throw ExitException()
            else -> throw Exception()
        }.apply { println() }
    )
}

// PRINT MACHINE STATUS
fun printMachineSupplyStatus(machineSupplyStatus: MutableMap<String, Int>) {
    println(MACHINE_STATUS)
    machineSupplyStatus.forEach { (key, value) ->
        println(key.replace(ASTERISK, value.toString()))
    }.apply { println() }
}

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

// FILL ACTION - UPDATE MACHINE SUPPLY
fun updateMachineSupply(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    val prompts = listOf(ADD_WATER, ADD_MILK, ADD_BEANS, ADD_CUPS)
    prompts.forEach {
        when (it) {
            ADD_WATER -> machineSupplyStatus[MACHINE_WATER] = machineSupplyStatus[MACHINE_WATER]!! + promptInput(it)
            ADD_MILK -> machineSupplyStatus[MACHINE_MILK] = machineSupplyStatus[MACHINE_MILK]!! + promptInput(it)
            ADD_BEANS -> machineSupplyStatus[MACHINE_BEANS] = machineSupplyStatus[MACHINE_BEANS]!! + promptInput(it)
            ADD_CUPS  -> machineSupplyStatus[MACHINE_CUPS] = machineSupplyStatus[MACHINE_CUPS]!! + promptInput(it)
        }
    }.apply { println() }
    return machineSupplyStatus
}

// PROMPT USER INPUT
fun promptInput(prompt: String): Int {
    println(prompt)
    return readln().toInt()
}

// TAKE ACTION - TAKE MONEY FROM MACHINE
fun takeMachineMoney(machineSupplyStatus: MutableMap<String, Int>): MutableMap<String, Int> {
    val moneyTaken = machineSupplyStatus[MACHINE_MONEY]
    println(ACTION_TAKE.replace(ASTERISK, moneyTaken.toString())).apply { println() }
    machineSupplyStatus[MACHINE_MONEY] = ZERO
    return machineSupplyStatus
}