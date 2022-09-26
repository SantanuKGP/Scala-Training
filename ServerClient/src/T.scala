import scala.io.StdIn.readLine

object T extends App{
  val a = new Thread( () => {
    println("Thread a has started")
  })
  a.setName("hello")
  println(a.getId + " " + a.getName)

  var line=""
  val j= new Object

    new Thread(() => {
    while(line!="close") {
      j.synchronized{
        line = readLine("[You] : ")
        println(line)
        j.notify()
      }
      Thread.sleep(100)
    }
      j.synchronized(j.notify())
  }).start()


  new Thread(() => {
    while(line!="close") {
      j.synchronized{
        j.wait()
        println("hey!! I'm live")
        j.notify()
      }
      Thread.sleep(100)

    }
  }).start()

  println(encoding("How are you? @Baby"))

  def encoding(str: String):String ={
    val regex= "@\\w+".r
    "San"+":::"+regex.findFirstIn(str).get.filter(_!='@')+":::"+regex.split(str).reduce(_ +":::" + _)
  }
  /*val b = new Thread( () => {

    println("Thread b has started")
//    var temp=0
//    while(true) {
//      temp +=1
//      println("Interruption: " + temp)
//    }
  })
  println(b.getId + " " + b.getName)
  b.start()
  Thread.sleep(100)
  println()*/
}
