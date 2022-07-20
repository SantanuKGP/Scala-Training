import scala.io.StdIn.readLine
object problem4 extends App{
     def ackermann(x: BigInt,y:BigInt) : BigInt={
       if(x==0)  y+1
       else if (y==0) ackermann(x-1,1)
       else ackermann(x-1,ackermann(x,y-1))
     }
  var str=readLine()
  while(str!=""){
    val x= str.split(" ").map(_.toInt)
    println(ackermann(x.head,x(1)))
    str=readLine()
  }

}
