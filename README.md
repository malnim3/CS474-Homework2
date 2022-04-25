# CS474-Homework5
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! I have added some new features to my language. Below I will explain and give examples of how to use the new features.
### To find my language go to: src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: src\test\scala\MyHW5Test.scala <br/>

# How to install
To create your project, 
  1. open up intellij, go to file > new > project...
  2. select scala > sbt > next
  3. download Oracle OpenJDK version 11.0.14
  4. set sbt to 1.6.2
  5. set scala to 3.0
  6. click finish
  
Add the functions from the object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.{WHATEVER CLASSES/DEF YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.{Optimize, PartialEval}
```
### New Features: <br/>
PartialEval: This new feature will partially evaluate an expression. Meaning there can be values in an expression which is not defined yet.<br/>
Optimize: This new feature will optimize a call to an expression to reduce any unnecessary calculations. <br/>
map: Works very similar to the Monadic Map feature already in scala. This feature takes in a type of SetExpression and outputs a type of SetExpression.<br/>

### How to use new features: <br/> 
NOTE: PartialEval has method overriding
PartialEval().simplify: <br/>
Parameters 1: (left: String, right: String, result: String) <br/>
Parameters 2: (s: String, set1: Set[Any], set2:Set[Any] <br/>
Output type 1: String <br/>
Output type 2: Set[Any] <br/>
Example of use: <br/>
```
PartialEval().simplify(List("x","*","x")
PartialEval().simplify("intersect", Set(1,"2","x"), Set("x",1,5))
```
Note: Optimize has functions: insert, delete, intersect, union, difference, symdifference, cartesian. All of these output the same type and have an input of the same type.
Here is a description of what each function is doing.
1. For Insert: if set is empty, then return set of what we are trying to insert. NOTE: Assumption is that opt2 is not empty
2. For Delete: if set is of length 1 and only element is element we are trying to delete, then return empty set. NOTE: Assumption is that opt2 is actually in the set
3. For Intersect: if both sets are the same, then we return one of the sets
4. For Union: if both sets are the same, then we return one of the sets
5. For Difference: if both sets are the same, then we return empty set
6. For SymDifference: if 1 set is empty or both sets are the same, then return other set or return empty set
7. For Cartesian: if 1 set is empty, then return  empty set

Optimize().*: <br/>
Parameters: (opt1: Set[Any], opt2: Set[Any]) <br/>
Output type :  Set[Any] <br/>
Example of use: <br/>
```
Optimize().union(Set(1,4),Set(1,4))
```

map: <br/>
Parameters: (f: SetExpression[T] => SetExpression[S]) <br/>
Output type :  SetExpression[S]] <br/>
Example of use: <br/>
```
Set(2).map(e => Optimize().difference(Set(e), Set(2)))
```
### Limitations: <br/>
1. Currently the program only allows unknown variables to be x,y,z when computing PartialEval
2. PartialEval only supports 1 unknown variable at this time.
3. Optimizations are limited to insert, delete, intersect, union, difference, symdifference, cartesian as stated above.
4. Output for map is Set(answer) so if the answer is supposed to be Set(2) the output will be Set(Set(2))
5. PartialEval does not know PEMDAS
6. PartialEval can only reduce up to X^2 so x*x*x will not be reduced correctly.

Thats all for this release, thank you!


# CS474-Homework4
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! I have added some new features to my language. Below I will explain and give examples of how to use the new features.
### To find my language go to: src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: src\test\scala\MyHW4Test.scala <br/>

# How to install
To create your project, 
  1. open up intellij, go to file > new > project...
  2. select scala > sbt > next
  3. download Oracle OpenJDK version 11.0.14
  4. set sbt to 1.6.2
  5. set scala to 3.0
  6. click finish
  
Add the functions from the object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.{WHATEVER CLASSES/DEF YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.{Catch, CatchException, ExceptionClassDef, IF, ThrowException, evaluateFunction}
```
### New Features: <br/>
IF: Evaluates some function pushed in, if it is true will execute some operation else will throw an exception. <br/>
ExceptionClassDef: Users will be able to define an exception class. <br/>
CatchException: Acts as a try block and overseas the evalutations being pushed in. <br/>
ThrowException: Will throw a given exception if one exists otherwise will produce an error message. <br/>
Catch: If exception is thrown and the thrown exception defines some sort of catch, will catch the exception otherwise will produce an error message. <br/>
evaluateFunction: If the IF function returns true then this fucntion will evaluate the function the user passed in. <br/>

### How to use new features: <br/> 
IF: <br/>
Parameters: (condition: => Boolean, thenClause: => Any, elseClause: => Any)
Output type: Any <br/>
Example of use: <br/>
```
IF((3>4), evaluateFunction("difference", Set(1,2,90000), Set(1)),ThrowException("someName","reason", "Check Fail"))
```
ExceptionClassDef: <br/>
Parameters: (exceptionName: String, reason: String, catchBlock: String = null) 
Output type: String <br/>
Example of use: <br/>
```
ExceptionClassDef("someName","reason", "catch")
```
CatchException: <br/>
Parameters: (exceptionName: String, iffunction: Any, someFunc: String = null, opt1: Set[Any] = null, opt2: Set[Any] = null, catchfunction: Any = null) 
Output type: Any <br/>
Example of use: <br/>
```
CatchException("someString", IF((3>4), evaluateFunction("difference", Set(1,2,90000), Set(1)),
        ThrowException("someName","reason", "Check Fail")), "difference", Set(1,2,900).asInstanceOf[Set[Any]], Set(1).asInstanceOf[Set[Any]],
        Catch("catch", Set("var"), "reason",Set("Check Fail").asInstanceOf[Set[Any]], "someName"))
```
ThrowException: <br/>
Parameters: (exceptionName: String, reason: String, valReason: String)
Output type: String <br/>
Example of use: <br/>
```
ThrowException("someName","reason", "Check Fail")
```
Catch: <br/>
Parameters: (storage: String, variable: Set[Any], reason: String, reasonVal: Set[Any], exceptionName: String)
Output type: String <br/>
Example of use: <br/>
```
Catch("catch", Set("var"), "reason",Set("Check Fail").asInstanceOf[Set[Any]], "someName")
```
evaluateFunction: <br/>
Parameters: (func: String, opt1: Set[Any], opt2: Set[Any])
Output type: Set[Any] <br/>
Example of use: <br/>
```
evaluateFunction("difference", Set(1,2,90000), Set(1))
```
NOTE: evaluate function only supports functions: insert, delete, intersect, union, different, and symdifference. <br/>

### Limitations: <br/>
1. Currently the program only allows CatchException to have 1 other function call excluding IF.
2. As mentioned above evaluateFunction only supports insert, delete, intersect, union, different, and symdifference.

Thats all for this release, thank you!

# CS474-Homework3
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! I have added some new classes to my language. Below I will explain and give examples of how to use the new features.
### To find my language go to: src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: src\test\scala\MyHW3Test.scala <br/>

# How to install
To create your project, 
  1. open up intellij, go to file > new > project...
  2. select scala > sbt > next
  3. download Oracle OpenJDK version 11.0.14
  4. set sbt to 1.6.2
  5. set scala to 3.0
  6. click finish
  
Add the functions from the object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.{WHATEVER CLASSES/DEF YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.{AbstractClassDef, ExtendClassDef, InterfaceDef, CallingClass}
```
### Q/A<br/>
1. Can a class/interface inherit from itself? 
    No. This would not be needed because anything inside the class/interfac has access to one another 
2. Can an interface inherit from an abstract class with all pure methods?
    No. Currently this language only allows classes to extend or inherate interface/abstract classes due to the way it was programmed. 
3. Can an interface implement another interface?
    No. Currently this language only allows classes to extend or inherate interface/abstract classes due to the way it was programmed. 
4. Can a class implement two or more different interfaces that declare methods with exactly the same signatures?
    No. Based on the way this program works, if an interface or abstract class wants to create a method with the name of an existing method, it would over write
    the interface.
5. Can an abstract class inherit from another abstract class and implement interfaces where all interfaces and the abstract class have methods with the same signatures?
    No.Based on the way this program works, if an interface or abstract class wants to create a method with the name of an existing method, it would over write
    the interface(even if signature was different).
6. Can an abstract class implement interfaces?
    No. Currently this language only allows classes to extend or inherate interface/abstract classes due to the way it was programmed. 
7. Can a class implement two or more interfaces that have methods whose signatures differ only in return types?
    No. Based on the way this program was coded, every interfaces/class needs to have a unique name.
8. Can an abstract class inherit from a concrete class?
    No. This function would not make sense since the program does not allow the user to create/call an instance of an abstract class.
9. Can an abstract class/interface be instantiated as anonymous concrete classes?
    No. This program is functioned in a way which requires all classes to have a name binded to it.

### New Features: <br/>
AbstractClassDef: Allows user to create their own Abstract Class <br/>
ExtendClassDef: Allows user to create their own Class that wants to use EXTENDS/IMPLEMENTS. Can also create regular classes without EXTENDS/IMPLEMENTS.<br/>
InterfaceDef: Allows user to create their own Interface with body consisting of Abstract Classes <br/>
CallingClass: Allows user to utilize the Classes/Interfaces using definitions above <br/>

### How to use new feature: <br/>
AbstractClassDef: <br/>
Parameters: (className: String, fieldName: List[String], constructorList: List[String] (ex: List(fieldName, fieldValue)), 
methodList: List[String] (ex: List(methodName, methodBody))) <br/>
Output: String <br/>
Example of use: <br/>
```
AbstractClassDef("SomeAbstractClass", List("f"), List("f","2"),  List("union", "tmp = 4")) 
```

ExtendClassDef: <br/>
Parameters: (className: String, fieldName: List[String], constructorList: List[String] (ex: List(fieldName, fieldValue)), 
methodList: List[String] (ex: List(methodName, methodBody)), extendClass: List[String] (ex: List(extends/implements, abstractClass/interface name))) <br/>
Output: String<br/>
Example of use: <br/>
```
ExtendClassDef("SomeClass", List("h"), List("h", "21"), List("difference", "something = 3"), List("extends", "SomeAbstractClass"))
```

InterfaceDef: <br/>
Parameters: (interfaceName: String, fieldName: List[String] , constructorList: List[String] (ex: List(fieldName, fieldValue)), 
methodList: List[String] (ex: List(methodName, methodBody))) <br/>
Output: String<br/>
Example of use: <br/>
```
InterfaceDef("newinter", List("f"), List("f", "23"), List("abstractclass", "someBind = 5"))
```

CallingClass: <br/>
Parameters:(nameCallingClass: String, callingMethodName: String, methParameters: List[Any] = List("","")) <br/>
Output: String<br/>
Example of use: <br/>
```
CallingClass("SomeClass", "union", List(Set(1,2,3),Set(444)))
```

NOTE: there are built in functions such as: 
empty -> returns string of empty set
nonempty -> returns string of "Set(1,2,3,4,5,6,7,8,9)"
union -> finds union of 2 sets; returns union
difference -> finds difference of 2 sets; returns difference

### Limitations: <br/>
1. Currently the program does supposrts only 1 method and 1 field per regualr/abstract class or interface
2. Methods user creates needs to be some variable = some value or just a value (aka cant be built in functions yet, still trying to figure that out)
3. Abstract classes and interfaces can not extend anything



# CS474-Homework2
# Homework 2
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! I have added some new classes to my language. Below I will explain and give examples of how to use the new features.
### To find my language go to: src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: src\test\scala\MyClassTest.scala <br/>

# How to install
To create your project, 
  1. open up intellij, go to file > new > project...
  2. select scala > sbt > next
  3. download Oracle OpenJDK version 11.0.14
  4. set sbt to 1.6.2
  5. set scala to 3.0
  6. click finish
  
Add the classes from the object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.{WHATEVER CLASSES/DEF YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.{ClassDef, ClassCall, ClassCallBool}
```
# Classes
### NOTE: All classes take in sets of type Set[Any]. If user does not provide Sets, classes will use default values set. 
FindIntersect => finds intersection of two sets <br/>
FindDifference => finds difference of two sets <br/>
CheckSet => Checks to see if set2 is subset of set1; Adds set1 and set2 together <br/>
isInSet => Checks to see if set1 is a subset of Set(2,4,6,8) overriding the function checkingSet in the class CheckSet; Will call function from extended class and will add the string name to set <br/>
MainClass => If set passed in is empty, will add 2 elements to it and return to the user <br/>

# Definitions that call classes
## ClassDef <br/>
## NOTE: Sets NEED to be of type Set[Any]. To convert set use: setName.asInstanceOf[Set[Any]]. 
### Case: "intersect0" <br/>
Parameter Type: (s: String) <br/>
Output: Some(MySetTheoryLang$FindIntersect@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("intersect0")
```
### Case: "intersect1" <br/>
Parameter Type: (s: String, set1: Set[Any]) <br/>
Output: Some(MySetTheoryLang$FindIntersect@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("intersect1", set1.asInstanceOf[Set[Any]])
```
### Case: "intersect2" <br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: Some(MySetTheoryLang$FindIntersect@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("intersect2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case: "difference0" <br/>
Parameter Type: (s: String)<br/>
Output: Some(MySetTheoryLang$FindDifference@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("difference0")
```
### Case: "difference1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Some(MySetTheoryLang$FindDifference@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("difference1", set1.asInstanceOf[Set[Any]])
```
### Case: "difference2" <br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: Some(MySetTheoryLang$FindDifference@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("difference2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case: "emptyset0" <br/>
Parameter Type: (s: String)<br/>
Output: Some(MySetTheoryLang$MainClass@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("emptyset0")
```
### Case: "emptyset1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Some(MySetTheoryLang$MainClass@NUMBER<br/>
Example of how to implement: <br/>
```
ClassDef("emptyset1", set1.asInstanceOf[Set[Any]])
```
## ClassCall <br/>
## NOTE: Sets NEED to be of type Set[Any]. To convert set use: setName.asInstanceOf[Set[Any]]. <br/>
### Case: "intersect0" <br/>
Parameter Type: (s: String)<br/>
Output: Set[Any] with intersection of default sets<br/>
Example of how to implement: <br/>
```
ClassCall("intersect0")
```
### Case: "intersect1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Set[Any] with intersection of 1 default set and 1 passed in set<br/>
Example of how to implement: <br/>
```
ClassCall("intersect1", set1.asInstanceOf[Set[Any]])
```
### Case: "intersect2" <br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: Set[Any] with intersection of passed in sets<br/>
Example of how to implement: <br/>
```
ClassCall("intersect2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case: "difference0" <br/>
Parameter Type: (s: String)<br/>
Output: Set[Any] with difference of default sets<br/>
Example of how to implement: <br/>
```
ClassCall("difference0")
```
### Case: "difference1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Set[Any] with difference of 1 default set and 1 passed in set<br/>
Example of how to implement: <br/>
```
ClassCall("difference1", set1.asInstanceOf[Set[Any]])
```
### Case: "difference2" <br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: Set[Any] with difference of passed in sets<br/>
Example of how to implement: <br/>
```
ClassCall("difference2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case: "emptyset0" <br/>
Parameter Type: (s: String)<br/>
Output: Set[Any] with default 2 values<br/>
Example of how to implement: <br/>
```
ClassCall("emptyset0")
```
### Case: "emptyset1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: If set passed in was empty, will return set of type Set[Any] with 2 default values. If set was not empty, would return set passed in.<br/>
Example of how to implement: <br/>
```
ClassCall("emptyset1", set1.asInstanceOf[Set[Any]])
```
### Case: "combineset0" <br/>
Parameter Type: (s: String)<br/>
Output: Set[Any] with combined default sets<br/>
Example of how to implement: <br/>
```
ClassCall("combineset0")
```
### Case: "combineset1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Set[Any] with combined set of 1 default set and 1 passed in set<br/>
Example of how to implement: <br/>
```
ClassCall("combineset1", set1.asInstanceOf[Set[Any]])
```
### Case: "combineset2" <br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: Set[Any] with combined set of default in set and Set("name")<br/>
Example of how to implement: <br/>
```
ClassCall("combineset2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case: "addname0" <br/>
Parameter Type: (s: String)<br/>
Output: Set[Any] with combined set of default in sets<br/>
Example of how to implement: <br/>
```
ClassCall("addname0")
```
### Case: "addname1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: Set[Any] with combined set of passed in set and Set("name")<br/>
Example of how to implement: <br/>
```
ClassCall("addname1", set1.asInstanceOf[Set[Any]])
```
## ClassCallBool<br/>
### Case "checkset0"<br/>
Parameter Type: (s: String)<br/>
Output: true if default set1 is a subset of default in set2 otherwise false<br/>
Example of how to implement: <br/>
```
ClassCallBool("checkset0")
```
### Case "checkset1"<br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: true if default set is a subset of passed in set otherwise false<br/>
Example of how to implement: <br/>
```
ClassCallBool("checkset1", set1.asInstanceOf[Set[Any]])
```
### Case "checkset2"<br/>
Parameter Type: (s: String, set1: Set[Any], set2: Set[Any])<br/>
Output: true if set1 is a subset of set2 otherwise false<br/>
Example of how to implement: <br/>
```
ClassCallBool("checkset2", set1.asInstanceOf[Set[Any]], set2.asInstanceOf[Set[Any]])
```
### Case "checkseteven0"<br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: true if default set is a subset of Set(2,4,6,8) otherwise false<br/>
Example of how to implement: <br/>
```
ClassCallBool("checkseteven0")
```
### Case "checkseteven1" <br/>
Parameter Type: (s: String, set1: Set[Any])<br/>
Output: true if set1 is a subset of Set(2,4,6,8) otherwise false<br/>
Example of how to implement: <br/>
```
ClassCallBool("checkseteven1", set1.asInstanceOf[Set[Any]])
```
# Rules of Classes
As mentioned earlier, this programs classes only take sets of type Set[Any].
Any function that is a part of ClassCall will return a Set[Any], functions that are a part of ClassCallBool will return
a boolean.
Another thing to keep in mind is that if you want to save the Set that is a product of another function such as
Delete(), Union() or any other .eval call, you need to save the Set returned by the function into some val THEN pass it into the functions as mentioned above.


# Homework 1
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! Thank you for deciding to use my set theory language. Below I will explain and give examples of how to use my language. <br/>
### To find my language go to: untitled3\src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: untitled3\src\test\scala\MySetTheoryLangTest.scala <br/>

# How to install
Add the scala object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.ArithExp.{WHATEVER FUNCTIONs/VARIABLES YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.ArithExp.{Insert, ValueSetInt, Check, ValueScopeAssign, Assign, Macro, ValueScopeMacro, Scope,ValueScopeNamed,
  Delete, ScopeAnon}
```
# Variables
ValueSetInt => Holds set of type Integer<br/>
ValueSetString => Holds set of type String<br/>
ValueSetDoub => Holds set of type Double<br/>
ValueSetFloat => Holds set of type Float<br/>
ValueSetChar => Holds set of type Char<br/>
ValueScopePremade => Holds map of pre-made string to set bindings<br/>
ValueScopeAssign => Holds map of string to set binding the user wants to save<br/>
ValueScopeMacro => Holds map of string to set bindings after computing n number of functions on a set<br/>
ValueScopeNamed => Holds map of string to set bindings of a named scope the user created<br/>
# Functions
## NOTE: This program only takes set of type: Int, String, Double, Float & Char
### Insert <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set of type Any<br/>
Eval type: .eval<br/>
Example of how to implement: <br/>
```
Insert(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### Delete <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set with deleted element(s)<br/>
Eval type: .eval<br/>
Example of how to implement: <br/>
```
Delete(ValueSetInt(someSet), ValueSetInt(elementsWantToDelete)).eval
```
### InterSect <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the intersection of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
InterSect(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### Union <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the union of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Union(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### Difference <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing set difference of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Difference(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### SymDifference <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the symmetric difference between the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
SymDifference(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### Cartesian <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the cartesian product of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Cartesian(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
### Assign <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a binding of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
Assign(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
### Scope <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a scope of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
Scope(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
### ScopeAnon <br/>
Parameters: (Set of valid type)<br/>
Output: Returns new set after being in a new scope<br/>
Eval type: .eval<br/>
Example of how to implement:
```
ScopeAnon(ValueSetInt(someSet)).eval
```
### Macro <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a Macro of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
someSet = Union(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
Macro(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
### Check <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: True if found in set; False if not found in set<br/>
Eval type: .eval3<br/>
Example of how to implement:
```
Check(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval3
```
# Rules of language
As mentioned earlier, this program only takes set of type: Int, String, Double, Float & Char.
Any function that is a part of .eval will return a Set[Any], functions that are a part of .eval2 will return
a string with a message, and functions that are part of .eval3 will return a boolean.
Another thing to keep in mind is that if you want to save the Set that is a product of another function such as
Delete(), Union() or any other .eval call, you need to save the Set returned by the function into some val THEN pass it into Macro(), Assign(), etc.
