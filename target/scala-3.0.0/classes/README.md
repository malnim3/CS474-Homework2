#Homework 1
Hello! Thank you for deciding to use my set theory language. Below I will explain and give examples of how to use my language. <br/>
###To find my language go to: untitled3\src\main\scala\MySetTheoryLang.scala <br/>
###To find my test file go to: untitled3\src\test\scala\MySetTheoryLangTest.scala <br/>

#How to install
Add the scala object named MySetTheoryLang to the folder you are sorting your code in. Import
MySetTheoryLang.ArithExp.{WHATEVER FUNCTIONs/VARIABLES YOU WANT TO USE}. Below is an example of how to do this.
```
import MySetTheoryLang.ArithExp.{Insert, ValueSetInt, Check, ValueScopeAssign, Assign, Macro, ValueScopeMacro, Scope,ValueScopeNamed,
  Delete, ScopeAnon}
```
#Variables
ValueSetInt => Holds set of type Integer<br/>
ValueSetString => Holds set of type String<br/>
ValueSetDoub => Holds set of type Double<br/>
ValueSetFloat => Holds set of type Float<br/>
ValueSetChar => Holds set of type Char<br/>
ValueScopePremade => Holds map of pre-made string to set bindings<br/>
ValueScopeAssign => Holds map of string to set binding the user wants to save<br/>
ValueScopeMacro => Holds map of string to set bindings after computing n number of functions on a set<br/>
ValueScopeNamed => Holds map of string to set bindings of a named scope the user created<br/>
#Functions
##NOTE: This program only takes set of type: Int, String, Double, Float & Char
###Insert <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set of type Any<br/>
Eval type: .eval<br/>
Example of how to implement: <br/>
```
Insert(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###Delete <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set with deleted element(s)<br/>
Eval type: .eval<br/>
Example of how to implement: <br/>
```
Delete(ValueSetInt(someSet), ValueSetInt(elementsWantToDelete)).eval
```
###InterSect <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the intersection of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
InterSect(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###Union <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the union of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Union(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###Difference <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing set difference of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Difference(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###SymDifference <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the symmetric difference between the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
SymDifference(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###Cartesian <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: Set containing the cartesian product of the two sets pushed in<br/>
Eval type: .eval<br/>
Example of how to implement:
```
Cartesian(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
```
###Assign <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a binding of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
Assign(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
###Scope <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a scope of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
Scope(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
###ScopeAnon <br/>
Parameters: (Set of valid type)<br/>
Output: Returns new set after being in a new scope<br/>
Eval type: .eval<br/>
Example of how to implement:
```
ScopeAnon(ValueSetInt(someSet)).eval
```
###Macro <br/>
Parameters: (Some string, Set of valid type)<br/>
Output: Returns string letting user know if there is already a Macro of the string passed in or if it was added<br/>
Eval type: .eval2<br/>
Example of how to implement:
```
someSet = Union(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval
Macro(ValueScopeAssign("someString"), ValueSetInt(someSet)).eval2
```
###Check <br/>
Parameters: (Set of valid type, Set of valid type)<br/>
Output: True if found in set; False if not found in set<br/>
Eval type: .eval3<br/>
Example of how to implement:
```
Check(ValueSetInt(someSet), ValueSetInt(someOtherSet)).eval3
```
#Rules of language
As mentioned earlier, this program only takes set of type: Int, String, Double, Float & Char.
Any function that is a part of .eval will return a Set[Any], functions that are a part of .eval2 will return
a string with a message, and functions that are part of .eval3 will return a boolean.
Another thing to keep in mind is that if you want to save the Set that is a product of another function such as
Delete(), Union() or any other .eval call, you need to save the Set returned by the function into some val THEN pass it into Macro(), Assign(), etc.