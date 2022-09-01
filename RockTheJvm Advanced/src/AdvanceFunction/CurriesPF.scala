package AdvanceFunction

object CurriesPF extends App{
  val supperAdder:Int => Int => Int= x=>y=>x-y
  val add3=supperAdder(3)
  println(add3(5))

  def curriedAdder(x:Int)(y:Int):Int=x-y
  //lifting
  val add4: Int=>Int=curriedAdder(3)
  println(add4(5))

  val add5=curriedAdder(5) _ //Int=>Int
  // Exercise
  val simpleAddFunction=(x:Int,y:Int)=>x+y
  def simpleAddMethod(x:Int,y:Int):Int=x+y
  def curriedMethod(x:Int)(y:Int):Int=x+y

  val add7=(x: Int) => simpleAddFunction(7,x)
  val add7_2 =simpleAddFunction.curried(7)
  val add7_3=simpleAddFunction(7,_)

  val add7_4 = curriedMethod(7) _
  val add7_5 = curriedMethod(7) (_)

  val add7_6 = simpleAddMethod(7, _:Int)
  val add7_7 = simpleAddMethod(7, _)
  println(add7_3(3))

  println("%4.2f".format(Math.PI))
  println(f"${Math.PI}%8.6f")
//  println(Math.PI.toString.format(4.1))

  def curriedFormatter(s:String)(number:Double):String=s.format(number)

  val numbers=List(Math.PI, Math.E, 1,9.8, 1.3e-12)

  val simpleFormat= curriedFormatter("%4.2f") _
  val seriousFormat= curriedFormatter("%8.6f") _
  val preciseFormat= curriedFormatter("%14.12f") _

  println(numbers.map(simpleFormat))
  println(numbers.map(curriedFormatter("%4.2f"))) // compiler does Eta expansion

  def byName(n: => Int):Int=n+1
  def byFunction(f:()=>Int):Int=f()+1

  def method:Int=42
  def parentMethod():Int=42

  println(byName(23))
  println(parentMethod())
  println(parentMethod) //ok, not good use
//  println( byName(()=> 42))
  println( byName( (()=> 42)() )  )
  
}
