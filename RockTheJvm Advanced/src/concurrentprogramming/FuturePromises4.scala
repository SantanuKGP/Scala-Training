package concurrentprogramming

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Random, Success, Try}

object FuturePromises4 extends App{
  def fulfillImmediately[T](value: T): Future[T] = Future(value)
  def inSequence[A,B](first : Future[A], second: Future[B]):Future[B]={
    first.flatMap(_ => second)
  }

  def first[A](fa : Future[A],fb : Future[A]):Future[A] = {
    val promise = Promise[A]

    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)

    promise.future
  }

  def last[A](fa : Future[A],fb : Future[A]):Future[A] = {
    val bothPromise = Promise[A]
    val lastPromise = Promise[A]

    val checkAndComplete = (result : Try[A]) =>
      if(!bothPromise.tryComplete(result)) lastPromise.complete(result)

    fa.onComplete(checkAndComplete)
    fb.onComplete(checkAndComplete)

    lastPromise.future
  }

  val fast = Future{
    Thread.sleep(100)
    45
  }
  val slow = Future{
    Thread.sleep(200)
    12
  }
  first(fast,slow).foreach(f=> println("First: " +f))
  last (fast,slow).foreach(f=> println("Last:  " +f))


  def retryUntil[A](action: ()=> Future[A], condition : A => Boolean): Future[A] ={
    action()
      .filter(condition)
    .recoverWith{
      case _ =>retryUntil(action, condition)
    }
  }

  val random= new Random()
  val action = () => Future{
    Thread.sleep(100)
    val nextValue = random.nextInt(100)
    println("generated "+ nextValue)
    nextValue
  }

  retryUntil(action,(x:Int)=> x< 50).foreach(result => println("settled at "+result))
  Thread.sleep(1000)
}
