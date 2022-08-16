package com.santanu
import scala.annotation.tailrec

object problem5new extends App {

  val test1: Unit = {
    val x = Comparator[Int]()
    val y = Array(1, 5, 8, 3, 9, 2, 6)
    println(x.len(y))
    x.sort(y)
    println(y.toList)
    val z=x.binarySearch(8, y).get
    println(s"8 is found in at index $z : ${y(z)}")
    println(x.binarySearch(7, y))
  }

  val test2: Unit = {
    val x = Comparator[Double]()
    val y = Array(1, 5, 8, 4.3, 9.2, 2, 6.5,9.03)
    println(x.len(y))
    x.sort(y)
    println(y.toList)
    val z=x.binarySearch(8, y).get
    println(s"8 is found in at index $z : ${y(z)}")
    val z1=x.binarySearch(6.5, y).get
    println(s"6.5 is found in at index $z : ${y(z1)}")
  }

  val test3: Unit = {
    val x = Comparator[Char]()
    val y = Array('a', 'e', 'h', 'c', 'i', 'b', 'f')
    println(x.len(y))
    x.sort(y)
    println(y.toList)
    val z=x.binarySearch('i', y).get
    println(s"'i' is found in at index $z : ${y(z)}")
    println(x.binarySearch('d', y))
  }

}
case class Comparator[T](){
  @tailrec
  final def len(x: Array[T], acc:Int=0):Int={
    if(x.isEmpty)  acc
    else len(x.tail,acc+1)
  }

//  def compare(o1: T, o2: T)(implicit ord: Ordering[T]): Boolean = ord.gt(o1, o2)

  protected def compare[T <% Ordered[T]] (x:T,y:T): Int ={
    if(x==y) 0 else if(x<y) -1 else 1
  }
  def sort(x:Array[T])(implicit ord: Ordering[T]):Array[T]={
    for(i <-0 until len(x); j <-i+1 until len(x) if compare(x(i),x(j))>0){
      val tmp=x(i)
      x(i)=x(j)
      x(j)=tmp
    }
    x
  }
  def binarySearch(elem:T,x:Array[T])(implicit ord: Ordering[T]):Option[Int]={
    @tailrec
    def search(start:Int=0,end:Int=len(x)-1):Option[Int]={
      if(start>end)  return Option.empty
      val mid=(start+end)/2
      compare(elem,x(mid)) match {
        case 0 =>  Option[Int](mid)
        case 1 => search(mid + 1, end)
        case -1 => search(start, mid - 1)
      }
    }
    search()
  }
}

/*@tailrec
def len[T](x: List[T], acc:Int=0):Int={
  if(x.isEmpty)  acc
  else len(x.tail,acc+1)
}*/
/*
  val test4: Unit = {
    val x = Comparator[Char]()
    val y = Array("apple", "ahead", "host", "cost","costly", "bride", "bear")
    println(x.len(y))
    x.sort(y)
    println(y.toList)
    println(x.binarySearch('i', y))
    println(x.binarySearch('d', y))
  }
*/