package com.domain.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.concurrent.TrieMap

@RunWith(classOf[JUnitRunner])
class InventoryTest extends FunSuite with BeforeAndAfter with Matchers {

  val itemList = TrieMap("AppleFoo" -> BigDecimal(0.35), "BananaFoo" -> BigDecimal(0.20), "MelonFoo" -> BigDecimal(0.50), "LimeFoo" -> BigDecimal(0.15))

  val inventory = new Items(itemList)

  test("Inventory should have Apple, Banana, Melons and Lime (4 items)") {

    assert(inventory.items.size == itemList.size)
  }

  test("Finding the items items by name") {

    assert(inventory.find("MelonFoo") == 0.50)
  }

  test("Lookup for non existing item in items") {

    assert(inventory.find("Foo") == null)
  }

  test("Add an item to the items list") {

    inventory("Orange", 0.80)

    assert(inventory.items.size == 5)
    assert(inventory.items.contains("Orange"))
  }

  test("remove an existing item from the items") {

    inventory.unapply("Orange")

    assert(inventory.items.size == 4)
    assert(!inventory.items.contains("Orange"))
  }

  test("try to remove none existing item from the items") {

    assert(inventory.unapply("Platonia") == None)
  }

  test("test add items") {

    val list = TrieMap("Apple" -> BigDecimal(0.35), "Banana" -> BigDecimal(0.35))

    assert(Items(list).items.size == 2)
  }

  test("test add an item into and existing list") {

    val list = TrieMap("Apple" -> BigDecimal(32), "Banana" -> BigDecimal(7))
    val items = Items(list)("Cucumber", 9)

    assert(items.size == 3)
  }

  test("test find an item") {

    val list = TrieMap("Orange" -> BigDecimal(5), "Mango" -> BigDecimal(0.4))
    assert(Items(list).find("Orange") == 5)
  }

  test("test remove and item") {

    val list = TrieMap("Orange" -> BigDecimal(5), "Mango" -> BigDecimal(0.4))
    val items = Items(list) unapply ("Orange")

    assert(items.size == 1)
  }

  test("test remove when item does not exist") {

    val list = TrieMap("Orange" -> BigDecimal(5), "Mango" -> BigDecimal(0.4))
    val items = Items(list) unapply ("Boo")

    assert(items.size == 0)
  }

}
