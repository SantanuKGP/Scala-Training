import scala.concurrent.duration.DurationDouble
import scala.util.Random

object validation extends App{
  def Password():String={
    val charSet=('a' to 'z') ++ ('A' to 'Z') ++ "@#$!-"
    val random= new Random()
    val size=random.between(8,11)
    var str=""
    while(str.length<size){
      val index= random.nextInt(charSet.length)
      if(!str.contains(charSet(index))) str += charSet(index)
    }
    str
  }

}
