import scala.io.StdIn._
import scala.collection.mutable.Map
object practice extends App{
  var m= Map[Char, Int]().withDefaultValue(0)
  val word="STRAIGHT"
  word.foreach(c => m(c)=m(c)+1)
  m.keys.foreach{ x=>
    println(x + " : " +m(x))
  }
  println(word.length)
}
/*
  1. Learning from another problem's part:
  val str=Array.range(1,3).foldLeft("{0}")((x,y) => x+ " {" + y + "}")
  var sum0 = readf(str).map(_.toString.toInt)
  println(sum0.sum)

  2. FOR MUTABLE we need aliasing Map,as example
  import scala.collection.mutable.{Map => mMap}
*/