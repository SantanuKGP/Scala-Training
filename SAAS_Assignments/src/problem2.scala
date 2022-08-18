import scala.io.Source._

object problem2 extends App{
  def numberWord(file: String):Unit={
    var acc=0
    val regex="[a-z_A-Z0-9-]+".r
    for(line <- fromFile(file).getLines()) acc+=regex.findAllMatchIn(line).toList.length
    println(s"Number of Words of $file is "+acc)
  }

  def numberWords(file: String):Unit={
    val regex="[a-z_A-Z\\d-]+".r
    val it=fromFile(file)
    try println(s"Number of Words of $file is "+regex.findAllMatchIn(it.mkString).length) finally it.close()
  }
  def newCount(file: String):Unit={
    val it=fromFile(file)
    try println(s"Number of Words of $file is "+ it.mkString.split("\\s+").length) finally it.close()
  }
  numberWord("file1.txt")
  numberWord("file2.txt")
  numberWord("file3.txt")

  numberWords("file1.txt")
  numberWords("file2.txt")
  numberWords("file3.txt")

  newCount("file1.txt")
  newCount("file2.txt")
  newCount("file3.txt")
}
