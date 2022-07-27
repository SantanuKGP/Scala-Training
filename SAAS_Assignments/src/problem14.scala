import scala.io.StdIn.{readInt, readLine}
import scala.collection.mutable.{Map => mMap}
object problem14 extends App{
//  taking input from user
  val x=readInt()
  var m= mMap[String,String]()
  Range(0,x).foreach{ _ =>
    val str=readLine()
    m(str.filter(_!='-'))=str
  }
  val pad= readInt()
  val str=readLine()


//  Analysis
  val wordList=str.split(" ")
  var acc=0
  var constraint=pad
  var needBreak=Array[Int]()
  var result=""
  var splitLen=0

  for(i <- 0 until wordList.length) {
    acc=acc+ wordList(i).length+1
    if(acc==constraint){
      result= result+ wordList(i) + " \n"
      acc=0
    }
    else if(acc>constraint){
      result= result+ moody(wordList(i),wordList(i).length-acc+constraint)+" "
      acc=wordList(i).length-splitLen+1
    }
    else{
      result=result+ wordList(i) + " "
    }
  }
  println(result)


  def moody(x: String,constraint: Int):String={
    val strArr= m(x.filter(c => {c!=',' && c!='.'})).split("-")
    val strArr= m(x.init).split("-")
    var len=0
    splitLen=0
    var res1=""
    var res2="-\n"
//    println("constraint: "+constraint)
    for(i <- 0 until strArr.length){
//      println("len: "+len)
      len=len+strArr(i).length
      if(len<=constraint){
        res1=res1+strArr(i)
        splitLen=len
      }
    }
    res1+"-\n"+x.slice(splitLen,x.length)
  }

}
