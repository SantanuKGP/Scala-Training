import scala.annotation.tailrec
import newComparator.Comparator
object problem5CaseClass extends App{
  val test1: Unit = {
    val p = newObject(Array(1, 5, 8, 3, 9, 2, 6))
    println(p.size())
    p.sort()
    println(p.x.toList)
    val z=p.binarySearch(8).get
    println(s"8 is found in at index $z : ${p.x(z)}")
    println(p.binarySearch(7))
  }
  val test2: Unit = {
    val p = newObject(Array(1, 5, 8, 4.3, 9.2, 2, 6.8,9.03))
    println(p.size())
    p.sort()
    println(p.x.toList)
    val z=p.binarySearch(8).get
    println(s"8 is found in at index $z : ${p.x(z)}")
    println(p.binarySearch(7))
  }
  val test3: Unit = {
    val p = newObject(Array('a', 'e', 'h', 'c', 'i', 'b', 'f'))
    println(p.size())
    p.sort()
    println(p.x.toList)
    val z=p.binarySearch('i').get
    println(s"'i' is found in at index $z : ${p.x(z)}")
    println(p.binarySearch('d'))
  }
}
case class newObject[T](x:Array[T]){
  def size(a:Array[T]=x,acc:Int=0):Int={
    if(a.length==0) acc else size(a.tail,acc+1)
  }
  private val len=size()

  def sort()(implicit cmp :Comparator[T]):Array[T]={
    for(i <-0 until len; j <- (i+1) until len if   cmp.compare(x(i),x(j))>0){
      val tmp=x(i)
      x(i)=x(j)
      x(j)=tmp
    }
    x
  }

  def binarySearch(elem:T)(implicit cmp:Comparator[T]):Option[Int]={
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
