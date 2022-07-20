import scala.collection.mutable.Map
import scala.io.StdIn.readInt

object problem9 extends App{
  var m= Map[Int,Int]()
  var x=readInt()
  var y=readInt()
  while(x!=1 && y!=1){
    m(y)=x
    x=readInt()
    y=readInt()
  }
  x=readInt()
  y=readInt()
  while(x!=1 && y!=1){
    m(y)=m(y)+x
    x=readInt()
    y=readInt()
  }
  m.keys.foreach{ i =>
    print(m(i)+" " )
    print(i+" ")
  }
}
