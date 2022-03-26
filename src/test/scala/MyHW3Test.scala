import MySetTheoryLang.ArithExp.*
import MySetTheoryLang.{AbstractClassDef, ExtendClassDef, InterfaceDef, CallingClass}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MyHW3Test extends AnyFlatSpec with Matchers{
  behavior of "my first language for set theory operations"

  it should "testing AbstractClassDef with abstract class name of Someclass" in {
    val testAbstract = AbstractClassDef("SomeAbstractClass", List("f"), List("f","2"),  List("union", "tmp = 4")) shouldBe "Saved Abstract Class!"
  }

  it should "testing AbstractClassDef with abstract class name of SomeAbstractClass even though there is already an existing class with that name" in {
    val testAbstractSameName = AbstractClassDef("SomeAbstractClass", List("f"), List("f","2")) shouldBe "Sorry already have Abstract Class with that name :("
  }

  it should "testing ExtendClassDef with class name SomeClass that extends SomeAbstractClass" in {
    val testIntersect = ExtendClassDef("SomeClass", List("h"), List("h", "21"), List("difference", "something = 3"), List("extends", "SomeAbstractClass")) shouldBe "Saved Extended Class!"
  }

  it should "testing InterfaceDef with string newinter adding abstract class named abstractclass in body " in {
    val testIntersect = InterfaceDef("newinter", List("f"), List("f", "23"), List("abstractclass", "someBind = 5")) shouldBe "Saved Interface!"
  }

  it should "testing CallingClass with class called SomeClass that is trying to call method from SomeAbstractClass" in {
    val testCallingClass = CallingClass("SomeClass", "union", List(Set(1,2,3),Set(444))) shouldBe "1,2,3,444"
    val testCallingClass2 = CallingClass("SomeClass", "difference", List(Set(1,2,3),Set(2))) shouldBe "1,3"
  }
}
