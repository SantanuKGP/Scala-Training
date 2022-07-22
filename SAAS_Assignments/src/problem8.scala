import scala.annotation.tailrec
import scala.io.StdIn.readLine

object problem8 extends App{
  @tailrec
  def factorial(n: Int, acc: Int=1):Int={
    if(n==0) acc else factorial(n-1,n*acc)
  }
  val input=readLine().split(" ").map(_.toInt)
  val len=input(0)
  var query=input(1)-1
  var str="abcdefghijklmnopqrstuvwxyz".slice(0,len)
  var fact_list= Array.range(0,len).map(x => factorial(x)).reverse//.foreach(println(_))
  var result=""
  while(!str.isEmpty){
    val char_ = str(query / fact_list.head)
    query=query % fact_list.head
    str=str.filter(_!=char_)
    result=result+char_
    fact_list=fact_list.tail
  }
  println(result)
}
