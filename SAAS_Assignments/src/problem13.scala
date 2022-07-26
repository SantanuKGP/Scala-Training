import scala.io.StdIn.readLine
import scala.collection.mutable.Map
object problem13 extends App{
  val x = readLine().split(" ").filter(_!="").map(_.toInt).tail.grouped(2).toArray
  val y = readLine().split(" ").filter(_!="").map(_.toInt).tail.grouped(2).toArray
  var i1=0
  var i2=0
  var result=Array[Array[Int]]()
  if(x(i1).head < y(i2).head){
    result=result :+ x(i1)
    i1=i1+1
  }
  else {
    result=result :+ y(i2)
    i2=i2+1
  }
  while(i1< x.length || i2<y.length){
    if(i1<x.length && i2<y.length){
      if( x(i1).head < y(i2).head){
        if(result.last(1)>= x(i1).head) result.last(1)= x(i1)(1) max result.last(1)
        else result=result :+ x(i1)
        i1=i1+1
      }
      else{
        if(result.last(1)>= y(i2).head) result.last(1)= y(i2)(1) max result.last(1)
        else result=result :+ y(i2)
        i2=i2+1
      }
    }
    if(i1==x.length && i2<y.length){
      if(result.last(1)>= y(i2).head) result.last(1)= y(i2)(1) max result.last(1)
      else result=result :+ y(i2)
      i2=i2+1
    }
    if(i1<x.length && i2==y.length){
      if(result.last(1)>= x(i1).head) result.last(1)= x(i1)(1) max result.last(1) else result=result :+ x(i1)
      i1=i1+1
    }
  }

  var res= result.flatten
  res = result.length +: res
  println(res.foldLeft("")((x,y)=> x+y+" "))
}
