package AdvanceFunction

object partialFunction extends App{

  val aLambda= (x:Int)=> x+1
  val aList=List(1,2,5,45,85,123)


  val aPartialFunction: PartialFunction[Int,Int]={
    case 1=> 42
    case 2=> 56
    case 5 => 999
  }
  println(aPartialFunction(2))
  println(aPartialFunction.unapply(2))
  println(aPartialFunction.isDefinedAt(52))
  println(aPartialFunction.unapply(52))
// unapply method gives us Option[Int]
//  You can use the same thing using lift
  val lifted=aPartialFunction.lift
  println(lifted(2))
  println(lifted(52))

//  orElse method helps to extend the function in more domain without changing the parent function

  val pfChain=aPartialFunction.orElse[Int,Int]{
    case 52 => 70
  }
  println(pfChain(52))
  val aMappedList=List(1,2,3).map{
    case 1 => 42
    case 2 => 78
    case 3 =>100
  }
  println(aMappedList)
//  PF can have only one  parameter type
  val aManualFussyFunction= new PartialFunction[Int,Int] {
    def apply(x:Int):Int= x match{
      case 1=> 42
      case 2=> 56
      case 5 => 999
    }
    def isDefinedAt(x:Int):Boolean= x==1 || x==2 || x==5
  }
  val chatBot : PartialFunction[String,String]={
    case "hello" => "Hey hey I am Elon"
    case "bye"  => "Try to bye me"
  }
  scala.io.Source.stdin.getLines().foreach( line =>  println("you said: "+ line))

}
