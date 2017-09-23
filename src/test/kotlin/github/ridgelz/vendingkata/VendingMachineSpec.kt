package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class VendingMachineSpec : Spek({
    var vendingMachine = VendingMachine()
    describe("a vending machine") {
        beforeEachTest { vendingMachine = VendingMachine() }
        on("receiving a nickel") {
            it("will increase the balance by by 5 cents.") {
                vendingMachine.insertCoin("5")
                assertEquals("USD0.05", vendingMachine.printBalance())
            }
        }
        on("receiving a dime") {
            it("will increase the balance by 10 cents") {
                vendingMachine.insertCoin("2.268")
                assertEquals("USD0.10", vendingMachine.printBalance())
            }
        }
        on("receiving a quarter") {
            it("will increase the balance by 25 cents") {
                vendingMachine.insertCoin("5.670")
                assertEquals("USD0.25", vendingMachine.printBalance())
            }
        }
        on("receiving a penny") {
            it("will place the penny in the coin return"){
                vendingMachine.insertCoin("2.500")
                assertTrue(vendingMachine.coinReturn.contains(Coin.PENNY))
            }
        }
    }
})


