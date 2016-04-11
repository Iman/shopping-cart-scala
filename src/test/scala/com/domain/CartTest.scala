package com.domain

import java.util.concurrent.ConcurrentHashMap

import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class CartTest extends FunSuite with BeforeAndAfter with Matchers {

  private val inventoryList = new ConcurrentHashMap[String, BigDecimal]().asScala
  inventoryList +=(("Apple" -> BigDecimal(0.35)), ("Banana" -> BigDecimal(0.20)), ("Melon" -> BigDecimal(0.50)), ("Lime" -> BigDecimal(0.15)))

  private val orderList = new ArrayBuffer[String] with mutable.SynchronizedBuffer[String]
  orderList +=("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")

  val cart = new Cart(inventoryList)

  test("Test get item prices") {

    val list = cart.getOrderPricing(orderList).asInstanceOf[ArrayBuffer[BigDecimal]].toList
    assert(list == List(0.5, 0.35, 0.15, 0.35, 0.2, 0.5, 0.15, 0.15))
  }

  test("Test total price") {

    assert(cart.getTotal(orderList) == 2.35)
  }
}
