import java.io.{DataInputStream, DataOutputStream, IOException}
import java.net.{Socket, UnknownHostException}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.StdIn.readLine
import scala.util.{Failure, Random, Success}


class Client (user:String,address : String, port : Int){
  private var ss : Socket= _
  private var input : DataInputStream = _
  private var output :DataOutputStream = _
  try{
    ss= new Socket(address,port)
    println("Connected")
    input= new DataInputStream(ss.getInputStream)
    output= new DataOutputStream(ss.getOutputStream)
  }
  catch {
    case t: UnknownHostException => println(t)
    case i: IOException =>println(i)
  }

  output.writeUTF(user)
  println(input.readUTF())

  var line = ""
  private val messages= scala.collection.mutable.Queue[String]()
  private val outputFuture = Future{
    while(line!="close") {
      line = readLine("")
      output.writeUTF(encoding(line))
    }
  }

  private val inputFuture= Future{
    while(isClose(line)){
      messages.enqueue(input.readUTF())
      while(messages.nonEmpty){
        decoding(messages.front)
        messages.dequeue()
      }
    }
  }
  var flag1 = false
  var flag2 = false
  inputFuture.onComplete({
    case Success(_) => println("InputFuture has been completed!")
    case Failure(exception) => println(s"Input side has got some issues:: $exception")
  })
  outputFuture.onComplete({
    case Success(_) => println("OutputFuture has been completed!")
    case Failure(exception) => println(s"Output side has got some issues:: $exception")
  })

  private val finalFuture= Future{
    if(flag1 && flag2)
    {
      input.close()
          println("input closed")
      output.close()
          println("output closed")
      ss.close()
          println("socket closed")
    }
    "success"
  }

  finalFuture.onComplete({
    case Success(value) => println(s"$user has ${value+"fully"} log out!"); println("Chat Again!!")
    case Failure(exception) => println(s"Output side has got some issues:: $exception")
  })
  /*  readObject.synchronized{
  //    println("I am waiting")
      readObject.wait()
  //    println("I am here")
    }*/

/*  try{
    input.close()
    //    println("input closed")
    output.close()
    //    println("output closed")
    ss.close()
    //    println("socket closed")
  }
  catch{
    case _: Throwable=> println("Closing Error")
  }
  println("Chat Again!!")*/


  private def isClose(x: String): Boolean={
    val y=x.toLowerCase()
    !(y== "close" || y=="exit" ||  y=="over")
  }

  def encoding(str: String):String ={
    val regex= "@\\w+".r
    try {
      user+":::"+regex.findFirstIn(str).get.filter(_!='@')+":::"+regex.split(str).filter(_!="").reduce(_ +":::" + _)
    }
    catch{
      case _ : Throwable => user+ ":::"+ "all"+":::"+ str
    }
  }

  def decoding(str:String):Unit={
    val Line = str.split(":::").toList
    if (Line(1) == user) println(s"[${Line.head}] : ${Line.last}")
    else if (Line(1).toLowerCase == "all" && Line.head!=user) println(s"[${Line.head}] : ${Line.last}")
  }

}

object Client extends App{

  val username= readLine("Enter your username: ")

  val IP= "localhost"
  new Client(username,IP, 8080)
/*
  val token= Password()
  println("Print the passcode : " + token)
  val time0= System.currentTimeMillis()
  val passCode=readLine("Enter the passcode : ")
  val timeTaken= System.currentTimeMillis() - time0

  val timeLimit= new Random().between(2000*token.length,3000*token.length)

  //  println(timeTaken + " " + timeLimit)
  if(passCode==token && timeLimit>=timeTaken ){
    println("="*100)
    println(" "*47 + "Chat Started")
    println("="*100)

    // code here
    val IP= "localhost"
    new Client(username,IP, 8080)

  }
  else println("Sorry! Try again")

  println("="*100)
  println(" "*47 + "Chat Ended")
  println("="*100)*/

  private def Password():String={
    val charSet=('a' to 'z') ++ ('A' to 'Z') ++ "@#$!-"
    val random= new Random()
    val size=random.between(8,11)
    var str=""
    while(str.length<size){
      val index= random.nextInt(charSet.length)
      if(!str.contains(charSet(index))) str += charSet(index)
    }
    str
  }
}