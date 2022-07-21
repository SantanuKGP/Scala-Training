import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}
object problem5 extends App{
  new lifeCycle
}
 class lifeCycle{
   private val str= readLine().split(" ").map(_.toInt)
   private val row=str.head
   private val col= str(1)

   private var x= Array[Array[String]]()
   // filter is used if mistakenly user gives space while inputting char
   while(str.head!=0){x= x :+ readLine().split("").filter(_!=" ") ; str(0)=str(0)-1}
   private val n=readInt()
   println(cycle(n,x))

   def friends(x:Array[Array[String]],r :Int, c: Int):Int={
     var neighbour=0
     if(r>0 && x(r-1)(c)=="@") neighbour=neighbour+1
     if(r<row-1 && x(r+1)(c)=="@") neighbour=neighbour+1
     if(c>0 && x(r)(c-1)=="@") neighbour=neighbour+1
     if(c<col-1 && x(r)(c+1)=="@") neighbour=neighbour+1
     if(r<row-1 && c<col-1  && x(r+1)(c+1)=="@") neighbour=neighbour+1
     if(r<row-1 && c>0 && x(r+1)(c-1)=="@") neighbour=neighbour+1
     if(r>0 && c>0 && x(r-1)(c-1)=="@") neighbour=neighbour+1
     if(r>0 && c<col-1 && x(r-1)(c+1)=="@") neighbour=neighbour+1
     neighbour
   }
   def nextGen(arr : Array[Array[String]]): Array[Array[String]]={
     val next= Array.ofDim[String](row,col)
     for(i<- 0 until row){
       for(j<- 0 until col){
         val f= friends(arr,i,j)
         if(f==3) next(i)(j)="@"
         else if (f==2) next(i)(j)=arr(i)(j)
         else next(i)(j)="#"
       }
     }
     next
   }
   // life cycle
   @tailrec
   private def cycle(n : Int, acc: Array[Array[String]]): Int ={
     if(n==0) acc.flatten.count(_ == "@")
     else cycle(n-1 ,nextGen (acc))
   }


   // printing the status
   def printColony():Unit={
     for(i<-0 until row) {
       for(j<-0 until col) {
         print(x(i)(j)+" ")
       }
       println("")
     }
   }
 }