import scala.io.StdIn.{readInt, readLine}
import scala.collection.mutable.{Map => mMap}

object problem15 extends App{
  //  taking input from user
  val x=readInt()
  var m= mMap[String,String]().withDefaultValue("")
  Range(0,x).foreach{ _ =>
    val str=readLine()
    m(str.filter(_!='-'))=str
  }
  val pad= readInt()
  val str=readLine()

  //  Analysis
  val wordList=str.split(" ")
  var acc=0
  var needBreak=Array[Int]()
  var result=""
  var splitLen=0

  for(i <- 0 until wordList.length) {
    acc=acc+ wordList(i).length+1
    if(acc==pad){
      result= result+ wordList(i) + " \n"
      println("== :"+wordList(i))
      acc=0
    }
    else if(acc>pad){
      println("> :"+wordList(i))
      result= result+ moody(wordList(i),wordList(i).length-acc+pad)+" "
      acc=wordList(i).length-splitLen+1
    }
    else{
      result=result+ wordList(i) + " "
    }
  }
  println(result)


  def moody(x: String,constraint: Int):String={
    val strArr= m(x.filter(c => {c!=',' && c!='.'})).split("-")
    var len=0
    splitLen=0
    var res=""
    for(i <- 0 until strArr.length){
      len=len+strArr(i).length
      if(len<=constraint){
        res=res+strArr(i)
        splitLen=len
      }
    }
    res+"-\n"+x.slice(splitLen,x.length)
  }
}
