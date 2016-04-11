import java.util.concurrent.ConcurrentHashMap

import com.domain.Cart
import com.domain.filter.Offers
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class CartFeaturesTest extends FeatureSpec with GivenWhenThen with Matchers {

  private val inventoryList = new ConcurrentHashMap[String, BigDecimal]().asScala
  inventoryList +=(("Apple" -> BigDecimal(0.35)), ("Banana" -> BigDecimal(0.20)), ("Melon" -> BigDecimal(0.50)), ("Lime" -> BigDecimal(0.15)))

  private val orderList = new ArrayBuffer[String] with mutable.SynchronizedBuffer[String]
  orderList +=("Melon", "Apple", "Lime", "Apple", "Banana", "Melon", "Lime", "Lime")

  val offers = new Offers(Map(("Melon" -> 2), ("Lime" -> 3)))

  val cart = new Cart(inventoryList)

  feature("ShoppingCart object") {

    scenario("Create the cart object with loaded items") {

      Given("ShoppingCart object is created and price list has retrieved")

      Then("the itemized list is created")

      val priceList = cart.getOrderPricing(ArrayBuffer("Apple", "Banana", "Lime", "Apple"))
      priceList should be(ArrayBuffer(0.35, 0.2, 0.15, 0.35))

      Then("the total amount")

      cart.getTotal(ArrayBuffer("Apple", "Banana", "Apple")) should be(0.90)

      Then("the extra item should be set")

      val newList = ArrayBuffer("Apple", "Apple", "Lime", "Apple", "Melon", "Melon", "Melon")
      cart.getOrderPricing(newList) should be(ArrayBuffer(0.35, 0.35, 0.15, 0.35, 0.5, 0.5, 0.5))

      Then("the last order total amount")

      cart.getTotal(newList) should be(2.70)

      Then("buy one get one free & three for the price two")

      cart.getTotal(offers.current(ArrayBuffer("Melon", "Melon", "Lime", "Lime", "Lime"))) should be(0.80)

    }
  }
}