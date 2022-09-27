import java.io.{DataInputStream, DataOutputStream, IOException}
import java.net.{ServerSocket, UnknownHostException}

class Server (private val port : Int){
  private var ip_users = 0
  private var m= Map[String,DataOutputStream]()

  try{
    val server= new ServerSocket(port)
    println("Server Started")
    println("Waiting for a client...")

    while(!server.isClosed && ip_users<1000){
      var temp_user =""
      val thread = new Thread( () =>{
        val socket = server.accept()
        val userPort = socket.getPort
        ip_users +=1
        val input= new DataInputStream(socket.getInputStream)
        val output = new DataOutputStream(socket.getOutputStream)
        val user = input.readUTF()
        println(s"$user joined with Port no : $userPort")
        temp_user = user
        try {
          val available_users = m.keys.toList.reduce(_ + " " + _)
          output.writeUTF("Available users : " + available_users)
        }
        catch{
          case _: Throwable => output.writeUTF("No user is available now")
        }
        m += (temp_user -> output)
        for(i <- m.keys) m(i).writeUTF(s"Server:::all:::$user joined in the chat")
        var line =""
        while(isClose(line)) {
          try {
            line = input.readUTF()
            val decoded = decoding(line)
            if(decoded(1).toLowerCase()!="all") m(decoded(1)).writeUTF(line)
            else{
              for(i <- m.keys) m(i).writeUTF(line)
            }
            line = decoded.last

          }
          catch {
            case _: Throwable => println(s"$user got disconnected !!"); line = "close"
          }
        }
        println(s"$user has left")
        m -= user
        for(i <- m.keys) m(i).writeUTF(s"Server:::all:::$user has left the chat")
        println("Total users now : " + m.keys.toList.length + ", IP Users Created: "+ ip_users)
        socket.close()
        input.close()
      })
      thread.start()
      Thread.sleep(5000)
    }

  }
  catch {
    case t: UnknownHostException => println(t)
    case i: IOException =>println(i)
  }
  private def decoding(str:String):List[String]={
    str.split(":::").toList
  }

  private def isClose(x: String): Boolean={
    x.toLowerCase() match{
      case "close" | "exit" => false
      case _ => true
    }
  }
}

object server extends App{
  new Server(8080)
}
