package concurrentprogramming

object intro3 extends App{
  val aThread=new Thread( new Runnable {
    override def run(): Unit = {
      println("Running in parallel")
    }
  })
  aThread.start()
  println("="*20)

  val aThread2=new Thread(new Runnable {
    override def run(): Unit = println("Running in parallel")
  })
  aThread2.start()
  aThread2.join() // blocks until aThread finishes running!
  println("="*20)
}
