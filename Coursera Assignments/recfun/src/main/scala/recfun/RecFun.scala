package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = if(r==0 || c==0 || c==r) 1 else pascal(c,r-1)+pascal(c-1,r-1)

  /**
   * Exercise 2
   */
  def balance(chars : List[Char]): Boolean ={
    def cond(x: Char): Boolean= x match{
      case '(' | ')' | '{' | '}' | '[' | ']' => true;
      case _=> false;
    }
    def cond2(x:Char):Boolean=x match{
      case '('  | '{' | '['  => true;
      case _=> false;
    }
    val s=Map[Char,Char](')' -> '(', '}' -> '{', ']' -> '[')
    val char = chars.filter(x=>cond(x))
    val l=scala.collection.mutable.Stack[Char]()
    for(i<- char){
      if(l.isEmpty || cond2(i)) l.push(i)
      else if (l.last==s(i)) l.pop()
    }
    l.isEmpty
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int ={
    if(money==0)  1
    else if(coins.isEmpty)  0
    else if(money<0) 0
    else countChange(money-coins.head,coins)+countChange(money,coins.tail)
  }
}
