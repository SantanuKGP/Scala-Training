import java.net.InetAddress

object ChatServer extends App {
    val serverSocket= new java.net.ServerSocket(8080,1000,InetAddress.getByName("127.0.10.1"))
    while(serverSocket.accept().isConnected){

    }





}
