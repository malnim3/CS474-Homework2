# CS474-Homework2
# Homework 2
Mashel Al-Nimer <br/>
malnim3@uic.edu <br/>
Hello! I have added some new classes to my language. Below I will explain and give examples of how to use the new features.
### To find my language go to: untitled3\src\main\scala\MySetTheoryLang.scala <br/>
### To find my test file go to: untitled3\src\test\scala\MyClassTest.scala <br/>

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
