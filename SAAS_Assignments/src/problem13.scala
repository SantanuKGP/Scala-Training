import scala.io.StdIn.readLine
import scala.collection.mutable.Map
object problem13 extends App{
  val x = readLine().split(" ").map(_.toInt).tail
  val y = readLine().split(" ").map(_.toInt).tail
  val m= Map[Int,Int]()
  for(i <- 0 until x.length by 2){
    m(x(i))=x(i+1)
  }
  for(i <- 0 until x.length by 2){
    m(y(i))=y(i+1)
  }
}
