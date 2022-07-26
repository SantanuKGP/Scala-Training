object thief extends App{
  def logic(x : String) : Boolean = (x == "01" || x == "10")
  def flip1(x: String):Int={
    x.grouped(2).toList.filter(logic(_)).length + x.slice(1,x.length).grouped(2).toList.filter(logic(_)).length + ('1'-x.head)
  }
  def flip2(str: String):Int={
    str.sliding(2).toArray.foldLeft('1'-str.head)((x,y)=> {if(y.head!=y(1)) x+1 else x})
  }
  def flip3(str: String):Int={
    str.sliding(2).filter(x => x(0) != x(1)).toList.length
  }

  println(flip1("0011"))
  println(flip1("10011"))
  println(flip2("0011"))
  println(flip2("10011"))
  println(flip3("0011"))
  println(flip3("10011"))
}

