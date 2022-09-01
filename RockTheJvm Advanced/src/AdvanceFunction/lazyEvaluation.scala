package AdvanceFunction

object lazyEvaluation extends App{

  def sideEffectCondition:Boolean={
    println("Boo")
    true
  }
  def simpleCondition: Boolean= false
  lazy val lazySideEffect=sideEffectCondition
  println(if(simpleCondition && lazySideEffect) "yes" else "no")

//  in conjunction with call by Name
  def byNameMethod(n: => Int):Int= n+n+n+1
  def retrieveMagicValue:Int={
    println("waiting")
    Thread.sleep(1000)
    42
  }
  println(byNameMethod(retrieveMagicValue))
//  Call by Need
  def byNeedMethod(n: => Int):Int={
    lazy val t=n
    t+t+t+1
  }
  println(byNeedMethod(retrieveMagicValue))
  abstract class MyStream[A]{
    def isEmpty :Boolean
    def head : A
    def tail: MyStream[A]

    def #::(elem: A):MyStream[A]
    def ++(anotherStream: MyStream[A]) : MyStream[A]

    def foreach(f: A=> Unit):Unit
    def map[B](f: A => B): MyStream[B]
    def filter(predicate: A => Boolean) : MyStream[A]

    def take(n:Int): MyStream[A]
    def takeAsList(n: Int): List[A]
  }

  object MyStream extends MyStream[Nothing]{
    def from[A](start: A)(generator: A => A): MyStream[A]= ???
    def isEmpty :Boolean = ???
    def head : Nothing = ???
    def tail: MyStream[Nothing] = ???

    def #::(elem: Nothing):MyStream[Nothing] = ???
    def ++(anotherStream: MyStream[Nothing]) : MyStream[Nothing] = ???

    def foreach(f: Nothing=> Unit):Unit = ???
    def map[B](f: Nothing => B): MyStream[B] = ???
    def filter(predicate: Nothing => Boolean) : MyStream[Nothing] = ???

    def take(n:Int): MyStream[Nothing] = ???
    def takeAsList(n: Int): List[Nothing] = ???
  }
}
