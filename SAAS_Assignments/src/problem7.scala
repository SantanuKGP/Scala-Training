import scala.collection.mutable.Map
import scala.io.StdIn.readLine

object problem7 extends App{
  val str=readLine().split(" ").map(_.toInt)
  val r=str.head
  var x=Array[Array[Int]]()
  val c=str(1)
  var row=Map[Int,Int]().withDefaultValue(0)
  var col=Map[Int,Int]().withDefaultValue(0)
  for(i<- 0 until r){
    val input=readLine().split(" ").map(_.toInt)
    x=x :+ input
    for(j<- 0 until c) {if(input(j)!=0){row(i)=1; col(j)=1} }
  }
  val rowf= row.filter(_._2 !=0)
  val colf= col.filter(_._2 !=0)
  for(i <- rowf.keys){
    for(j <- colf.keys){
      print(x(i)(j)+" ")
    }
    println()
  }
}
