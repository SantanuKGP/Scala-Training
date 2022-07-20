import scala.io.StdIn._
import Math._
object problem1 extends App{
  var str=readLine("Enter the values of side: ")
  while(str!=""){
    val x=str.split(" ").map(_.toDouble)
    printType(x(0),x(1),x(2))
    str=readLine("Enter the values of side: ")
  }

  //  tolerance for dim 1
  def tol1(x : Double,y : Double) : Boolean = if(abs(x-y)<1e-3) true else false
  //  tolerance for dim 2
  def tol2(x : Double,y : Double) : Boolean = if(abs(x-y)<1e-6) true else false
  // logic for existence
  def logic(x: Double,y : Double, z: Double)= if((x+y-z)> 0) true else false
  // triangle exists or not
  def existence(x1: Double, x2: Double, x3 : Double): Boolean ={
    logic(x1,x2,x3) && logic(x2,x3,x1) && logic(x3,x1,x2)
  }
  def isEquilateral(x1 : Double,x2 : Double, x3 : Double) : Boolean={
    if(tol1(x1,x2) && tol1(x2,x3)) true
    else false
  }
  def isIsosceles(x1 : Double,x2 : Double, x3 : Double) : Boolean={
    if(tol1(x1,x2) || tol1(x2,x3)) true
    else false
  }
  def rightAngled(x1 : Double,x2 : Double, x3 : Double) : Boolean={
    tol2(x1*x1+x2*x2,x3*x3) || tol2(x2*x2+x3*x3,x1*x1) || tol2(x3*x3+x1*x1,x2*x2)
  }
  def printType(x1 : Double,x2 : Double, x3 : Double) : Unit={
    if(!existence(x1,x2,x3)) println("Does not exist")
    else if(isEquilateral(x1,x2,x3)) println("Equilateral")
    else if(rightAngled(x1,x2,x3)) println("Right Angled")
    else if(isIsosceles(x1,x2,x3)) println("Isosceles")
    else println("Not Special")
  }

}
