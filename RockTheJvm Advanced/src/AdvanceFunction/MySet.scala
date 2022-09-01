package AdvanceFunction

import scala.annotation.tailrec

trait MySet [A] extends (A=> Boolean ){
  def apply(elem :A):Boolean = contains(elem)
  def contains(elem : A):Boolean
  def +(elem:A):MySet[A]
  def ++(anotherSet: MySet[A]) :MySet[A]

  def map[B](f:A => B): MySet[B]
  def flatMap[B](f:A => MySet[B]):MySet[B]
  def filter(predicate:A => Boolean):MySet[A]
  def foreach(f:A => Unit):Unit

  def -(elem:A):MySet[A]
  def --(anotherSet: MySet[A]) :MySet[A] // difference
  def &(anotherSet: MySet[A]) :MySet[A] //intersection
  def unary_! :MySet[A]
}

class EmptySet [A] extends MySet[A]{
  def contains(elem : A):Boolean=false
  def +(elem:A):MySet[A]=new NonEmptySet[A](elem,this)
  def ++(anotherSet: MySet[A]) :MySet[A]=anotherSet

  def map[B](f:A => B): MySet[B]=new EmptySet[B]
  def flatMap[B](f:A => MySet[B]):MySet[B]=new EmptySet[B]
  def filter(predicate:A => Boolean):MySet[A]=this
  def foreach(f:A => Unit):Unit=()

  def -(elem:A):MySet[A]=this
  def --(anotherSet: MySet[A]) :MySet[A]=this // difference
  def &(anotherSet: MySet[A]) :MySet[A]= this
  def unary_! :MySet[A]= new PropertyBasedSet[A](_=>true)         // new AllInclusiveSet[A]
}
/*class AllInclusiveSet[A] extends MySet[A]{
  override def contains(elem: A): Boolean = true

  override def +(elem: A): MySet[A] = this

  override def ++(anotherSet: MySet[A]): MySet[A] = this

  override def map[B](f: A => B): MySet[B] = ???

  override def flatMap[B](f: A => MySet[B]): MySet[B] = ???

  override def filter(predicate: A => Boolean): MySet[A] = ???

  override def foreach(f: A => Unit): Unit = ???

  override def -(elem: A): MySet[A] = ???

  override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)

  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)

  override def unary_! : MySet[A] = new EmptySet[A]
}*/
// all elements of type A which satisfy a property
// {x in A | property(x)}

class PropertyBasedSet[A](property : A => Boolean) extends MySet[A]{
  def contains(elem : A):Boolean=property(elem)
  def +(elem:A):MySet[A] = new PropertyBasedSet[A](x=> property(x) || x==elem)
  def ++(anotherSet: MySet[A]) :MySet[A]= new PropertyBasedSet[A](x=> property(x) || anotherSet(x))

  def map[B](f:A => B): MySet[B]=fail
  def flatMap[B](f:A => MySet[B]):MySet[B]=fail
  def foreach(f:A => Unit):Unit=fail

  def filter(predicate:A => Boolean):MySet[A]=new PropertyBasedSet[A](x=> property(x) && predicate(x))
  def -(elem:A):MySet[A]=filter(x=> x!=elem)
  def --(anotherSet: MySet[A]) :MySet[A]= filter(!anotherSet) // difference
  def &(anotherSet: MySet[A]) :MySet[A]= filter(anotherSet) //intersection
  def unary_! :MySet[A]=new PropertyBasedSet[A](x=> !property(x))
  def fail= throw new IllegalArgumentException("Failure!")
}

class NonEmptySet [A](head:A,tail:MySet[A]) extends MySet[A]{
  def contains(elem : A):Boolean= elem==head || tail.contains(elem)
  def +(elem:A):MySet[A]=if(this contains elem) this else new NonEmptySet[A](elem,this)
  def ++(anotherSet: MySet[A]) :MySet[A]= tail ++ anotherSet + head

//  [1 2 3] ++ [4 5]= [4 5 1 2 3]

  def map[B](f:A => B): MySet[B]=(tail map f)+ f(head)
  def flatMap[B](f:A => MySet[B]):MySet[B]=(tail flatMap f) ++ f(head)
  def filter(predicate:A => Boolean):MySet[A]={
    val filteredTail =tail filter predicate
    if(predicate(head)) filteredTail + head
    else filteredTail
  }
  def foreach(f:A => Unit):Unit={
    f(head) ; tail foreach f
  }

  def -(elem:A):MySet[A]= if(head==elem) tail else tail-elem + head
  def --(anotherSet: MySet[A]) :MySet[A]=filter(!anotherSet) // difference
  def &(anotherSet: MySet[A]) :MySet[A] = filter(anotherSet)
  //filter(x=> anotherSet.contains(x))
  //filter(x=> anotherSet(x))
  def unary_! :MySet[A]=new PropertyBasedSet[A](x=> !this.contains(x))
}

object MySet{
  def apply[A](values: A*):MySet[A]={
    @tailrec
    def buildSet(valSeq:Seq[A], acc: MySet[A]): MySet[A] = if(valSeq.isEmpty) acc else buildSet(valSeq.tail,acc+valSeq.head)

    buildSet(values.toIndexedSeq, new EmptySet[A])
  }
}
object MySetPlayGround extends App{

}