package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class VendingMachineSpec : Spek({
    val WEIGHT_OF_NICKEL = "5.000" //Grams
    val WEIGHT_OF_DIME = "2.268"
    val WEIGHT_OF_QUARTER = "5.670"
    val WEIGHT_OF_PENNY = "2.500"

    var vendingMachine = VendingMachine()
    beforeEachTest { vendingMachine = VendingMachine() }
    describe("a vending machine processing coins") {
        on("not having received a coin") {
            it("will display 'INSERT COIN'") {
                assertEquals("INSERT COIN", vendingMachine.printDisplay())
            }
        }
        on("receiving a nickel") {
            vendingMachine.insertCoin(WEIGHT_OF_NICKEL)
            it("will increase the balance by by 5 cents.") {
                assertEquals("USD0.05", vendingMachine.printDisplay())
            }
        }
        on("receiving a dime") {
            vendingMachine.insertCoin(WEIGHT_OF_DIME)
            it("will increase the balance by 10 cents") {
                assertEquals("USD0.10", vendingMachine.printDisplay())
            }
        }
        on("receiving a quarter") {
            vendingMachine.insertCoin(WEIGHT_OF_QUARTER)
            it("will increase the balance by 25 cents") {
                assertEquals("USD0.25", vendingMachine.printDisplay())
            }
        }
        on("receiving a penny") {
            vendingMachine.insertCoin(WEIGHT_OF_PENNY)
            it("will place the penny in the coin return"){
                assertTrue(vendingMachine.coinReturn.contains(Coin.PENNY))
            }
        }
        on("receiving several coins") {
            repeat(2){vendingMachine.insertCoin(WEIGHT_OF_QUARTER)}
            vendingMachine.insertCoin(WEIGHT_OF_DIME)
            vendingMachine.insertCoin(WEIGHT_OF_PENNY)
            vendingMachine.insertCoin(WEIGHT_OF_PENNY)
            vendingMachine.insertCoin("0.95")
            it("will display a balance matching the total of the coins"){
                assertEquals("USD0.60", vendingMachine.printDisplay())
            }
            it("will have two pennies and an unknown coin in the output slot"){
                assertEquals(listOf(Coin.PENNY, Coin.PENNY, Coin.UNKNOWN), vendingMachine.coinReturn)
            }
        }
    }
    describe("a vending machine processing sales") {
        on("selecting a cola with exact change") {
            repeat(4){vendingMachine.insertCoin(WEIGHT_OF_QUARTER)}
            it("will have a cola in the product bin"){
                vendingMachine.dispense(Product.COLA)
                assertTrue(vendingMachine.productBin.contains(Product.COLA))
            }
            it("will display thank you on the screen"){
                assertEquals("THANK YOU", vendingMachine.printDisplay())
            }
            it("will then display INSERT COIN on the screen"){
                assertEquals("INSERT COIN", vendingMachine.printDisplay())
            }
        }
        on("selecting a cola with a larger balance") {
            repeat(6){vendingMachine.insertCoin(WEIGHT_OF_QUARTER)}
            it("will have a cola in the product bin"){
                vendingMachine.dispense(Product.COLA)
                assertTrue(vendingMachine.productBin.contains(Product.COLA))
            }
            it("will display thank you on the screen"){
                assertEquals("THANK YOU", vendingMachine.printDisplay())
            }
            it("will then dispense coins into the output matching remaining amount"){
                assertEquals(listOf(Coin.QUARTER, Coin.QUARTER), vendingMachine.coinReturn)
            }
        }
        on("using another larger balance"){
            repeat(6){vendingMachine.insertCoin(WEIGHT_OF_QUARTER)}
            vendingMachine.insertCoin(WEIGHT_OF_DIME)
            vendingMachine.insertCoin(WEIGHT_OF_PENNY)
            it("will have a cola in the product bin"){
                vendingMachine.dispense(Product.COLA)
                assertTrue(vendingMachine.productBin.contains(Product.COLA))
            }
            it("will display thank you on the screen"){
                assertEquals("THANK YOU", vendingMachine.printDisplay())
            }
            it("will then dispense coins into the output matching remaining amount"){
                assertEquals(listOf(Coin.PENNY, Coin.QUARTER, Coin.QUARTER, Coin.DIME), vendingMachine.coinReturn)
                assertEquals("INSERT COIN", vendingMachine.printDisplay())
            }
        }
        on("selecting a cola with insufficient balance") {
            repeat(2){vendingMachine.insertCoin(WEIGHT_OF_QUARTER)}
            vendingMachine.dispense(Product.COLA)
            it("will display the price of the item"){
                val expectedColaPrice = vendingMachine.formatter.format(Product.COLA.cost)
                assertEquals(expectedColaPrice,vendingMachine.printDisplay())
            }
            it("will then display the current balance and not dispense a cola"){
                assertEquals("USD0.50",vendingMachine.printDisplay())
                assertEquals(listOf<Product>(),vendingMachine.productBin)
            }
        }
    }
})


