import MySetTheoryLang.ArithExp.*
import MySetTheoryLang.{ClassDef, ClassCall, ClassCallBool}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class MyClassTest extends AnyFlatSpec with Matchers {
  behavior of "my first language for set theory operations"

  it should "testing ClassCall with string intersect0" in {
    val testIntersect = ClassCall("intersect0") shouldBe Set()
  }

  it should "testing ClassCall with string intersect1" in {
    val testIntersect = ClassCall("intersect1", Set(1,2,3).asInstanceOf[Set[Any]]) shouldBe Set(3)
  }

  it should "testing ClassCallBool with string intersect0" in {
    val testIntersect = ClassCallBool("checkset0") shouldBe false
  }

  it should "testing ClassCallBool with string intersect1" in {
    val testIntersect = ClassCallBool("checkset2", Set(1,2,3).asInstanceOf[Set[Any]], Set(2).asInstanceOf[Set[Any]]) shouldBe true
  }

  it should "testing ClassCallBool with string checkseteven1" in {
    val testIntersect = ClassCallBool("checkseteven1", Set(1,2,3).asInstanceOf[Set[Any]]) shouldBe false
  }

}
