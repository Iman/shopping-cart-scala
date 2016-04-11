package com.domain

import java.util.concurrent.ConcurrentHashMap

import com.domain.filter.Offers

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Main extends App {

  var order = List("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")
  val offers = new Offers(Map(("Melon" -> 2), ("Lime" -> 3)))

  private val inventoryList = new ConcurrentHashMap[String, BigDecimal]().asScala
  inventoryList +=(("Apple" -> BigDecimal(0.35)), ("Banana" -> BigDecimal(0.20)), ("Melon" -> BigDecimal(0.50)), ("Lime" -> BigDecimal(0.15)))

  private val orderList = new ArrayBuffer[String] with mutable.SynchronizedBuffer[String]
  orderList +=("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")

  val cart = new Cart(inventoryList)

  val priceList = cart.getOrderPricing(orderList)
  var total = cart.getTotal(orderList)

  println("")
  println("Before promotion:")
  sys.props("line.separator")
  println("Total: " + total)
  sys.props("line.separator")
  println("")
  total = cart.getTotal(offers.current(orderList))

  println("After promotion:")
  sys.props("line.separator")
  println("Total after offer deductions: " + total)

}
