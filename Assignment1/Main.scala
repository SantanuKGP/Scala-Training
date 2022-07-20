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
      if(line!="") //line.filter(_ !=',').split(' ').map(_.toInt).foldLeft(0)( (x,y)=> x+1).toString
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
  def coreDigit(x: BigInt, y: BigInt):BigInt={
    val z=((x%9)*(y%9))%9
    if(z!=0) z else 9
  }
  println(coreDigit(9875,4))
/*
//  testing the validity of our new function
  val out_f=raw"C:\Users\s.kundu\OneDrive - Adform\Documents\Output\out.txt"
  val in_f=raw"C:\Users\s.kundu\OneDrive - Adform\Desktop\Editing\scala-2.13.8\bin\file.txt"
  fileData(sumify,in_f,out_f)

  def sumify(x: String) : String={
    val y=x.split(", ").map(_.toInt).sum
    y.toString
  }

 */
  def exam_data(ix : String) : String={
    val x= ix.filter(_ !=',').split(' ').map(_.toInt)
    if(x(0)*x(1)-x(2)> 0) "No" else "Yes"
  }

  var inDir="exam_data.txt"
  var outDir="exam_data_out.txt"
  fileData(exam_data,inDir,outDir)


  def thief_data(ix: String): String={
    val flip= ix.foldLeft('1'-ix.head)((x,y)=> if(x.head==x.tail.head) x else x+1)
    /*
    @tailrec
    def flip(x: String,count: Int=0): Int={
      if(x.tail.isEmpty ) count
      else if(x.head==x.tail.head) flip(x.tail,count)
      else flip(x.tail,count+1)
    }*/
    val t=('1'-ix.head)
    (flip(ix)+t).toString
  }
  println(thief_data("10011"))
  println(thief_data("0011"))
  inDir="thief_data.txt"
  outDir="thief_data_out.txt"
  fileData(thief_data,inDir,outDir)
}
