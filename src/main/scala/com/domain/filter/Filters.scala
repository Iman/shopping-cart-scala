package com.domain.filter

import java.util.concurrent.ConcurrentHashMap

import scala.collection.mutable

import scala.collection.JavaConverters._

sealed trait Filters

case class Offers(productToOffer: Map[String, Int]) extends Filters {

  private val productGroup = new ConcurrentHashMap[String, Int]().asScala

  private val dropList = new mutable.HashSet[String]() with mutable.SynchronizedSet[String]

  def current(order: List[String]) = {

    order.groupBy(item => item).map(x => productGroup.put(x._1, x._2.length))

    productGroup.foreach { x =>

      if (x._2 > 1) {

        if (null != productToOffer.get(x._1).orNull) {

          val offerKey = productToOffer.get(x._1).getOrElse(0)

          offerKey match {

            //Buy one get one free
            case (2) => {

              if (productGroup.get(x._1).getOrElse(0) % 2 == 0 || productGroup.get(x._1).getOrElse(0) - 1 > 3) {
                val j = productGroup.get(x._1).getOrElse(0) / 2
                for (i <- 1.to(j)) {
                  dropList += x._1
                }
              }

            }

            //Three for the price two
            case (3) => {

              if (productGroup.get(x._1).getOrElse(0) % 2 != 0 || productGroup.get(x._1).getOrElse(0) > 4) {
                val j = productGroup.get(x._1).getOrElse(0) / 3
                for (i <- 1.to(j)) {
                  dropList += x._1
                }
              }
            }
            case _ => Nil
          }
        }
      }
    }

    order diff dropList.toList
  }
}

