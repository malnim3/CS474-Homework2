import MySetTheoryLang.ArithExp.{Insert, ValueSetInt, Check, ValueScopeAssign, Assign, Macro, ValueScopeMacro, Scope,ValueScopeNamed,
  Delete, ScopeAnon}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class YourSetTheoryLanguageTest extends AnyFlatSpec with Matchers {
  behavior of "my first language for set theory operations"

  it should "create a set and insert objects into it" in {
    val list: Set[Int] = Set(1,2)
    val list2: Set[Int] = Set(4, 5, 6)
    val testInsert = Insert(ValueSetInt(list), ValueSetInt(list2)).eval
    Check(ValueSetInt(testInsert.asInstanceOf[Set[Int]]), ValueSetInt(list)).eval3 shouldBe true
  }

  it should "create assign binding" in {
    val list: Set[Int] = Set(1,2)
    val list2: Set[Int] = Set(4, 5, 6)
    val testAssign = Assign(ValueScopeAssign("someName"), ValueSetInt(list2)).eval2 shouldBe "Added to list!"
  }

  it should "create macro binding" in {
    val list: Set[Int] = Set(5)
    val list2: Set[Int] = Set(4, 5, 6)
    val list3 = Delete(ValueSetInt(list2),ValueSetInt(list)).eval
    val testMacro = Macro(ValueScopeMacro("someName"), ValueSetInt(list3.asInstanceOf[Set[Int]])).eval2 shouldBe "Saved Macro!"
  }

  it should "create named scope binding" in {
    val list: Set[Int] = Set(1,2)
    val list2: Set[Int] = Set(4, 5, 6)
    val testScope = Scope(ValueScopeNamed("someName"), ValueSetInt(list2)).eval2 shouldBe "Saved Scope!"
  }
  it should "create anon scope binding" in {
    val list: Set[Int] = Set(1,2)
    val testScopeAnon = ScopeAnon(ValueSetInt(list)).eval shouldBe Set(1, 2, 1000)
  }
}