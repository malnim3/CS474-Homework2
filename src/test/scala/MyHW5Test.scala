import MySetTheoryLang.ArithExp.*
import MySetTheoryLang.{Optimize, PartialEval, ExceptionClassDef, IF, ThrowException, evaluateFunction}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MyHW5Test extends AnyFlatSpec with Matchers{
  behavior of "my first language for set theory operations"

  it should "testing PartialEval of intersect expression" in {
    PartialEval().simplify("intersect", Set(1,"2","x"), Set("x",1,5)) shouldBe ("intersect",Set(1, "x"),Set())
  }

  it should "testing monadic function map" in {
    Set(2).map(e => Optimize().difference(Set(e), Set(2))) shouldBe Set(Set())
  }

  it should "testing PartialEval for multiplication of variable and itself" in {
    PartialEval().simplify(List("x","*","x")) shouldBe "x^2"
  }

  it should "testing Optimize for union " in {
    Optimize().union(Set(1,4),Set(1,4)) shouldBe Set(1,4)
  }

  it should "testing PartialEval of intersect expression, this time the value is y that we are looking for" in {
    PartialEval().simplify("intersect", Set(1,"2","y"), Set("y",1,5)) shouldBe ("intersect",Set(1, "y"),Set())
  }

}
