package concurrentprogramming

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}
import scala.util.Success

object FuturePromises3 extends App{
  case class User(name : String)
  case class Transaction(sender: String, receiver : String, amount : Double, status : String)

  object BankingApp{
    val name = "Rock yhe JVM banking"

    def fetchUser(name:String): Future[User]=Future{
      Thread.sleep(500)
      User(name)
    }

    def createTransaction(user : User, merchantName: String, amount: Double):Future[Transaction]= Future{
      Thread.sleep((1000))
      Transaction(user.name,merchantName, amount, "SUCCESS")
    }

    def purchase(username: String, item : String, merchantName: String, cost: Double):String={
      //fetch the user, create transaction, wait for the transaction to finish
      val transactionStatusFuture=for{
        user <-fetchUser(username)
        transaction <- createTransaction(user, merchantName,cost)
      } yield transaction.status

      Await.result(transactionStatusFuture,2.seconds)
    }
  }
  println(BankingApp.purchase("San","i Phone 420","rock the jvm",3000))

  val promise = Promise[Int]()
  val future = promise.future

  future.onComplete{
    case Success(r) => println("[consumer] I've received " + r)
  }

  val producer = new Thread( ()=> {
    println("[producer] Producing")
    Thread.sleep(1000)
    promise.success(1)
    println("[producer] done")
  })
  producer.start()
  Thread.sleep(1000)
}
