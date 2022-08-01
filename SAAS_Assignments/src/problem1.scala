import scala.io.StdIn._
import Math._
object problem1 extends App{
  var str=readLine("Enter the values of side: ")
  while(str!=""){
    val x=str.split(" ").map(_.toDouble)
    new Triangle(x(0),x(1),x(2))
    str=readLine("Enter the values of side: ")
  }
}
class Triangle(x1:Double,x2: Double, x3: Double ){
  //  tolerance for dim 1
  def tol1(x : Double,y : Double) : Boolean = abs(x-y)<1e-3
  //  tolerance for dim 2
  def tol2(x : Double,y : Double) : Boolean = abs(x-y)<1e-6
  // logic for existence
  def logic(x: Double,y : Double, z: Double):Boolean= (x+y-z)> 0

  var typeCode: Int = if(logic(x1,x2,x3) && logic(x2,x3,x1) && logic(x3,x1,x2)) 1 else 0
  typeCode = if(tol1(x1,x2) || tol1(x2,x3)) 3 else typeCode
  typeCode= if(tol1(x1,x2) && tol1(x2,x3)) 2 else typeCode
  typeCode = if(tol2(x1*x1+x2*x2,x3*x3) || tol2(x2*x2+x3*x3,x1*x1) || tol2(x3*x3+x1*x1,x2*x2)) 4 else typeCode

  typeCode match{
    case 0 => println("Does not exist")
    case 1 => println("Not Special")
    case 2 => println("Equilateral")
    case 3 => println("Isosceles")
    case 4 => println("Right Angled")
    case _ => println("Error Code ")
  }

}