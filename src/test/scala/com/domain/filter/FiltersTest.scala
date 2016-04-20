package com.domain.filter

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class FiltersTest extends FunSuite with BeforeAndAfter with Matchers {

  val offers = new Offers(Map(("Melon" -> 2), ("Lime" -> 3)))

  test("Offers applied on one Apple and one Banana (noting should be changed)") {

    val oneItemEach = ArrayBuffer("Apple", "Banana")
    var afterOffer = offers.current(oneItemEach)
    assert(afterOffer == ArrayBuffer("Apple", "Banana"))
  }

  test("Offers applied on one each product in basket even though we've a few Limes and Melons (noting should be changed)") {

    val order = ArrayBuffer("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")
    var afterOffer = offers.current(order)
    assert(afterOffer == ArrayBuffer("Apple", "Apple", "Banana", "Melon", "Lime", "Lime"))
  }

  test("Two Melons as buy one get one free (it should remove one Melon from the ArrayBuffer) ") {

    val order = ArrayBuffer("Melon", "Melon")
    var afterOffer = offers.current(order)
    assert(afterOffer == ArrayBuffer("Melon"))
  }

  test("Three Limes for price of two (final ArrayBuffer should have two Limes)") {

    val order = ArrayBuffer("Lime", "Lime", "Lime")
    var afterOffer = offers.current(order)
    assert(afterOffer == ArrayBuffer("Lime", "Lime"))
  }

  test("Two Melons as buy one get one free and three Limes for price of two (final ArrayBuffer should have one Melon and one Lime)") {

    val order = ArrayBuffer("Lime", "Lime", "Melon", "Apple", "Lime", "Apple", "Banana", "Melon")
    var afterOffer = offers.current(order)
    assert(afterOffer == ArrayBuffer("Lime", "Apple", "Lime", "Apple", "Banana", "Melon"))
  }

  test("Testing empty ArrayBuffer ") {
    assert(offers.current(ArrayBuffer()) == ArrayBuffer())
  }

}
