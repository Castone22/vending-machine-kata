# vending-machine-kata

A kotlin backend for a vending machine.

## build

```bash
git clone https://github.com/Castone22/vending-machine-kata.git
cd vending-machine-kata
./gradlew clean build
```

Just tests can be run using 

```bash
./gradlew test 
```

Note: this will say it was skipped, though the previous directive, jUnitPlatform will run the actual tests in question.
"test" just happens to include this directive.

The tests can be pulled down and played with a bit to see how
the system fits together.  Currently implemented:

* Judge provided coin by weight as long as it's defined in the enum.
* Make change
* Display various messages including the balance or information on the current state of the machine.
* Dispense product.

--- 
## known issues

* Currently it has no way to actually track the quantity of
product that is in stock, meaning it will continue to try to dispense
even if it is out of say... cola.
* The machine similarly does not keep track of how many coins
it has.  Meaning it would allow someone to make a purchase
 even if it could not make change. 