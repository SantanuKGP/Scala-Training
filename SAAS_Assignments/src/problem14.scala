import scala.io.StdIn.{readInt, readLine}
import scala.collection.mutable.{Map => mMap}
import scala.math.pow

object problem14 extends App{
  val wordNo=readInt()
  val wordList=readLine().split(" ").map(_.trim)
  val numbers=readInt()
  val str=readLine()

//  test
//  val wordList=Array("SATAN", "SEES", "OASIS", "STRAIGHT", "FORWARD", "SENSE", "FROG", "DREW", "SHIRTS")
//  val wordList=Array("SATAN", "SEES","FROG", "DREW", "SHIRTS")
//  val str="AAEEIODFGHNRRRSSSTTW"

  var dictionary=mMap[Char,Int]().withDefaultValue(0)
  for(i <- str) dictionary(i)=dictionary(i)+1


  def compare(myMap1: mMap[Char,Int],myMap2: mMap[Char,Int]=dictionary):Boolean={
    var condition=true
    myMap1.keys.foreach{x =>
      if(myMap1(x)>myMap2(x)) condition=false
    }
    condition
  }

  def maxWord2(x: Array[String]= wordList, n: Int,m :Int=wordNo):Int={
    val res0=n.toBinaryString
    val res1="0"*(m-res0.length) + res0
    var newMap=mMap[Char,Int]().withDefaultValue(0)
    for(i <- 0 until x.length; j<-x(i)){
      if(res1(i)=='1'){
        newMap(j)=newMap(j)+1
      }
    }
    if(compare(newMap)) res1.filter(_=='1').length else 0
  }
  var value=0
  var total=pow(2,wordNo).toInt
  for(i <- 0 until total){
    value= value max maxWord2(wordList,i)
  }
  println(value)

}
//  for (elem <- dictionary.keys) {println(elem+ ": "+dictionary(elem))}
//  println(maxWord(wordList,dictionary))
/*
  def maxWord(x: Array[String], myMap: mMap[Char,Int]):Int={
    if(x.length==0)  0
    else{
      val word=x.head
      val prevMap=myMap
      var condition=true
      for(i<- word){
        if(myMap(i)<=0) condition=false else myMap(i)=myMap(i)-1
      }
      val m1=maxWord(x.tail,myMap)+1
      val m2=maxWord(x.tail,prevMap)
      println("condition: " + condition + ", m1: "+ m1 + ", m2: "+ m2 + ",word:" + word)
      if(condition) m1 max m2 else m2
    }

  }*/