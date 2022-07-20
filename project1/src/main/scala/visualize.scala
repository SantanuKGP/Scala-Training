import scala.util.Random
import scala.collection.mutable.Map


import org.nspl._
import org.nspl.awtrenderer._
import scala.util.Random.nextDouble

object visualize extends App{
  val random= new Random()
  val n=10
  var it=10000
  var map = Map[Int,Int]().withDefaultValue(0)
  while( it >0){
    val x= random.nextInt(n)
    println(x)
    map(x)=map(x)+ 1
    it= it-1
  }
  for(i <- 0 until n) println(s"$i : ${map(i)}")
}
