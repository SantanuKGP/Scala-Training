import scala.io.StdIn.{readInt, readLine}
import scala.collection.mutable.{Map => mMap}
object problem11 extends App{
  var x=readInt()
  var m= mMap[Int,Int]().withDefaultValue(0)
  Range(0,x).foreach{ _ =>
    val y=readLine().split( " ").map(x=> x.toInt)
    m(y(0))=y(1)
  }
  x=readInt()
  var (a,b)=(0,0)
  val y=readLine().split(" ").map(_.toInt)
  for(i <- 0 until x by 2){
    a=a+y(i)
    b=b+y(i+1)
    while(m.contains(a)) a=m(a)
    while(m.contains(b)) b=m(b)
  }
  if(a>b) println("A "+a) else println("B "+b)
}
