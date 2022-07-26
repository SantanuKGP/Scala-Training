import scala.io.StdIn.{readInt, readLine}
import scala.collection.mutable.{Map => mMap}
object problem14 extends App{
 val x=readInt()
  var m= mMap[String,String]()
  Range(0,x).foreach{ _ =>
    val str=readLine()
    m(str.filter(_!='-'))=str
  }
  val str=readLine()
  val wordList=str.split(" ")
}
