import Client.username

import java.io.{DataInputStream, DataOutputStream, IOException}
import java.net.{Socket, UnknownHostException}
import scala.collection.mutable.Queue
import scala.io.StdIn.readLine
//import Server.server._
import scala.util.Random

class Client (user:String,address : String, port : Int){
  private var ss : Socket = _
  private var input : DataInputStream = _
  private var output :DataOutputStream = _
  try{
    ss= new Socket(address,port)
    println("Connected")

//    input= new DataInputStream(System.in)
    input= new DataInputStream(ss.getInputStream)
    output= new DataOutputStream(ss.getOutputStream)
  }
  catch {
    case t: UnknownHostException => println(t)
    case i: IOException =>println(i)
  }

  output.writeUTF(user)

  val readObject= new Object
  val writeObject= new Object
  var line = ""
  private val messages= scala.collection.mutable.Queue[String]()
  new Thread(() => {
    while(line!="close") {
      line = readLine("[You] : ")
      output.writeUTF(encoding(line))
      Thread.sleep(1000)
      while(messages.nonEmpty){
        decoding(messages.front)
        messages.dequeue()
      }
    }
    if(isClose(line)){
      readObject.notify()
    }
  }).start()
  Thread.sleep(1000)
//  while(line!="close"){

  new Thread(() => {
    while(line!="close"){
      messages.enqueue(input.readUTF())
//      println(input.readUTF())
  }}).start()
//  }
  readObject.synchronized{
    readObject.wait()
  }
  /*
  while(isClose(line)){
    try{
//      line=input.readUTF()
      new Thread( ()=>{
      line=readLine("[You] : ")
      output.writeUTF(encoding(line))
//      println(encoding(line))
        line.wait()
      })
      val serverLine = decoding(.readUTF())
      if (serverLine(1) == user) println(s"[${serverLine.head}] : ${serverLine.last}")
      else if (serverLine(1).toLowerCase == "all" && serverLine.head!=user) println(s"[${serverLine.head}] : ${serverLine.last}")
      else println("----")

    /*  new Thread( () => {
      line=readLine("[You] : ")
      output.writeUTF(encoding(line))

      }).start()
      // new DataInputStream(new BufferedInputStream(socket.getInputStream))
      new Thread( () => {
        val serverLine = decoding(new DataInputStream(ss.getInputStream).readUTF())
        if (serverLine(1) == user) println(s"[${serverLine.head}] : ${serverLine.last}")
        else if (serverLine(1).toLowerCase == "all") println(s"[${serverLine.head}] : ${serverLine.last}")
      }).start()*/
    }
    catch {
      case e : Throwable=> println("Server Error! " +e); line ="close"
    }
  }*/

//  catch {
//  case e : Throwable=> println("Server Error! " +e); line ="close"
//  }

  try{
    input.close()
    output.close()
    ss.close()
  }
  catch{
    case _: Throwable=> println("Closing Error")
  }

  private def isClose(x: String): Boolean={
    x.toLowerCase() match{
      case "close" | "exit" | "over"  => false
      case _ => true
    }
  }

  def encoding(str: String):String ={
    val regex= "@\\w+".r
    try {
      user+":::"+regex.findFirstIn(str).get.filter(_!='@')+":::"+regex.split(str).filter(_!="").reduce(_ +":::" + _)
    }
    catch{
      case _ : Throwable => println("Got error here!"); user+ ":::"+ "all"+":::"+ str
    }
  }

  def decoding(str:String):Unit={
    val Line = str.split(":::").toList
    if (Line(1) == user) println(s"[${Line.head}] : ${Line.last}")
    else if (Line(1).toLowerCase == "all" && Line.head!=user) println(s"[${Line.head}] : ${Line.last}")
    else println("----")
  }

}

object Client extends App{
  val username = readLine()
//  val clientIp= ip()
//  addUser(clientIp,username)
//  println(s"IP: $clientIp, username: $username")
  val IP= "localhost"
  val client = new Client(username,IP, 8080)

//  removeUser(clientIp)

//  @scala.annotation.tailrec
  def ip:String= {
    new Array[String](4).map(_ =>new Random().between(9,255).toString).reduce(_ + "." + _)
//    if(isExist(x)) ip() else x
  }
}
