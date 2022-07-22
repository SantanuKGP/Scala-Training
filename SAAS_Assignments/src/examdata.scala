import scala.io.Source.fromFile
object examdata extends App{
  val data= fromFile("exam_data.txt").mkString.split("\n").foreach{x=>val y=x.split(", ").toList.map(_.toString.toInt); if(y(0)*y(1)>y(2)) println("No")  else println("Yes")}
  for(line <- fromFile("exam_data_1.txt").getLines()){val y=line.split(", ").map(_.toInt); if(y(0)*y(1)>y(2)) println("No")  else println("Yes")}
  /**
   * Interestingly, we can not use Method 1 for "exam_data_1.txt" [underlying reason could be: Filetype::"Windows(CRLF)", not "Unix"]
   */
}
