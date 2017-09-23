package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class VendingMachineSpec : Spek({
    var vendingMachine: VendingMachine = VendingMachine()
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
    }
})


