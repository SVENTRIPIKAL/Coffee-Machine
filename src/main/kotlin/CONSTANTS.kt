package machine

// CONSTANTS
const val ZERO = 0
const val ONE = 1
const val TWO = 2
const val THREE = 3
const val ASTERISK = "*"

// MACHINE STATUS
const val MACHINE_STATUS = "The coffee machine has:"
const val MACHINE_WATER = "* ml of water"
const val MACHINE_WATER_DEFAULT = 400
const val MACHINE_MILK = "* ml of milk"
const val MACHINE_MILK_DEFAULT = 540
const val MACHINE_BEANS = "* g of coffee beans"
const val MACHINE_BEANS_DEFAULT = 120
const val MACHINE_CUPS = "* disposable cups"
const val MACHINE_CUPS_DEFAULT = 9
const val MACHINE_MONEY = "$* of money"
const val MACHINE_MONEY_DEFAULT = 550

// ACTIONS
const val BUY = "buy"
const val FILL = "fill"
const val TAKE = "take"
const val EXIT = "exit"
const val REMAINING = "remaining"
const val ACTION_PROMPT = "Write action ($BUY, $FILL, $TAKE, $REMAINING, $EXIT):"
const val ACTION_BUY = "What do you want to buy? $ONE - espresso, $TWO - latte, $THREE - cappuccino:"
const val ACTION_TAKE = "I gave you $*"

// ESPRESSO - BUY COSTS
const val ESPRESSO_WATER_COST = 250
const val ESPRESSO_BEANS_COST = 16
const val ESPRESSO_MONEY_COST = 4

// LATTE - BUY COSTS
const val LATTE_WATER_COST = 350
const val LATTE_MILK_COST = 75
const val LATTE_BEANS_COST = 20
const val LATTE_MONEY_COST = 7

// CAPPUCCINO - BUY COSTS
const val CAPPUCCINO_WATER_COST = 200
const val CAPPUCCINO_MILK_COST = 100
const val CAPPUCCINO_BEANS_COST = 12
const val CAPPUCCINO_MONEY_COST = 6

// ACTION FILL - UPDATE MACHINE SUPPLY
const val ADD_WATER = "Write how many ml of water you want to add:"
const val ADD_MILK = "Write how many ml of milk you want to add:"
const val ADD_BEANS = "Write how many grams of coffee beans you want to add:"
const val ADD_CUPS = "Write how many disposable cups you want to add:"