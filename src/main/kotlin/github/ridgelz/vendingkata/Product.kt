package github.ridgelz.vendingkata

import java.util.*
import javax.money.Monetary
import javax.money.MonetaryAmount


enum class Product(val cost: MonetaryAmount) {
    COLA(valueOfCoin(1.00, Monetary.getCurrency(Locale.US))),
    CHIPS(valueOfCoin(0.50, Monetary.getCurrency(Locale.US))),
    CANDY(valueOfCoin(0.65, Monetary.getCurrency(Locale.US))),
}