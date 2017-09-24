package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class VendingMachineSpec : Spek({
    var vendingMachine = VendingMachine()
    beforeEachTest { vendingMachine = VendingMachine() }
    describe("a vending machine processing coins") {
        on("not having received a coin") {
            it("will display 'INSERT COIN'") {
                assertEquals("INSERT COIN", vendingMachine.printDisplay())
            }
        }
        on("receiving a nickel") {
            vendingMachine.insertCoin("5")
            it("will increase the balance by by 5 cents.") {
                assertEquals("USD0.05", vendingMachine.printDisplay())
            }
        }
        on("receiving a dime") {
            vendingMachine.insertCoin("2.268")
            it("will increase the balance by 10 cents") {
                assertEquals("USD0.10", vendingMachine.printDisplay())
            }
        }
        on("receiving a quarter") {
            vendingMachine.insertCoin("5.670")
            it("will increase the balance by 25 cents") {
                assertEquals("USD0.25", vendingMachine.printDisplay())
            }
        }
        on("receiving a penny") {
            vendingMachine.insertCoin("2.500")
            it("will place the penny in the coin return"){
                assertTrue(vendingMachine.coinReturn.contains(Coin.PENNY))
            }
        }
    }
    describe("a vending machine processing sales") {
        on("selecting a cola") {
            repeat(4){vendingMachine.insertCoin("5.670")}
            vendingMachine.dispense(Product.COLA)
            it("will have a cola in the product bin"){
                assertTrue(vendingMachine.productBin.contains(Product.COLA))
            }
            it("will display thank you on the screen"){
                assertEquals("THANK YOU", vendingMachine.printDisplay())
            }
            it("will then display INSERT COIN on the screen"){
                assertEquals("INSERT COIN", vendingMachine.printDisplay())
            }
        }
    }
})


