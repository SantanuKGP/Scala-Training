import scala.io.StdIn.readLine
import scala.collection.mutable.Map
object problem13 extends App{
  val x = readLine().split(" ").map(_.toInt).tail.sliding(2).toArray
  val y = readLine().split(" ").map(_.toInt).tail.sliding(2).toArray
  var i1=0
  var i2=0
  var result=Array[Array[Int]]()
  while(i1< x.length || i2<y.length){
    if(x(i1).head < y(i2).head){
      if(result.last(1)>= x(i1).head) result.last(1)= x(i1)(1)
      else result=result :+ x(i1)
      i1=i1+1
    }
    if(x(i1).head > y(i2).head){
      if(result.last(1)>= y(i2).head) result.last(1)= y(i2)(1)
      else result=result :+ y(i2)
      i2=i2+1
    }
    if(i1==x.length) {result= result :++ y.slice(i2, y.length); i2=y.length}
    if(i2==y.length) {result= result :++ x.slice(i2, x.length); i1=x.length}
  }
  var res= result.flatten
  res = result.length +: res
  println(res.foldLeft("")((x,y)=> x+y+" "))
}
