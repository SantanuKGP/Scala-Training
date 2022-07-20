import scala.collection.mutable.Queue
object segmentTree extends App{
  /*
  def segT(x : List[Int])={
    var q = Queue[List[Int]]()
    val s= Queue[Int]()
    q.enqueue(List(0,x.length-1))
    while(q.nonEmpty){
      if(q.head(0)!=q.head(1)){
        val mid=(q.head(0)+q.head(1))/2
        s.enqueue(x.slice(q.head(0),q.head(1)+1).sum)
//        println(q.head(0)+" " +q.head(1))
        q.enqueue(List(q.head(0),mid))
        q.enqueue(List(mid+1,q.head(1)))
      }
      else s.enqueue(x(q.head(0)))
      q.dequeue()
    }
    for(i <- 0 until s.length){
      print(s(i)+" ")
    }
  }
  segT(List(1,3,5,7,9,11))*/
}

