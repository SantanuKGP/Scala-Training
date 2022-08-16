// https://leetcode.com/problems/regular-expression-matching/

object patternLeetCode extends App{
  println(isMatch("aa","a")) //false
  println(isMatch("aa","a*")) //true
  println(isMatch("ab",".*")) //true
  println(isMatch("ab",".?")) //false
  println(isMatch("ab",".?b")) //true
  println(isMatch("ab",".?a")) //false
  println(isMatch("aab","c*a*b")) //false

  def isMatch(s: String, p: String): Boolean = {
    p.r.matches(s)
  }
}
