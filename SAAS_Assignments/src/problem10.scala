import scala.io.StdIn.readLine

object problem10 extends App{
  def isIntersect(v: Array[Int], h: Array[Int]):Boolean={
    (h(1) min h(2))<= v(0) && (h(1) max h(2))>= v(0) && (v(1) min v(2))<= h(0) && (v(1) max v(2))>= h(0)
  }
  val Array(v,h)=readLine().split(" ").map(_.toInt)
  var VL= Array[Array[Int]]()
  var HL= Array[Array[Int]]()
  for(i<- 0 until v){
    VL=VL :+ readLine().split(" ").map(_.toInt)
  }
  for(i<- 0 until h){
    HL=HL :+ readLine().split(" ").map(_.toInt)
  }
  var acc=0
  for(i <- VL; j<- HL){
    if(isIntersect(i,j)) acc=acc+1
  }
  println(acc)
}
