import scala.collection.mutable.Map
import scala.io.StdIn.{readLine, readInt}

object problem9 extends App{
  def polySum(): Unit = {
    val m = Map[Int, Int]().withDefaultValue(0)
    val x = readLine().split(" ").map(_.toInt)
    val y = readLine().split(" ").map(_.toInt)
    for (i <- 0 until x.length - 2 by 2) {
      m(x(i + 1)) = x(i)
    }
    for (i <- 0 until y.length - 2 by 2) {
      m(y(i + 1)) = m(y(i + 1)) + y(i)
    }
    val sortedKey = m.keys.toArray.sortWith(_ > _)
    for (i <- sortedKey) {
      if (m(i) != 0) print(m(i) + " " + i + " ")
    }
    println()
  }

  print("Enter the no of testcases: ")
  var t=readInt()
  while(t!=0){
    polySum()
    t=t-1
  }
}
