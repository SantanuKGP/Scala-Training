import scala.io.StdIn._
import scala.math.pow
object problem6 extends App{
  val n=readInt()
  for(i <- 1 to n){
    val num=readLine()
    if(num.forall(_.isDigit)) num2str(num) else str2num(num)
  }

  def str2num(str:String):Unit={
    val m = Map[Char, Int]('I'-> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' ->500, 'M' -> 1000)
    var num=0
    for(i <- 0 until str.length-1){
      if(m(str(i))< m(str(i+1))) num=num-m(str(i))
      else num=num+m(str(i))
    }
    println(num+m(str(str.length-1)))
  }


  def num2str(num :String):Unit={
    val m=Map[Int,String](1-> "I", 4->"IV", 5 -> "V", 9->"IX",10 -> "X",40-> "XL", 50->"L",90->"XC", 100 -> "C",400->"CD", 500 -> "D", 900->"CM",1000->"M")
    var s=""
    var mc=pow(10,num.length-1).toInt
    var n=num.toInt
    var nl=num.length
    while (mc>=1) {
      val res=n/mc
      if(res!=0 && res<4) s=s+ m(1*mc)*res
      else if(res!=0 && (res<6 || res==9 ) && nl<4) s=s+ m(res*mc)
      else if (res!=0 && nl<4) s=s+m(5*mc)+m(mc)*(res-5)
      else if(res!=0 && nl>=4) s=s+ "M" * res
      nl=nl-1
      n=n-res*mc
      mc=mc/10
    }
    println(s)
  }
}
