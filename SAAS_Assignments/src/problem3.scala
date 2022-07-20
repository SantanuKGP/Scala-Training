import scala.io.StdIn.readLine

object problem3 extends App{
 var str= readLine()
  while(str!=""){
    var x= str.split('.').map(_.toInt)
    while(x.head%10 ==0) x(0)=x(0)/10
    if(x(1)==0) println( "#"*5 + "." +x.head)
    else println(x(1)+ "#"*(5-x(1).toString.length) + "." +x.head)
    str=readLine()
  }
}
