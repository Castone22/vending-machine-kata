package github.ridgelz.vendingkata

import java.util.*
import javax.money.Monetary
import javax.money.MonetaryAmount
import javax.money.format.MonetaryFormats
import javax.money.format.MonetaryAmountFormat
import github.ridgelz.vendingkata.Coin.*

class VendingMachine {
    private val locale: Locale = Locale.US
    private val validCoins: List<Coin> = Arrays.asList(NICKEL, DIME, QUARTER)
    private var dispensed = false
    private var failedProduct: Product = Product.UNKNOWN
    private var balance: MonetaryAmount = valueOfCoin(0.00, Monetary.getCurrency(locale))
    val formatter: MonetaryAmountFormat = MonetaryFormats.getAmountFormat(locale)
    val coinReturn: MutableCollection<Coin> = mutableListOf()
    val productBin: MutableCollection<Product> = mutableListOf()

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
            balance = balance.subtract(product.cost)
            populateCoinReturn()
            productBin.add(product)
            dispensed = true
        } else {
            failedProduct = product
        }
    }

    fun returnCoins() {
        populateCoinReturn()
    }


    private fun populateCoinReturn(){
        for(coin in Coin.values().dropLast(1).reversed()) {
            while(balance >= coin.value){
                coinReturn.add(coin)
                balance = balance.subtract(coin.value)
            }
        }
    }

}