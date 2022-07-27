import scala.io.StdIn.readLine

object problem16 extends App{
  var str=readLine()
  while(str!=""){
    val reg=readLine()
    val regex= reg.r
    val list= regex.findAllMatchIn(str)
    var maxC=0
    for(i <- list) maxC=maxC max i.toString().length
    println(maxC)
    str=readLine()
  }
}
