import MySetTheoryLang.ArithExp.*
import MySetTheoryLang.{Catch, CatchException, ExceptionClassDef, IF, ThrowException, evaluateFunction, exceptionClass}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MyHW4Test extends AnyFlatSpec with Matchers{
  behavior of "my first language for set theory operations"

  it should "testing IF when the IF does not throw an exception " in {
    IF((3<4), evaluateFunction("difference", Set(1,2,90000), Set(1)),ThrowException("someName","reason", "Check Fail")) shouldBe Set(2, 90000)
  }

  it should "testing IF when If throws an exception" in {
    ExceptionClassDef("someName","reason", "catch")
    IF((3>4), evaluateFunction("difference", Set(1,2,90000), Set(1)),ThrowException("someName","reason", "Check Fail")) shouldBe "Exception Thrown"
  }

  it should "testing Catch when catch is not defined" in {
    ExceptionClassDef("someOtherName","reason")
    Catch("catch", Set("var"), "reason",Set("Check Fail").asInstanceOf[Set[Any]], "someOtherName") shouldBe "Error...no catch block for given exception"
  }

  it should "testing Catch when catch is defined" in {
    ExceptionClassDef("someName","reason", "catch")
    Catch("catch", Set("var"), "reason",Set("Check Fail").asInstanceOf[Set[Any]], "someName") shouldBe "Exception Caught"
  }

  it should "testing evaluateFunction" in {
    evaluateFunction("union", Set(1,2,90000), Set(1)) shouldBe Set(1, 2, 90000)
  }
}