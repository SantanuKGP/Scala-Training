package concurrentprogramming

import java.util.concurrent.Executors

object intro6 extends App{
  //executors
  val pool= Executors.newFixedThreadPool(10)
  pool.execute(()=> println("something in the thread pool"))
  pool.execute(()=>{
    Thread.sleep(1000)
    println("Done after 1 second")
  })

  pool.execute(()=>{
    Thread.sleep(10000)
    println("Almost done")
    Thread.sleep(10000)
    println("Done after 21 seconds")
  })
  pool.shutdown()
  println(pool.isShutdown)
}
