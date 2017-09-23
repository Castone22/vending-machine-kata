package github.ridgelz.vendingkata

import java.util.*
import javax.money.Monetary
import javax.money.MonetaryAmount

enum class Coin(val weight: Double, val value: MonetaryAmount) {
    NICKEL(5.000, valueOfCoin(0.05, Monetary.getCurrency(Locale.US))),
    DIME(2.268, valueOfCoin(0.10, Monetary.getCurrency(Locale.US))),
    QUARTER(5.670, valueOfCoin(0.25, Monetary.getCurrency(Locale.US)))
}





