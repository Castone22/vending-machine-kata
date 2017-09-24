package github.ridgelz.vendingkata

import java.util.*
import javax.money.Monetary
import javax.money.MonetaryAmount
import javax.money.format.MonetaryFormats
import javax.money.format.MonetaryAmountFormat
import github.ridgelz.vendingkata.Coin.*

class VendingMachine {
    var locale: Locale = Locale.US
    var balance: MonetaryAmount = valueOfCoin(0.00, Monetary.getCurrency(locale))
    var formatter: MonetaryAmountFormat = MonetaryFormats.getAmountFormat(locale)
    var validCoins: List<Coin> = Arrays.asList(NICKEL, DIME, QUARTER)
    var coinReturn: MutableCollection<Coin> = mutableListOf()

    fun insertCoin(s: String) {
        val coinType = findCoinByWeight(s.toDouble())
        if(validCoins.contains(coinType)) {
            balance = balance.add(coinType.value)
        } else {
            coinReturn.add(coinType)
        }

    }

    fun printBalance(): String {
        var balanceString = formatter.format(balance)
        if (balanceString == "USD0.00") balanceString = "INSERT COIN"
        return balanceString
    }

}