package github.ridgelz.vendingkata

import javax.money.Monetary
import javax.money.MonetaryAmount

enum class Coin(val weight: Double, val value: MonetaryAmount) {
    NICKEL(5.000, valueOfCoin(0.05, "USD")),
    DIME(2.268, valueOfCoin(0.10, "USD")),
    QUARTER(5.670, valueOfCoin(0.25, "USD"));

}

fun valueOfCoin(coinValue: Double, currencyCode: String): MonetaryAmount{
    return Monetary.getDefaultAmountFactory().setNumber(coinValue).setCurrency(currencyCode).create()
}

fun findCoinByWeight(weight: Double): Coin? {
    return Coin.values().find { it.weight == weight }
}


