
#Credit Suisse Scala (Exercise)

The Shop has been implemented as a command-line application. Using any language you like, write a simple program that calculates the price of a basket of shopping. 
Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana". Multiple items are present multiple times in the list, so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.

Items are priced as follows:
- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as ‘buy one get one free’
- Limes are 15p each, but are available in a ‘three for the price two’ offer

Given a list of shopping, calculate the total cost of those items.

## To run the application:

```
sbt test
sbt run
```


### To cleanup and remove the target directory

```
sbt clean 
```
or
```
find . -name target -type d -exec rm -rf {} \;
```

## Running Test

```
	[info] CartFeaturesTest:
	[info] I want to get the price list from an array 
	[info] So I can have the itemized list with prices 
	[info] And get the orders price list 
	[info] And get the order total 
	[info] Add more item into the list 
	[info] And get the orders price list with new added items 
	[info] And get the order total at last order 
	[info] Feature: Cart object
	[info]   Scenario: Create the cart object with loaded inventory
	[info]     Given Cart object is created and price list has retrieved 
	[info]     Then the itemized list is created 
	[info]     Then the basic list should have both product name and price 
	[info]     Then the total amount 
	[info]     Then the extra item should be set 
	[info]     Then the full list should have both products and prices 
	[info]     Then the last order total amount 
	[info] OffersTest:
	[info] - Offers applied on one Apple and one Banana (noting should be changed)
	[info] - Offers applied on one each product in basket even though we've a few Limes and Melons (noting should be changed)
	[info] - Two Melons as buy one get one free (it should remove one Melon from the list) 
	[info] - Three Limes for price of two (final list should have two Limes)
	[info] - Two Melons as buy one get one free and three Limes for price of two (final list should have one Melon and one Lime)
	[info] - Four Melons as buy one get one free and six Limes for price of two (final list should have four Limes and two Melon)
	[info] - Five Melons as buy one get one free and seven Limes for price of two (final list should have five Limes and three Melon)
	[info] - Testing empty list 
	[info] InventoryTest:
	[info] - Inventory should have Apple, Banana, Melons and Lime (4 items)
	[info] - Inventory total price should be 1.20p
	[info] - Finding the inventory items by name
	[info] - Lookup for non existing item in inventory
	[info] - Inventory actual total price should be same as expected with dummy list
	[info] - Add an item to the inventory
	[info] - remove an item from the inventory
	[info] CartTest:
	[info] - Test get item prices
	[info] - Test get item prices with a few of each items
	[info] - Test itemized price list
	[info] - Test total
	[info] Run completed in 493 milliseconds.
	[info] Total number of tests run: 20
	[info] Suites: completed 4, aborted 0
	[info] Tests: succeeded 20, failed 0, canceled 0, ignored 0, pending 0
	[info] All tests passed.
	[success] Total time: 7 s, completed 26-Jul-2015 17:53:50
```
## Running the app

```
	[info] Running com.creditsuisse.Main 
	Basket:
	Melon________0.50
	Melon________0.50
	Apple________0.35
	Lime________0.15
	Lime________0.15
	Lime________0.15
	Apple________0.35
	Banana________0.20
	Total: 2.35
```

```
	Items in basket after offer applied (Three Limes for the price two and buy one Melon get one free):
	Apple________0.35
	Lime________0.15
	Apple________0.35
	Banana________0.20
	Melon________0.50
	Lime________0.15
	Total after offer deductions: 1.70
```
