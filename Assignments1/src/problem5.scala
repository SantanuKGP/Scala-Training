import scala.annotation.tailrec

object problem5 extends App {
//  trait hello[A]
//  class hello [A]{
//    def len[A] (x : List[A])= x.foldLeft(0)((x,y)=> x+1)
//    def < [A]  (x : A) : Boolean
//    def comp[A](x: A, y: A)= if( x== y) 0 else if (x < y) -1 else 1
//    def sort[A]
//  }

/*  val a:newObject[Int]=NotEmpty(1,NotEmpty(2,NotEmpty(3,Empty())))
  println(a.tail.tail.tail==Empty())
  println(a)*/
}
/*trait newObject[A] {
  def head:A
  def tail: newObject[A]
}
case class Empty[A]() extends newObject[A]{
  override def head: A = throw new NoSuchElementException()
  override def tail: newObject[A] = throw new NoSuchElementException()
}

case class NotEmpty[A](h: A, t: newObject[A]) extends newObject[A]{
  override def head: A = h
  override def tail: newObject[A] = t
}


case class Op[A](x: newObject[A]){
  def len:Int = calculate(x)
  @tailrec
  final def calculate(x:newObject[A],acc:Int=0):Int={
    if(x==Empty())  acc
    else calculate(x.tail,acc+1)
  }
  def compare(x:newObject[A],y:newObject[A]):Boolean=true
}*/
//object newObject[A]{
//  @tailrec
//   def len(x:newObject[A],acc:Int=0):Int={
//    if(x==Empty())  acc
//    else len(x.tail,acc+1)
//  }
//}


//case class newObject[A]() extends newObject[A]{
//  override def head: A = throw new NoSuchElementException()
//  override def tail: newObject[A] = throw new NoSuchElementException()
//}
