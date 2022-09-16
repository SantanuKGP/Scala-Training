package concurrentprogramming

object concurrence4 extends App{
  var x=0
  val threads= (1 to 100).map(_ => new Thread(() => x+=1))
  threads.foreach(_.start())
  threads.foreach(_.join())
  println(x) //print anything
}
