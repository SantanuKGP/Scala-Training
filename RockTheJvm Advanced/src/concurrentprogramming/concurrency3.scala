package concurrentprogramming

import concurrentprogramming.concurrency2.BankAccount

object concurrency3 extends App{
  def buySafe(account : BankAccount, thing :String, price :Int):Unit={
    account.synchronized({account.amount -=price
    println( s"I've bought $thing, my account is now $account")})
  }

  for(_ <- 1 to 100){
    val account= new BankAccount(50000)
    val thread1= new Thread(()=> buySafe(account,"shoes",3000))
    val thread2= new Thread(()=> buySafe(account,"Iphone12",4000))

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()
    println( )
//    println("=="*25)
  }
}
