import scala.annotation.tailrec
import scala.collection.immutable._
//import scala.util.control.Breaks.break
object Main {
  def main(args: Array[String]): Unit = {
//    println(BigInt("340282366920938463463374607431768211456"))
//    fibonacci(10000)
    val x:List[Int]=List(10,1,12,13)
    println(x.toString)
    println(x.foldLeft("")((x,y)=>x+y))

    /*
    println(len(x,0))
    var count =x.foldLeft(0)((x,y)=> x+1);
    println(count,x.foldRight(0)((x,y)=> y+1))

 */

  }
  /*
  @tailrec
  def test(x : Int, n: Int):Any ={
    if(x <= n) {println(fib(0,1,x)); test(x+1,n);}
    else return
  }
   */
  def fibonacci(n: Long): Long= fib(0,1,n)
  @tailrec
  def fib(x1 : Long, x2 : Long, n : Long) : Long={
    if(n==0) x1
    else {
      println(x1)
      fib(x2, x1+x2,n-1)}
  }

  @tailrec
  def len(x : List[Int],count: Int):Int={
    if(x.isEmpty) count
    else len(x.tail,count+1)
  }

}