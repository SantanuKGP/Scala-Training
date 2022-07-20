object thief extends App{
  def logic(x : String) : Boolean = (x == "01" || x == "10")
  def flip(x: String)={
    x.grouped(2).toList.filter(logic(_)).length + x.slice(1,x.length).grouped(2).toList.filter(logic(_)).length + ('1'-x.head)
  }
  println(flip("0011"))
  println(flip("10011"))
}
