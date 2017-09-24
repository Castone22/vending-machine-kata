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
    var productBin: MutableCollection<Product> = mutableListOf()
    private var dispensed = false
    private var failedProduct: Product = Product.UNKNOWN

    fun insertCoin(s: String) {
        val coinType = findCoinByWeight(s.toDouble())
        if(validCoins.contains(coinType)) {
            balance = balance.add(coinType.value)
        } else {
            coinReturn.add(coinType)
        }

    }

    fun printDisplay(): String {
        var displayString = formatter.format(balance)
        if (displayString == "USD0.00") displayString = "INSERT COIN"
        if (failedProduct != Product.UNKNOWN){
            displayString = formatter.format(failedProduct.cost)
            failedProduct = Product.UNKNOWN
        }
        if (dispensed){
            displayString = "THANK YOU"
            dispensed = false
        }
        return displayString
    }


    fun dispense(product: Product) {
        if(balance >= product.cost) {
            coinReturn = populateCoinReturn(product.cost)
            balance = balance.subtract(product.cost)
            productBin.add(product)
            dispensed = true
        } else {
            failedProduct = product
        }
    }

    private fun populateCoinReturn(productCost: MonetaryAmount): MutableList<Coin> {

        return mutableListOf(Coin.QUARTER, Coin.QUARTER)
    }

}