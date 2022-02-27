import MySetTheoryLang.CheckSet

object MySetTheoryLang:
    type BasicType = Set[Any]
    var bindingScopingAssign: Map[String, Set[Any]] = Map()
    var bindingScopingMacro: Map[String, Set[Any]] = Map()
    var bindingScopingScope: Map[String, Set[Any]] = Map()


    //Virtual Dispatch abstract class-----------------------------------------------------------------------------------
    abstract class VDClass

    //Finds intersection of 2 sets
    class FindIntersect(set1: Set[Any] = Set(1,2), set2: Set[Any] = Set(3,4)) extends VDClass:
      import  ArithExp.*
      def findingIntersect: Set[Any] = InterSect(Basic(set1), Basic(set2)).eval

    //Finds difference of two sets
    class FindDifference(set1: Set[Any] = Set(1,2), set2: Set[Any] = Set(3,4)) extends VDClass:
      import  ArithExp.*
      def findingDifference: Set[Any] = Difference(Basic(set1), Basic(set2)).eval

    //Extended class----------------------------------------------------------------------------------------------------
    class CheckSet(set1: Set[Any] = Set(1,2,3), set2: Set[Any] = Set("Four")):
      import ArithExp.*
      //Checks to see if set2 is subset of set1
      def checkingSet: Boolean = Check(Basic(set1), Basic(set2)).eval3
      //Adds set1 and set2 together
      def addToSet: Set[Any] = Insert(Basic(set1), Basic(set2)).eval

    class isInSet(set1: Set[Any] = Set(1,2,3,4), set2: Set[Any] = Set()) extends CheckSet{
      import ArithExp.*
      //Checks to see if set1 is a subset of Set(2,4,6,8) overriding the function checkingSet in the class CheckSet
      override def checkingSet: Boolean = {
        //println(Check(Basic(Set(2,4,6,8)), Basic(set1)).eval3)
        return Check(Basic(Set(2,4,6,8)), Basic(set1)).eval3
      }
      //Will call function from extended class and will add the string name to set
      def addNameToSet: Set[Any] = {
        val A = new CheckSet(set1, Set("Name").asInstanceOf[Set[Any]])
        return A.addToSet
      }
    }

    //Nested Class------------------------------------------------------------------------------------------------------
    //Creating instance of inner class within outer class
    //If set passed in is empty, will add 2 elements to it and return to the user
    class MainClass(set: Set[Any] = Set()) extends VDClass{ //Outer class
      import  ArithExp.*
      val checkSet: Set[Any] = set
      //Will add the 2 elements to set
      class ChildClass{ //Inner class
        val twoElem: Set[Any] = Set("One", 2)
        def addingTwo(set:Set[Any] = Set()): Set[Any]= Insert(Basic(set), Basic(twoElem)).eval
      }
      val A = new ChildClass()
      def checkIfEmpty : Set[Any] = {
        if(checkSet.isEmpty){
          return A.addingTwo(checkSet)
        }else{
          return checkSet
        }
      }
    }

    //Case for Virtual Dispatch-----------------------------------------------------------------------------------------
    //Determine dynamic type
    def ClassDef(s: String, opt1: Set[Any] = Set(), opt2: Set[Any] = Set()) : Option[VDClass] =
      s.toLowerCase match {
        case "intersect0" => Some(new FindIntersect()) //Will use default values
        case "intersect1" => Some(new FindIntersect(opt1)) //Will use 1 default value and 1 set value
        case "intersect2" => Some(new FindIntersect(opt1, opt2)) //Will use 2 set values
        case "difference0" => Some(new FindDifference) //Will use default values
        case "difference1" => Some(new FindDifference(opt1)) //Will use 1 default value and 1 set value
        case "difference2" => Some(new FindDifference(opt1, opt2)) //Will use 2 set values
        case "emptyset0" => Some(new MainClass)//Will use default values
        case "emptyset1" => Some(new MainClass(opt1))//Will use value passed in
        case _ => None
      }
    //Make function call to appropriate class and method
    def ClassCall(s: String, opt1: Set[Any] = Set(), opt2: Set[Any] = Set()) : Set[Any] =
      s.toLowerCase match {
        case "intersect0" => FindIntersect().findingIntersect //Will use default values
        case "intersect1" => FindIntersect(opt1).findingIntersect //Will use 1 default value and 1 set value
        case "intersect2" => FindIntersect(opt1, opt2).findingIntersect //Will use 2 set values
        case "difference0" => FindDifference().findingDifference //Will use default values
        case "difference1" => FindDifference(opt1).findingDifference //Will use 1 default value and 1 set value
        case "difference2" => FindDifference(opt1, opt2).findingDifference //Will use 2 set values
        case "emptyset0" => MainClass().checkIfEmpty //Will use default values
        case "emptyset1" => MainClass(opt1).checkIfEmpty //Will use value passed in
        case "combineset0" => CheckSet().addToSet //Will use default values
        case "combineset1" => CheckSet(opt1).addToSet //Will use 1 default value and 1 set value
        case "combineset2" => CheckSet(opt1, opt2).addToSet //Will use 2 set values
        case "addname0" => isInSet().addNameToSet //Will use default values
        case "addname1" => isInSet(opt1).addNameToSet //Will use value passed in
      }
    def ClassCallBool(s: String, opt1: Set[Any] = Set(), opt2: Set[Any] = Set()) : Boolean =
      s.toLowerCase match {
        case "checkset0" => CheckSet().checkingSet //Will use default values
        case "checkset1" => CheckSet(opt1).checkingSet //Will use 1 default value and 1 set value
        case "checkset2" => CheckSet(opt1, opt2).checkingSet //Will use 2 set values
        case "checkseteven0" => isInSet().checkingSet //Will use default values
        case "checkseteven1" => isInSet(opt1).checkingSet //Will use 1 default value and 1 set value
      }

    //------------------------------------------------------------------------------------------------------------------
    enum ArithExp:
      //Value1-6 are possible set types the user can create
      case Basic(inputAny: BasicType)
      case ValueSetInt(inputInt: Set[Int])
      case ValueSetString(inputString: Set[String])
      case ValueSetDoub(inputDouble: Set[Double])
      case ValueSetFloat(inputFloat: Set[Float])
      case ValueSetChar(inputChar: Set[Char])
      case ValueScopePremade(name: String)
      case ValueScopeAssign(name: String)
      case ValueScopeMacro(name: String)
      case ValueScopeNamed(name: String)
      //Functions call for set manipulation
      case Insert(opt1: ArithExp, opt2: ArithExp)
      case Delete(opt1: ArithExp, Opt2: ArithExp)
      case InterSect(opt1: ArithExp, opt2: ArithExp)
      case Union(opt1: ArithExp, opt2: ArithExp)
      case Difference(opt1: ArithExp, opt2: ArithExp)
      case SymDifference(opt1: ArithExp, opt2: ArithExp)
      case Cartesian(opt1: ArithExp, opt2: ArithExp)
      case Assign(opt1: ArithExp, opt2: ArithExp)
      case Check(opt1: ArithExp, opt2: ArithExp)
      case Macro(opt1: ValueScopeMacro, opt2: ArithExp)
      case Scope(opt1: ValueScopeNamed, opt2: ArithExp)
      case ScopeAnon(opt1: ArithExp)
      //Bind for set objects to variables
      private val bindingScopingPremade: Map[String, Set[Any]] = Map("freshman" -> Set(1), "sophomore" -> Set(1.2), "junior" -> Set(1,2,3), "senior" -> Set(1,2,3,4))

      def eval: BasicType =
        this match {
          //To prevent matching error, we change the type the user has input to type Any
          case Basic(i) => i
          case ValueSetInt(i) => i.asInstanceOf[BasicType]
          case ValueSetString(i) => i.asInstanceOf[BasicType]
          case ValueSetDoub(i) => i.asInstanceOf[BasicType]
          case ValueSetFloat(i) => i.asInstanceOf[BasicType]
          case ValueSetChar(i) => i.asInstanceOf[BasicType]
          //If passed in, will map string as shown on line 22
          case ValueScopePremade(name) => bindingScopingPremade(name)
          case ValueScopeAssign(name) => bindingScopingAssign(name)
          case ValueScopeMacro(name) => bindingScopingMacro(name)
          case ValueScopeNamed(name) => bindingScopingScope(name)
          //Here the function calls set up above will actually be defined
          //Adds or combines sets together
          case Insert(opt1, opt2) => opt1.eval ++ opt2.eval
          //Deletes element(s) from set
          case Delete(opt1, opt2) => opt1.eval -- opt2.eval
          //Finds the intersection of the 2 sets passed in
          case InterSect(opt1, opt2) => opt1.eval intersect opt2.eval
          //Finds the union of the 2 sets passed in
          case Union(opt1, opt2) => opt1.eval union opt2.eval
          //Finds the difference between the 2 sets passed in
          case Difference(opt1, opt2) => opt1.eval diff opt2.eval
          //Finds the Symmetric difference between the 2 sets passed in
          case SymDifference(opt1, opt2) => (opt1.eval union opt2.eval) diff (opt1.eval intersect opt2.eval)
          //Finds the cartesian product of the 2 sets passed in
          case Cartesian(opt1, opt2) =>
            var newSet: Set[Any] = Set()
            for (i <- 0 to opt1.eval.size){
              for (j <- 0 to opt2.eval.size){
                newSet += ((i,j))
              }
            }
            return newSet
          case ScopeAnon(opt1) =>
            val x = opt1.eval + 1000
            return x
        }

      def eval2: String =
        this match{
          //Assign: Takes in string and Set[Any]. If its in the bindingScopingAssign if not there else adds to bindingScoping
          case ValueScopeAssign(i) => i.asInstanceOf[String]
          case ValueScopeMacro(i) => i.asInstanceOf[String]
          case ValueScopeNamed(i) => i.asInstanceOf[String]
          case Assign(opt1, opt2) =>
            if(bindingScopingAssign.contains(opt1.eval2)){
              return "Sorry, already have value for that name :("
            }else{
              //println(opt1.eval2 + " -> " + opt2.eval)
              //println("empty before?" + bindingScopingAssign.isEmpty.toString() )
              bindingScopingAssign = bindingScopingAssign + (opt1.eval2 -> opt2.eval)
              //println("empty after?" + bindingScopingAssign.isEmpty.toString() )
              return "Added to list!"
            }
          //Scope: Create named scope with some value and output the data in terminal
          case Scope(opt1, opt2) =>
            if(bindingScopingScope.contains(opt1.eval2)){
              return ("Sorry already have Scope with that name :(")
            }else{
              bindingScopingScope = bindingScopingScope + (opt1.eval2 -> opt2.eval)
              return ("Saved Scope!")
            }

          //Macro: Set operation that can be referenced by someName
          case Macro(opt1, opt2) =>
            if(bindingScopingMacro.contains(opt1.eval2)){
              return ("Sorry already have Macro with that name :(")
            }else{
              bindingScopingMacro = bindingScopingMacro + (opt1.eval2 -> opt2.eval)
              return ("Saved Macro!")
            }
        }

      def eval3: Boolean =
        this match{
          //Check: Takes in Set and some value. If value is in set return true else return false
          case Check(opt1, opt2) => opt2.eval subsetOf opt1.eval
        }

  //My Main function I used to figure out if my code and other operations were working
    @main def runArithExp: Unit =
      import ArithExp.*
      //val firstExpression = Sub(Add(Add(Value(2), Value(3)),Var("Adan")), Var("x")).eval
      val list: Set[Int] = Set(1,2)
      val list2: Set[Int] = Set(4, 5, 6)
      //println(CheckSet().checkingSet(list.asInstanceOf[Set[Any]], list2.asInstanceOf[Set[Any]]))
      //println(isInSet().checkingSet(list.asInstanceOf[Set[Any]], list2.asInstanceOf[Set[Any]]))
      //val car1 = ClassDef("intersect0")

      //val A = new FindIntersect()
      //println("car1: " + car1.get + " A: " + A)

      //println(A.findingIntersect)
      println(ClassDef("intersect0"))

      //SetMan();
      //SetMan(list.asInstanceOf[Set[Any]], list2.asInstanceOf[Set[Any]]);
      //println("Testing Nested Class: "+ MainClass(Set(1).asInstanceOf[Set[Any]]).checkIfEmpty())
      //println("Testing table----------------")
  //    val list3: Set[Int] = Set(4)
      //val secondExpressiom = Insert(ValueSetInt(list), ValueSetInt(list2)).eval
  //    val thirdExpression = Delete(ValueSetInt(secondExpressiom.asInstanceOf[Set[Int]]), ValueSetInt(list3)).eval
  //    val fourthExpression = Difference(ValueSetInt(secondExpressiom.asInstanceOf[Set[Int]]),ValueSetInt(list3)).eval
  //    val fifthExpression = Difference(ValueSetInt(list3),ValueSetInt(fourthExpression.asInstanceOf[Set[Int]])).eval
  //    val sixth = SymDifference(ValueSetInt(list),ValueSetInt(fourthExpression.asInstanceOf[Set[Int]])).eval

     // println(car1)

