package github.ridgelz.vendingkata

import javax.money.Monetary
import javax.money.MonetaryAmount

fun valueOfCoin(coinValue: Double, currencyCode: String): MonetaryAmount {
    return Monetary.getDefaultAmountFactory().setNumber(coinValue).setCurrency(currencyCode).create()
}

fun findCoinByWeight(weight: Double): Coin? {
    return Coin.values().find { it.weight == weight }
}