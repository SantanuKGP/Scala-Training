object stringOps extends App{
  val str : String ="Hello, I am learning scala"
  println(str.charAt(2))
  println(str.charAt(2)==str(2))
  println(str(2))
  println()
  println(str.startsWith("Hello")) // checks initial substring
  println(str.startsWith("Hell")) // checks initial substring
  println(str.startsWith("Help")) // checks initial substring
  println(str.replace(" ","-"))
  println()
  println( "San," + str + '2')
  println('a'+"2"+'z')
  println(str.take(2))
  println(str.reverse)
//  S- Interpolator
  val name="San"
  val age= 23
  println(s"$name is $age years old")
//  F- Interpolator
  println(f"${name+"dy"} is ${age+1}%5.2f years old")
//  raw-interpolator
  println("This is a \n newline")
  println(raw"This is a \n newline")
}
