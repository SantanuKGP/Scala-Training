import scala.io.Source._

object problem2 extends App{
  def numberWord(file: String):Unit={
    var acc=0
    val regex="[a-z_A-Z_0-9_-]+".r
    for(line <- fromFile(file).getLines()) acc+=regex.findAllMatchIn(line).toList.length
    println(s"Number of Words of $file is "+acc)
  }

  def numberWords(file: String):Unit={
    val regex="[a-z_A-Z_0-9_-]+".r
    val it=fromFile(file)
    try println(s"Number of Words of $file is "+regex.findAllMatchIn(it.mkString).toList.length) finally it.close()
  }
  numberWord("file1.txt")
  numberWord("file2.txt")
  numberWord("file3.txt")

  numberWords("file1.txt")
  numberWords("file2.txt")
  numberWords("file3.txt")
}
