package concurrentprogramming

object concurrency extends App{
  def runInParallel:Unit={
    var x=0

    val thread1= new Thread(()=>{x=1})
    val thread2= new Thread(()=>{x=2})
    thread1.start()
    thread2.start()
    Thread.sleep(1)
    println(x)
  }
  for(_ <-1 to 1000) runInParallel // race condition, better to avoid this situation
}
