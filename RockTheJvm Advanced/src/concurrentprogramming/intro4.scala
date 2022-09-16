package concurrentprogramming

object intro4 extends App{
  val threadHello= new Thread( ()=> (1 to 5).foreach(_ => println("thread 0: hello ")))
  val threadBye= new Thread( ()=> (1 to 5).foreach(_ => println("thread 1: bye " )))
  threadHello.start()
  threadHello.join()
  threadBye.start()
}
