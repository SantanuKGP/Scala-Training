package concurrentprogramming

object intro2 extends App{
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
  runnable.run() // don't run in parallel
  println("="*20)
}
