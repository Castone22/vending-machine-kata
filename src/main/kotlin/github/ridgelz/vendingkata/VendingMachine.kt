package github.ridgelz.vendingkata

import org.javamoney.moneta.function.MonetaryUtil
import java.util.*
import javax.money.Monetary
import javax.money.MonetaryAmount
import javax.money.format.MonetaryFormats

class VendingMachine {
    var locale = Locale.US
    var balance: MonetaryAmount = valueOfCoin(0.00, Monetary.getCurrency(locale))
    var formatter = MonetaryFormats.getAmountFormat(locale)

    fun insertCoin(s: String) {
        balance = balance.add(findCoinByWeight(s.toDouble())?.value)
    }

    fun printBalance(): String {
        return formatter.format(balance)
    }

}