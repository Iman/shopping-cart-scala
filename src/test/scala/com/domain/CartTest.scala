package com.domain

import com.domain.filter.Offers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.concurrent.TrieMap
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

@RunWith(classOf[JUnitRunner])
class CartTest extends FunSuite with BeforeAndAfter with Matchers {

  private val inventoryList = TrieMap("Apple" -> BigDecimal(0.35), "Banana" -> BigDecimal(0.20), "Melon" -> BigDecimal(0.50), "Lime" -> BigDecimal(0.15))

  private val orderList = new ArrayBuffer[String] with mutable.SynchronizedBuffer[String]
  orderList +=("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")

  val cart = new Cart(inventoryList)

  test("Test the price per ordered item") {

    val list = cart.getOrderPricing(orderList).asInstanceOf[ArrayBuffer[BigDecimal]].toList
    assert(list == List(0.5, 0.35, 0.15, 0.35, 0.2, 0.5, 0.15, 0.15))
  }

  test("Test total cost") {

    assert(cart.getTotal(orderList) == 2.35)
  }

  test("Test total cost after discounts applied") {

    val offers = new Offers(Map(("Melon" -> 2), ("Lime" -> 3)))
    assert(cart.getTotal(offers.current(orderList)) == 1.70)
  }
}
