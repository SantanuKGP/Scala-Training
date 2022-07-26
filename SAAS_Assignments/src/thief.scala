object thief extends App{
  def logic(x : String) : Boolean = (x == "01" || x == "10")
  def flip1(x: String):Int={
    x.grouped(2).toList.filter(logic(_)).length + x.slice(1,x.length).grouped(2).toList.filter(logic(_)).length + ('1'-x.head)
  }
  def flip2(str: String):Int={
    str.sliding(2).toArray.foldLeft('1'-str.head)((x,y)=> {if(y.head!=y(1)) x+1 else x})
  }
  def flip3(str: String):Int={
    str.sliding(2).filter(x => x(0) != x(1)).toList.length+ '1'-str.head
  }

  println(flip1("0011"))
  println(flip1("10011"))
  println(flip2("0011"))
  println(flip2("10011"))
  println(flip3("0011"))
  println(flip3("10011"))

  /*Another code problem 3*/
// We can not use Long because that operation will be invalid
//  It is O(n) in time and space complexity for both. There is better option.
  def reducer(n: String,multiplier: Int=1): String = {
    if(BigInt(n) <10) n
    else reducer((n * multiplier).split("").map(_.toInt).sum.toString)
  }
  println(reducer("844658468468468689796565", 769879 ))

}
