import java.io.IOException
import java.net.ServerSocket

class Server (serverSocket : ServerSocket){

  def startServer():Unit={
    try{
      while(!serverSocket.isClosed){
        val socket= serverSocket.accept()
        println(" has entered!")
        val clientHandler = new ClientHandler(socket)

        val thread = new Thread(()=>clientHandler)
        thread.start()
      }
    }
    catch{
      case _: Throwable => println("Has exited Chat")
    }
  }

  def closeServerSocket():Unit={
    try{
      if(serverSocket!=null){
        serverSocket.close()
      }
    } catch{
      case e: IOException => println(e)
    }
  }

//  val serverSocket= new ServerSocket(8080)

}

object Server{
  val serverSocket= new ServerSocket(8080)
  val server = new Server(serverSocket)
  server.startServer()
}
