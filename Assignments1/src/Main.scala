import scala.io.Source._
import java.io._
import scala.annotation.tailrec

object Main extends App{
  def fileData( f : String => String =copy,inputFile : String, outputFile : String="Out.txt"): Unit ={

    val read=fromFile(inputFile)
    val it=read.getLines()

    val writer=new PrintWriter(new File(outputFile))
    while(it.hasNext){
      val line=it.next()
      if(line!="")
      writer.write(f(line)+"\n")
      else writer.write( "\n")
    }
    read.close()
    writer.close()
  }
  def copy(x :List[String]) :String={
    x.foldRight("")( _ + " " + _)
  }
  def copy( x: String) : String={
    x
  }
  def copy(x :Array[String]) :String={
    x.foldRight("")( _ + " " + _)
  }
  def coreDigit(str: String):String={
    val input=str.split(" ")
    val x=BigInt(input.head)
    val y=BigInt(input(1))
    val z=((x%9)*(y%9))%9
    if(z!=0) z.toString else "9"
  }
  println(coreDigit("9875 4"))
  def exam_data(ix : String) : String={
    val x= ix.filter(_ !=',').split(' ').map(_.toInt)
    if(x(0)*x(1)-x(2)> 0) "No" else "Yes"
  }

  var inDir="exam_data.txt"
  var outDir="exam_data_out.txt"
  fileData(exam_data,inDir,outDir)
  fileData(coreDigit,"CoreData.txt","CoreDataOut.txt")
/*
  class meow{
    var x=0
    def inc ={x= x+1; x}
  }*/

  def thief_data(ix: String): String={
    //var z=new meow();
    var z=ix.head
    val flip= ix.slice(1,ix.length).foldLeft('1'-ix.head){
      (x,y)=>  {if(y!=z) {z=y;x+1 } else {z=y;x}}
    }
    flip.toString

    /*
    @tailrec
    def flip(x: String,count: Int=0): Int={
      if(x.tail.isEmpty ) count
      else if(x.head==x.tail.head) flip(x.tail,count)
      else flip(x.tail,count+1)
    }
    val t='1'-ix.head
    (flip(ix)+t).toString*/
  }
  println(thief_data("10011"))
  println(thief_data("0011"))
  inDir="thief_data.txt"
  outDir="thief_data_out.txt"
  //fileData(thief_data,inDir,outDir)
}
