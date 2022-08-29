package AdvanceFunction

trait MySet [A] extends (A=> Boolean ){
  def apply(elem :A):Boolean = contains(elem)
  def contains(elem : A):Boolean
  def +(elem:A):MySet[A]
  def ++(anotherSet: MySet[A]) :MySet[A]

  def map[B](f:A => B): MySet[B]
  def flatMap[B](f:A => MySet[B]):MySet[B]
  def filter(predicate:A => Boolean):MySet[A]
  def foreach(f:A => Unit):Unit
}

class EmptySet [A] extends MySet[A]{
  def contains(elem : A):Boolean
  def +(elem:A):MySet[A]
  def ++(anotherSet: MySet[A]) :MySet[A]

  def map[B](f:A => B): MySet[B]
  def flatMap[B](f:A => MySet[B]):MySet[B]
  def filter(predicate:A => Boolean):MySet[A]
  def foreach(f:A => Unit):Unit
}
class EmptySet [A] extends MySet[A]