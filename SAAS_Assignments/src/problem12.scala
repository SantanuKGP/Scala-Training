import scala.collection.mutable
import scala.io.StdIn.readLine

object problem12 extends App{
  val input=readLine().split(" ").map(_.toInt)
  val row=input(0)
  val col= input(1)
  var grid=Array[Array[String]]()
  var (e_col,e_row) =(-1,-1)
  var (x_col,x_row) =(-1,-1)
  var pathLength=0
  for(i <- 0 until row){
    val y=readLine().split(" ")
    grid= grid :+ y
    if(y.contains("#")) { e_col=y.takeWhile(_!="#").length;  e_row= i}
    if(y.contains("@")) { x_col=y.takeWhile(_!="@").length;  x_row= i}
  }

  def checkPath(r : Int, c : Int): Boolean= if(r<0 || r>=row || c<0 || c>=col ) false else grid(r)(c)=="1" || grid(r)(c)=="@"
  def endPath(r : Int, c : Int): Boolean= if(r<0 || r>=row || c<0 || c>=col ) false else grid(r)(c)=="@"
  var queue= mutable.Queue[Array[Int]](Array(e_row,e_col,pathLength))

  while(!queue.isEmpty){
    if(endPath(queue.head(0),queue.head(1))) println(queue.head(2))
    if(checkPath(queue.head(0)+1,queue.head(1))) queue.enqueue(Array(queue.head(0)+1,queue.head(1),queue.head(2)+1))
    if(checkPath(queue.head(0)-1,queue.head(1))) queue.enqueue(Array(queue.head(0)-1,queue.head(1),queue.head(2)+1))
    if(checkPath(queue.head(0),queue.head(1)+1)) queue.enqueue(Array(queue.head(0),queue.head(1)+1,queue.head(2)+1))
    if(checkPath(queue.head(0),queue.head(1)-1)) queue.enqueue(Array(queue.head(0),queue.head(1)-1,queue.head(2)+1))

    if(endPath(queue.head(0),queue.head(1))) queue.clear() else queue.dequeue()
  }
}
