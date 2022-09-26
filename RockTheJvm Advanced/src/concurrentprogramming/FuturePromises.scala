package concurrentprogramming

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object FuturePromises extends App{
  def calc :Int={
    Thread.sleep(2000)
    2
  }
  val aFuture= Future{
    calc
  }
 println(aFuture.value)
  aFuture.onComplete({
    case Success(calc) => println(s"Calculation : $calc")
    case Failure(exception) => println(s"I haave failed with $exception")
  })
  Thread.sleep(2000)
}
