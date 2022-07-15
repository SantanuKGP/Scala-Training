import scala.annotation.tailrec
object problemset2 extends App{


  @tailrec
  def stringConcat(ip:String,n:Int):String = {
    if(n==1) ip
    else stringConcat(ip+ip,n-1)
  }
  println(stringConcat("xp",2))


  def factorial(n:Int):Int = {
    @tailrec
    def fact(n: Int, acc: Int=1):Int={
      if(n<=1) acc
      else fact(n-1,acc*n)
    }
    fact(n)
  }
  println(factorial(4))
}
