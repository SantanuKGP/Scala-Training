import scala.io.StdIn.readLine
import scala.util.Random

object ChatClient extends App{
  val username= readLine("Enter your username: ")

  val token= validation.Password()
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



  }
  else println("Sorry! Try again")
}
