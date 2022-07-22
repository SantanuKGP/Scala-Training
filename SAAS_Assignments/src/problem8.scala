import scala.annotation.tailrec
import scala.io.StdIn.readLine

object problem8 extends App{
  @tailrec
  def factorial(n: Int, acc: Int=1):Int={
    if(n==0) acc else factorial(n-1,n*acc)
  }
  val input=readLine().split(" ").map(_.toInt)
  val len=input(0)
  val query=input(1)
  val str="abcdefghijklmnopqrstuvwxyz".slice(0,len)
  val fact_list= Array.range(1,len).map(x => factorial(x)).foreach(println(_))
  var result=""
}
