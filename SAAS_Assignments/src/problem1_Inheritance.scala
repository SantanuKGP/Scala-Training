import java.lang.Math.abs
import scala.io.StdIn.readLine

object problem1_Inheritance extends App{
  var str=readLine("Enter the values of side: ")
  while(str!=""){
    val x=str.split(" ").map(_.toDouble)
    new Category(x)
    str=readLine("Enter the values of side: ")
  }
}

sealed class TriangleType(x: Array[Double]){
  val Array(x1,x2,x3)=x
  //  tolerance for dim 1
  def tol1(x : Double,y : Double) : Boolean = abs(x-y)<1e-3
  //  tolerance for dim 2
  def tol2(x : Double,y : Double) : Boolean = abs(x-y)<1e-6
  // logic for existence
  def logic(x: Double,y : Double, z: Double):Boolean= (x+y-z)> 0

  lazy val isExists :Boolean= logic(x1,x2,x3) && logic(x2,x3,x1) && logic(x3,x1,x2)
  lazy val IsRightAngled: Boolean= tol2(x1*x1+x2*x2,x3*x3) || tol2(x2*x2+x3*x3,x1*x1) || tol2(x3*x3+x1*x1,x2*x2)
  lazy val IsEquilateral: Boolean=tol1(x1,x2) && tol1(x2,x3) && tol1(x3,x1)
  lazy val IsIsosceles: Boolean=tol1(x1,x2) || tol1(x2,x3) || tol1(x3,x1)
}
sealed class RightAngled(x:Array[Double]) extends TriangleType(x: Array[Double]){
  if(isExists && IsRightAngled) println("Right Angled")
}

sealed class Equilateral (x:Array[Double]) extends TriangleType(x: Array[Double]){
  if(isExists && IsEquilateral) println("Equilateral")
}

sealed class Isosceles (x:Array[Double]) extends TriangleType(x: Array[Double]){
  if(isExists && IsIsosceles && !IsRightAngled && !IsEquilateral) println("Isosceles")
  if(isExists && !IsRightAngled && !IsEquilateral && !IsIsosceles) println("Not Special")
  if(!isExists) println("Invalid")
}
sealed class Category (x:Array[Double]) extends TriangleType(x: Array[Double]){
  new RightAngled(x)
  new Equilateral(x)
  new Isosceles(x)
}