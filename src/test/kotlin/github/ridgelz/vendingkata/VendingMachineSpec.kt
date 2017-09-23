package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class VendingMachineSpec : Spek({

    on("receiving a coin") {
        val vendingMachine = VendingMachine()
        it("receives a nickel, the balance will be increased by 5 cents.") {
            vendingMachine.insertCoin("5 grams")
            assertEquals("$0.05", vendingMachine.balance)
        }
        it("receives a dime, the balance will be increased by 10 cents"){
            vendingMachine.insertCoin("2.268 grams")
            assertEquals("0.10", vendingMachine.balance)
        }
    }

})


