package com.domain.model

import java.util.concurrent.ConcurrentHashMap

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.JavaConverters._

class InventoryTest extends FunSuite with BeforeAndAfter with Matchers {

  var itemList = new ConcurrentHashMap[String, BigDecimal]().asScala

  itemList +=(("AppleFoo" -> BigDecimal(0.35)), ("BananaFoo" -> BigDecimal(0.20)), ("MelonFoo" -> BigDecimal(0.50)), ("LimeFoo" -> BigDecimal(0.15)))

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

}
