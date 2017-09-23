package github.ridgelz.vendingkata

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.*

class CoinSpec: Spek ({
  on("identifying coins"){
    it("is given a coin weighing 5 grams, it should give a nickel"){
        assertEquals(Coin.NICKEL, findCoinByWeight(5.000))
    }

  }
})