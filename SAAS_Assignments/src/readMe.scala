import scala.io.Source._
object readMe extends App{
  for(line <- fromFile(".\\src\\readMe.scala").getLines()) println(line)
}
