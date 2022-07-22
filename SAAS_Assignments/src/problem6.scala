import scala.io.StdIn._
package scala.io{}
object problem6 extends App{
  val map_roman2_int= Map[Char,Int]()

}
/*
Learning from another problem's part:
  val str=Array.range(1,3).foldLeft("{0}")((x,y) => x+ " {" + y + "}")
  var sum0 = readf(str).map(_.toString.toInt)
  println(sum0.sum)
*/