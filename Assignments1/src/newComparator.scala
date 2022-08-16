object newComparator {

  trait Comparator[T]{
    def compare(x:T,y:T):Int
  }
  implicit object ComparatorInt extends Comparator[Int]{
    override def compare(x:Int,y:Int):Int={
      if(x==y) 0
      else if(x<y) -1
      else 1
    }
  }
  implicit object ComparatorDouble extends Comparator[Double] {
    override def compare(x:Double,y:Double):Int={
      if(x==y) 0
      else if(x<y) -1
      else 1
    }
  }
  implicit object ComparatorChar extends Comparator[Char]{
    override def compare(x:Char,y:Char):Int={
      if(x==y) 0
      else if(x<y) -1
      else 1
    }
  }
  implicit object ComparatorString extends Comparator[String]{
    override def compare(x:String,y:String):Int={
      if(x==y) 0
      else if(x<y) -1
      else 1
    }
  }
  def compare[T](x:T,y:T)(implicit comparator:Comparator[T]):Int = {
    comparator.compare(x,y)
  }
}
