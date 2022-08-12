object patternLeetCode extends App{
  println(isMatch("aa","a"))
  println(isMatch("aa","a*"))
  println(isMatch("ab",".*"))
  def isMatch(s: String, p: String): Boolean = {
    p.r.matches(s)
  }
}
