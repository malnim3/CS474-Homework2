import MySetTheoryLang.ArithExp.{Basic, Union}
import MySetTheoryLang.{CheckSet, interface}

import scala.language.dynamics
import reflect.Selectable.reflectiveSelectable
import language.experimental.macros


object MySetTheoryLang:
    type BasicType = Set[Any]
    var bindingScopingAssign: Map[String, Set[Any]] = Map()
    var bindingScopingMacro: Map[String, Set[Any]] = Map()
    var bindingScopingScope: Map[String, Set[Any]] = Map()

    //var inherit: Map[String, Set[Any]] = Map()
    //trait Selectable extends Any

    //Homework 4--------------------------------------------------------------------------------------------------------

    var exceptionClass: Map[String, String] = Map()
    var exceptionCatchBlock: Map[String, String] = Map()
    var throwException: Map[String, String] = Map()

    //IF definition
    def IF(condition: => Boolean, thenClause: => Any, elseClause: => Any): Any =
      if condition then thenClause else elseClause
    end IF

    //ExceptionClassDef definition
    def ExceptionClassDef(exceptionName: String, reason: String, catchBlock: String = null): String = {
      if(!exceptionClass.contains(exceptionName)){
        exceptionClass = exceptionClass + (exceptionName -> reason)
        if(!exceptionCatchBlock.contains(catchBlock) && catchBlock != null){
          exceptionCatchBlock = exceptionCatchBlock + (catchBlock -> exceptionName)
          return "Exception class created"
        }else{
          return "Sorry exception binded with name already, try again"
        }
      }
      "Sorry exception class with that name already exists, try again"
    }

    //ThrowException definition
    def ThrowException(exceptionName: String, reason: String, valReason: String): String = {
      //Checking to see if there is a binging between the two strings
      if(exceptionClass(exceptionName) != reason){
        return "Wrong reason string for this exception, try again"
      }
      //Checking for duplicates
      if(!throwException.contains(reason)){
        throwException = throwException + (reason -> valReason)
      }else{
        return "Duplicate name, try again"
      }
      //println("Exception Thrown")
      "Exception Thrown"
    }

    //Help evaluate functions
    def evaluateFunction(func: String, opt1: Set[Any], opt2: Set[Any]): Set[Any] ={
      import  ArithExp.*
      //val opt1 = func(1).toSet.asInstanceOf[Set[Any]]
      //val opt2 = func(2).toSet.asInstanceOf[Set[Any]]
      func.toLowerCase() match{
        case "insert" => Insert(Basic(opt1), Basic(opt2)).eval
        case "delete" => Delete(Basic(opt1), Basic(opt2)).eval
        case "intersect" => InterSect(Basic(opt1), Basic(opt2)).eval
        case "union" => Union(Basic(opt1), Basic(opt2)).eval
        case "difference" => Difference(Basic(opt1), Basic(opt2)).eval
        case "symdifference" => SymDifference(Basic(opt1), Basic(opt2)).eval
      }
    }

    //CatchException definition supposed to act as the try?
    def CatchException(exceptionName: String, iffunction: Any, someFunc: String = null, opt1: Set[Any] = null, opt2: Set[Any] = null, catchfunction: Any = null): Any = {
      //Checking if exception was thrown
      if(iffunction == "Exception Thrown"){
        //Checking if exception was caught
        if(catchfunction == "Exception Caught"){
          return "CatchException complete"
        }else{
          return "Error...could not catch exception"
        }
      }else{
        //Compute functions
        //println(iffunction.asInstanceOf[Set[Any]])
        //println(evaluateFunction(iffunction.asInstanceOf[List[String]]))
        //println("opt1: " + opt1 + "opt2: " + opt2 + "some function: " + someFunc)
        //if (someFunc != null)
        evaluateFunction(someFunc,opt1,opt2)

        //println(smtg)
      }
      "Exception was not found"
    }

    //Catch definition supposed to act as the catch
    def Catch(storage: String, variable: Set[Any], reason: String, reasonVal: Set[Any], exceptionName: String): String = {
      import  ArithExp.*
      if(exceptionClass(exceptionName) != reason){
        return "Wrong field, please try again"
      }
      if(exceptionCatchBlock(storage) != exceptionName){
        return "Error...no catch block for given exception"
      }
      Insert(Basic(variable), Basic(reasonVal)).eval
      //println("Exception Caught")
      return "Exception Caught"
    }

    //Homework 3--------------------------------------------------------------------------------------------------------
    //Abstract class Storage
    var abstractClass: Map[String, String] = Map()
    //Regular class Storage
    var regularClass: Map[String, String] = Map()
    //Classes that has key word IMPLEMENTS or EXTENDS Storage
    var extendImplementClasses: Map[String, String] = Map()
    //Constructor Storage
    var constructor: Map[String, String] = Map()
    //Interface Storage
    var interface: Map[String, String] = Map()
    //Field Storage
    var field: Map[String, List[String]] = Map()
    //Method Storage
    var method: Map[String, String] = Map()
    //Method for each class/interface
    var classMethod: Map[String, List[String]] = Map()


    class DynamicClass extends Dynamic {
      var map = Map.empty[String, Any]

      def selectDynamic(name: String) = {
        map get name getOrElse sys.error("method not found")
      }

      def updateDynamic(name: String)(value: Any) = {
        map += name -> value
      }
      def applyDynamic(name: String)(args: Any*):String = {
        import  ArithExp.*
        name.toLowerCase match {
          case "empty" =>"Set()"
          case "nonempty" => "Set(1,2,3,4,5,6,7,8,9)"
          case "constructor" =>  ("Constructor: "+ args.mkString("", " = ", ""))
          case "method" =>"Default constructor called: default = Set(1,2,3,4,5,6,7,8,9)"
          case "methname" => ("method name: " + args.mkString("", " method body:  ", ""))
          case "union" => val tmp = Union(Basic(args(0).asInstanceOf[Set[Any]]), Basic(args(1).asInstanceOf[Set[Any]])).eval; tmp.mkString(",")
          case "difference" => val tmp = Difference(Basic(args(0).asInstanceOf[Set[Any]]), Basic(args(1).asInstanceOf[Set[Any]])).eval;  tmp.mkString(",")
        }
      }
    }

    //Abstract class with body in method
    def AbstractClassDef(s: String, fieldMap: List[String], constructorList: List[String] = null, methodList: List[String] = List("defaultMethod")) : String =
      s match {
        case s =>
          if(abstractClass.contains(s)){
            return "Sorry already have Abstract Class with that name :("
          }else{
            //AbstractClass = abstractClass + (s -> fieldMap)
            val d = new DynamicClass
            val constKey = constructorList(0)
            val constValue = constructorList(1)
            //Saving field of abstract class
            field = field + (s -> (fieldMap))
            //println("abstract class: " + field(s))
            //Creating constructor NOTE: classes can not have same field name otherwise it will we written over here...
            if(constructorList == null){
              //Will provide default constructor
              d.constructor("default", "4")
              constructor = constructor + ("default" -> "4")
            }else{
              //Will add constructor user has set
              d.constructor(constKey, constValue)
              constructor = constructor + (constKey -> constValue)
            }
            //Adding method
            if(methodList(0) == "defaultMethod"){
              //We are leaving this method without body
              method = method + ("defaultMethod" -> null)
              classMethod = classMethod + (s -> List("defaultMethod"))
              d.method()
            }else{
              //We will add a body to this method
              val methName = methodList(0)
              val methBod = methodList(1)
              method = method + (methodList(0) -> methodList(1))
              classMethod = classMethod + (s -> methodList)
              d.methName(methName, methBod)
            }
            abstractClass = abstractClass + (s -> s)
            return "Saved Abstract Class!"
          }
      }

    //Letting classes extend/implements ONLY ONE class/interface
    def ExtendClassDef(s: String, fieldMap: List[String], constructorList: List[String] = null, methodList: List[String] = List("defaultMethod"), extendClass: List[String] = null) : String =
      s match {
        case s =>
          if(regularClass.contains(s)){
            return "Sorry already have Class with that name :("
          }else{
            val d = new DynamicClass
            val constKey = constructorList(0)
            val constValue = constructorList(1)
            //Saving field of class
            field = field + (s -> fieldMap)
            //println("class field: " + field(s))
            //Creating constructor NOTE: classes can not have same field name otherwise it will we written over here...
            if(constructorList == null){
              //Will provide default constructor
              d.constructor("default", "4")
              constructor = constructor + ("default" -> "4")
            }else{
              //Will add constructor user has set
              d.constructor(constKey, constValue)
              constructor = constructor + (constKey -> constValue)
            }
            //Adding method
            val methName = methodList(0)
            val methBod = methodList(1)
            if(methodList(0) == "defaultMethod"){
              //We are leaving this method without body
              method = method + ("defaultMethod" -> null)
             // classMethod = classMethod + (s -> List("defaultMethod"))
              d.method()
            }else if(methName.toLowerCase == "sum" || methName.toLowerCase == "add"){
              //Will add a body to this method that is already built in
            }else{
              //We will add a body to this method
              method = method + (methName -> methBod)
             // classMethod = classMethod + (s -> methodList)
              d.methName(methName, methBod)
            }
            //Checking to see if key word IMPLEMENTS or EXTENDS is valid
            if (extendClass == null){
              //Do nothing bc user does not want class to extend anything
              //println("Does not have extended class")
            }else if(extendClass(0).toLowerCase == "extends"){
              //println("In extends if statement")
              //Check to see if class trying to EXTEND is a valid class
              if(abstractClass.contains(extendClass(1)) || regularClass.contains(extendClass(1))){
                extendImplementClasses = extendImplementClasses + (s -> extendClass(1))
              }else{
                return "Sorry class is not valid :("
              }
              //println("In extends if statement end")
            }else{
              //Check to see if interface trying to IMPLEMENT is a valid interface
              if(interface.contains(extendClass(1))){
                extendImplementClasses = extendImplementClasses + (s -> extendClass(1))
              }else{
                return "Sorry interface is not valid :("
              }
            }
            classMethod = classMethod + (s -> (methodList ::: classMethod(extendClass(1))))
            regularClass = regularClass + (s -> s)
            return "Saved Extended Class!"
          }
      }

    //Creating interface
    def InterfaceDef(name: String, fieldList: List[String] = null, constructorList: List[String] = null, methodName: List[String]): String =
      if(interface.contains(name)){
        return "Sorry already have Interface with that name :("
      }else{
        val d = new DynamicClass
        val constKey = constructorList(0)
        val constValue = constructorList(1)
        //Saving field of class
        field = field + ((name -> fieldList))
        println("class field: " + field(name))
        //Creating constructor NOTE: classes can not have same field name otherwise it will we written over here...
        if(constructorList == null){
          //Will provide default constructor
          d.constructor("default", "4")
          constructor = constructor + ("default" -> "4")
        }else{
          //Will add constructor user has set
          d.constructor(constKey, constValue)
          constructor = constructor + (constKey -> constValue)
        }
        //Adding method
        val methName = methodName(0)
        val methBod = methodName(1)
        if(methodName(0) == "defaultMethod"){
          //We are leaving this method without body
          method = method + ("defaultMethod" -> null)
          classMethod = classMethod + (name -> List("defaultMethod"))
          d.method()
        }else if(methName.toLowerCase == "sum" || methName.toLowerCase == "add"){
          //Will add a body to this method that is already built in
        }else{
          //We will add a body to this method
          method = method + (methodName(0) -> methodName(1))
          abstractClass = abstractClass + (methodName(0) -> name)
          classMethod = classMethod + (name -> methodName)
          d.methName(methName, methBod)
        }
        interface = interface + (name -> name)
      }
      return "Saved Interface!"

    //Calling regular class/extended class/implemented class/abstract class/interface
    def CallingClass(name: String, callingMethod: String, methName: List[Any] = List("","")) : String =
      if(abstractClass.contains(name)){
        //send back error if user is trying to call abstract class
        return "Sorry not allowed to call abstract class"
      }else if(interface.contains(name)){
        //send back error if user is trying to call interface
        return "Sorry not allowed to call interface"
      }else if(extendImplementClasses.contains(name)){
        //Make sure the class has access to the functions extended/implemented
        if(classMethod(name).contains(callingMethod)){
          val d = new DynamicClass
          val arg1 = methName(0)
          val arg2 = methName(1)
          callingMethod.toLowerCase match{
            case "empty" => return d.empty()
            case "nonempty" => return d.nonempty()
            case "union" => return d.union(arg1, arg2)
            case "difference" => return d.difference(arg1, arg2)
            case _ => return d.methName(arg1, arg2)
          }
        }else{
          return "class does not have access to method"
        }
      }else{
        //Can only make calls to whatever was defined in class
        if(classMethod(name).contains(callingMethod)){
          val d = new DynamicClass
          val arg1 = methName(0)
          val arg2 = methName(1)
          callingMethod.toLowerCase match{
            case "empty" => return d.empty()
            case "nonempty" => return d.nonempty()
            case "union" => return d.union(arg1, arg2)
            case "difference" => return d.difference(arg1, arg2)
            case _ => return d.methName(arg1, arg2)
          }
        }else{
          return "class does not have access to method"
        }
      }
      "Done calling class"

    //Homework 2--------------------------------------------------------------------------------------------------------
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
              return "Sorry already have Scope with that name :("
            }else{
              bindingScopingScope = bindingScopingScope + (opt1.eval2 -> opt2.eval)
              return "Saved Scope!"
            }

          //Macro: Set operation that can be referenced by someName
          case Macro(opt1, opt2) =>
            if(bindingScopingMacro.contains(opt1.eval2)){
              return "Sorry already have Macro with that name :("
            }else{
              bindingScopingMacro = bindingScopingMacro + (opt1.eval2 -> opt2.eval)
              return "Saved Macro!"
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

      val A = AbstractClassDef("Someclass", List("f"), List("f","2"))
      //println("a: " + A)

      val B = AbstractClassDef("SomeOtherClass", List("g"), List("g", "4"), List("m2", "tmp = 4"))
      //println("b: " + B)

      val C = AbstractClassDef("Someclass", List("f"), List("f","2"))
      //println("c: " + C)

      val tmp = (ExtendClassDef("classExtend", List("h"), List("h", "21"), List("difference", "something = 3"), List("extends", "SomeOtherClass")))
//      println(InterfaceDef("newinter", List("f"), List("f", "23"), List("abstractclass", "whatever = 5")))
//      println(InterfaceDef("newinter", List("f"), List("f", "23"), List("abstractclass", "whatever = 5")))
//      println(CallingClass("newinter", ""))
//      println(CallingClass("Someclass", ""))
//      println(CallingClass("classExtend", "random"))
//      println(CallingClass("classExtend", "difference", List(Set(1,2,3),Set(2,3))))
     //println(CallingClass("classExtend", "m2"))
      //SetMan();
      //SetMan(list.asInstanceOf[Set[Any]], list2.asInstanceOf[Set[Any]]);
      //println("Testing Nested Class: "+ MainClass(Set(1).asInstanceOf[Set[Any]]).checkIfEmpty())
      //println("Testing table----------------")
  //    val list3: Set[Int] = Set(4)
      val someList = List("var", "reason")
      ExceptionClassDef("someName","reason", "catch")
      println(CatchException("someString", IF((3>4), evaluateFunction("difference", Set(1,2,90000), Set(1)),
        ThrowException("someName","reason", "Check Fail")), "difference", Set(1,2,900).asInstanceOf[Set[Any]], Set(1).asInstanceOf[Set[Any]],
        Catch("catch", Set("var"), "reason",Set("Check Fail").asInstanceOf[Set[Any]], "someName")))
      //val secondExpressiom = Insert(ValueSetInt(list), ValueSetInt(list2)).eval
  //    val thirdExpression = Delete(ValueSetInt(secondExpressiom.asInstanceOf[Set[Int]]), ValueSetInt(list3)).eval
  //    val fourthExpression = Difference(ValueSetInt(secondExpressiom.asInstanceOf[Set[Int]]),ValueSetInt(list3)).eval
  //    val fifthExpression = Difference(ValueSetInt(list3),ValueSetInt(fourthExpression.asInstanceOf[Set[Int]])).eval
