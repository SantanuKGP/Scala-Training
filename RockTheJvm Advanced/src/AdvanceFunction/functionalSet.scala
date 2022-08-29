package AdvanceFunction

object functionalSet extends App{
  println(findDuplicate(Array(1,2,2,4,5,6)))
  def findDuplicate(nums: Array[Int]): Int = {
    nums.diff(nums.distinct).head
  }
  val set =Set(1,2,3)
  println(set(2))
  println(set(21))
}
