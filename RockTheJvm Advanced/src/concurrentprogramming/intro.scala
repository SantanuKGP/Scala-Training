package concurrentprogramming

object intro extends App{
  val aThread=new Thread( new Runnable {
    override def run(): Unit = {
      println("Running in parallel")
    }
  })
  aThread.start()
  println("="*20)

  val runnable=new Runnable {
    override def run(): Unit = println("Running in parallel")
  }
  val aThread2=new Thread(runnable)
  aThread2.start()
  // comment out below line and check the difference
//  Thread.sleep(1)
  println("="*20)

}
