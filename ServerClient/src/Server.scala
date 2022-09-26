import java.io.{BufferedInputStream, DataInputStream, DataOutputStream, IOException}
import java.net.{ServerSocket, Socket, UnknownHostException}
import scala.annotation.tailrec
import scala.util.Random

class Server (port : Int){
//  private var server : ServerSocket = _
//  private var socket : Socket = _
//  private var input : DataInputStream = _
//  private var output :DataOutputStream = _


}

object Server extends App{
//  val server= new Server(5000)
  private val port = 8080
  private var ip_users = 0

  // q memory format : [sender, receiver, message ]
  private val q = scala.collection.mutable.Queue[String]()
  private var m= Map[String,DataOutputStream]()

  try{
    val server= new ServerSocket(port)
    println("Server Started")
    println("Waiting for a client...")

    /* added for new listeners */

    while(!server.isClosed && ip_users<1000){
      var temp_user =""
      val thread = new Thread( () =>{
        val socket = server.accept()

        println(socket)
        val userPort = socket.getPort
        println(userPort)

        ip_users +=1
        val input= new DataInputStream(socket.getInputStream)
        val output = new DataOutputStream(socket.getOutputStream)

        val user = input.readUTF()


  //        ip_users +=( ip-> user)

        println(s"$user joined with Port no : $userPort")
        temp_user = user+userPort
        m += (temp_user -> output)
        var line =""
        while(isClose(line)) {
          try {
            line = input.readUTF()
            val decoded = decoding(line)
            if(decoded(1).toLowerCase()!="all") m(decoded(1)).writeUTF(line)
            else{
              for(i <- m.keys) m(i).writeUTF(line)
            }
            println(line)
            line = decoded.last
          }
          catch {
            case _: Throwable => println(s"$user got disconnected !!"); line = "close"
          }
        }

        println(s"$user has left")
        m -=temp_user
        socket.close()
        input.close()
    })
      thread.start()
      Thread.sleep(5000)
//      if (ip_users==0) server.close()
//      ip_users -=1
    }

  }
  catch {
    case t: UnknownHostException => println(t)
    case i: IOException =>println(i)
  }
  def decoding(str:String):List[String]={
    str.split(":::").toList
  }
   def peer2peer(user1: String, user2 :String) : Unit ={
     // read from

   }

/*  final def isExist(k : String):Boolean={
    try{
      ip_users(k)
      true
    }
    catch{
      case _ :Throwable => false
    }
  }

  @tailrec
  final def ipAddress():String= {
    val x= new Array[String](4).map(_ =>new Random().between(9,255).toString).reduce(_ + "." + _)
    if(isExist(x)) ipAddress() else x
  }

  // entry of user-ip in database
  final def addUser(IP:String,user :String):Unit={
    ip_users += (IP -> user)
  }


  // removeUser remove user and Ip from Database
  final def removeUser(IP:String):Unit={
    ip_users -= IP
  }
*/

  /*
  * isClose to check whether user want to end the chat or not
  * */
  private def isClose(x: String): Boolean={
    x.toLowerCase() match{
      case "close" | "exit" => false
      case _ => true
    }
  }
}
