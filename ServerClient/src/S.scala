import scala.io.StdIn.readLine

case class S() {
  var x = Map[String,Int]()
  while(true){
    val str = readLine()
    x +=(str -> 1)
  }

  def isEx(y:String):Boolean={
    try{
      x(y)
      true
    }
    catch{
      case _:Throwable => false
    }
  }
}

