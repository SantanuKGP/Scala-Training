object WhatAFunction extends App{
  val doubler= new MyFunction[Int,Int]{
    override def apply(element : Int) : Int = element*2
  }
  println(doubler(4))
  val strToInt= new Function1[ String ,Int]{
    override def apply (str : String) : Int= str.toInt
  }
  println(strToInt("34")+56)

  val adder :((Int,Int)=> Int) = new Function2 [Int, Int, Int]{
    override def apply(a: Int, b: Int): Int= a+b
  }
  println(adder(40,60))

//  def concatenator : (String, String) => String = new
}
trait MyFunction[A,B] {
  def apply(element: A) : B
}