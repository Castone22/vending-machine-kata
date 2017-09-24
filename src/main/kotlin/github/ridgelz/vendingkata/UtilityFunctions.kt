package github.ridgelz.vendingkata

import javax.money.CurrencyUnit
import javax.money.Monetary
import javax.money.MonetaryAmount

fun valueOfCoin(coinValue: Double, currency: CurrencyUnit): MonetaryAmount {
    return Monetary.getDefaultAmountFactory().setNumber(coinValue).setCurrency(currency).create()
}

fun findCoinByWeight(weight: Double): Coin {
    return Coin.values().find { it.weight == weight } ?: Coin.UNKNOWN
}