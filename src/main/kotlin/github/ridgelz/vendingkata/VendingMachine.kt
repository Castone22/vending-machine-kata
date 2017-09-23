package github.ridgelz.vendingkata

class VendingMachine {
    var balance: String = "$0.00"

    fun insertCoin(s: String) {
        balance = "$0.05"
    }

}