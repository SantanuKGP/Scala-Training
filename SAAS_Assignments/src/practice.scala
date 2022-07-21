import scala.io.StdIn.readLine
import scala.collection.mutable.Map
object practice extends App{
  val m=Map[Int,Int](1->10,18->2,2->9,6->3,5->6)
  m.keys.foreach{ i=> println(i + " : " + m(i))}

}
