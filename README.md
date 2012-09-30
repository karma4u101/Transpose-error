Transpose error
===============

This is a stripped down example taken from (ref 1)[1] (well there may still be some noice) that will produce an "hard to locate" error when set-up to run with 
Lift 2.5-M1. The same code runs without any problem with Lift 2.4. 

To reproduce the error (and/or make it run) follow the steps bellow.

1) Get the stack trace 
 
	Transpose-error$ ./sbt 
	> compile
	> last 

**The Problem:**
The stack trace does not hint on the orgin of the problem (in the project code) although you will be able to fix the problem from within  
the project code (in this case in code.snippet.Countries).
The "unsufficient information" (from user perspective) in the stack trace is from a user point of view the problem but the real problem 
seems to be with some implicite clash outside the "users code" in the project. 
In 2.4 the code worked without a added net.liftweb.record.MandatoryTypedField **is** call (via implicite), in 2.5-M1 without a explicite 
**get** or **is** call the code will not compile.

2) Make it run 

Edit and change the **last line** in the **render method** in **code.snippet.Countries** from: 
 
	object Countries extends Loggable {
	:
	def render = { 
	:
	"* *" #> (c.name)

To
 
	"* *" #> (c.name.get)
 
[1] https://github.com/karma4u101/Templating-With-Twitter-Bootstrap


