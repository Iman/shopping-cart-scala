
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
sbt clean run
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
sbt clean test
```