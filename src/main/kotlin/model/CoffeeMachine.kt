package machine.model

/**
 *  data class housing coffee machine supply status
 *  @param water machine total ml of water supply
 *  @param milk machine total ml of milk supply
 *  @param beans machine total g of bean supply
 *  @param cups machine total disposable cups supply
 *  @param money machine total dollar currency supply
 */
data class CoffeeMachine(
    val water: Int,
    val milk: Int,
    val beans: Int,
    val cups: Int,
    val money: Int
)
