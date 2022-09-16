package concurrentprogramming

object concurrency2 extends App{
  class BankAccount(@volatile var amount:Int){
    override def toString: String=""+amount
  }
  def buy(account :BankAccount, thing :String, price :Int):Unit={
    account.amount -=price
    println( s"I've bought $thing, my account is now $account")
  }
  for(_ <- 1 to 100){
    val account= new BankAccount(50000)
    val thread1= new Thread(()=> buy(account,"shoes",3000))
    val thread2= new Thread(()=> buy(account,"Iphone12",4000))

    thread1.start()
    thread2.start()
    thread1.join()
    thread2.join()
    println(account.amount)
    println("=="*25)
  }
}
