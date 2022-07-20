import scala.io.Source._
//import scala.io.StdIn._
object problem2 extends App{
  val x= fromFile("file.txt")
  var acc=0
  var it=x.getLines()
  while(it.hasNext){
    val y=it.next()
    acc+= y.split(" ").filter(_ !=' ').filter(_ !="").length
  }
  println(acc)
}
