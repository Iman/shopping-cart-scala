package com.domain.model

import scala.collection.concurrent

sealed trait Inventory

case class Items(items: concurrent.Map[String, BigDecimal]) extends Inventory {

  def apply(item: (String, BigDecimal)) = items += item

  def unapply(name: String): Option[BigDecimal] = {
    if (items.contains(name)) {
      items.remove(name)
    } else {
      None
    }
  }

  def find[BigDecimal](name: String) = items.get(name).orNull
}