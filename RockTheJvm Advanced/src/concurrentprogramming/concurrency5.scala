package concurrentprogramming

object concurrent5 extends App{
  var message= ""
  val awesomeThread= new Thread ( () => {
    Thread.sleep(1000)
    message = "Scala is awesome"
  })
  message = "Scala sucks"
  awesomeThread.start()
  Thread.sleep(2000)
  println(message)
}
