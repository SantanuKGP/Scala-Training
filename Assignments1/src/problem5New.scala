import scala.annotation.tailrec
import newComparator.Comparator

object problem5New extends App{
    val test1: Unit = {
      val x = Array(1, 5, 8, 3, 9, 2, 6)
      println(size(x))
      sort(x)
      println(x.toList)
      val z=binarySearch(8,x).get
      println(s"8 is found in at index $z : ${x(z)}")
      println(binarySearch(7,x))
    }

    val test2: Unit = {
      val x = Array(1, 5, 8.2, 4.3, 9.2, 2, 6.8,9.03)
      println(size(x))
      sort(x)
      println(x.toList)
      val z=binarySearch(8.2,x).get
      println(s"8 is found in at index $z : ${x(z)}")
      val z1=binarySearch(6.8,x).get
      println(s"6.8 is found in at index $z1 : ${x(z1)}")
    }

    val test3: Unit = {
      val  x= Array('a', 'e', 'h', 'c', 'i', 'b', 'f')
      println(size(x))
      sort(x)
      println(x.toList)
      val z=binarySearch('i',x).get
      println(s"'i' is found in at index $z : ${x(z)}")
      println(binarySearch('d',x))
    }

    @tailrec
    def size[T](x:Array[T],acc:Int=0):Int={
      if(x.length==0) acc else size(x.tail,acc+1)
    }

    def sort[T](x:Array[T])(implicit comparator:Comparator[T]):Array[T]={
      val len=size(x)
      for(i <-0 until len; j <- (i+1) until len if   comparator.compare(x(i),x(j))>0){
        val tmp=x(i)
        x(i)=x(j)
        x(j)=tmp
      }
      x
    }

    def binarySearch[T](elem:T,x:Array[T])(implicit cmp:newComparator.Comparator[T]):Option[Int]={
      val len=size(x)
      @tailrec
      def search(start:Int=0,end:Int=len-1):Option[Int]={
        if(start>end)  return Option.empty
        val mid=(start+end)/2
        cmp.compare(elem,x(mid)) match {
          case 0 =>  Option[Int](mid)
          case 1 => search(mid + 1, end)
          case -1 => search(start, mid - 1)
        }
      }
      search()
    }

}


