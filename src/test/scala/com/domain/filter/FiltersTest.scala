package com.domain.filter

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class FiltersTest extends FunSuite with BeforeAndAfter with Matchers {

  val offers = new Offers(Map(("Melon" -> 2), ("Lime" -> 3)))

  test("Offers applied on one Apple and one Banana (noting should be changed)") {

    val oneItemEach = List("Apple", "Banana")
    var afterOffer = offers.current(oneItemEach)
    assert(afterOffer == List("Apple", "Banana"))
  }

  test("Offers applied on one each product in basket even though we've a few Limes and Melons (noting should be changed)") {

    val order = List("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")
    var afterOffer = offers.current(order)
    assert(afterOffer == List("Apple", "Apple", "Banana", "Melon", "Lime", "Lime"))
  }

  test("Two Melons as buy one get one free (it should remove one Melon from the list) ") {

    val order = List("Melon", "Melon")
    var afterOffer = offers.current(order)
    assert(afterOffer == List("Melon"))
  }

  test("Three Limes for price of two (final list should have two Limes)") {

    val order = List("Lime", "Lime", "Lime")
    var afterOffer = offers.current(order)
    assert(afterOffer == List("Lime", "Lime"))
  }

  test("Two Melons as buy one get one free and three Limes for price of two (final list should have one Melon and one Lime)") {

    val order = List("Lime", "Lime", "Melon", "Apple", "Lime", "Apple", "Banana", "Melon")
    var afterOffer = offers.current(order)
    assert(afterOffer == List("Lime", "Apple", "Lime", "Apple", "Banana", "Melon"))
  }

  test("Testing empty list ") {

    val order = List()
    var afterOffer = offers.current(order)
    assert(afterOffer == List())
  }

}
