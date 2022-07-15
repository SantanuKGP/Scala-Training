object MethodNotations extends App{
  class Person(val name : String, favoriteMovie: String,val age : Int = 0){
    def likes(movie: String): Boolean= movie== favoriteMovie
    def + (person : Person): String=s"${this.name} is hanging out with ${person.name}"
    def + (nickname: String): Person= new Person(s"$name ($nickname)", favoriteMovie)

    def unary_! : String= s"${name}, what is this?"
    def unary_+ : Person = new Person(name, favoriteMovie,age+1)
    // apply
    def apply(): String= s"Hi, my name is ${name} and I like ${favoriteMovie}"
    def apply(n : Int) : String = s"$name watched $favoriteMovie $n times"
    def learns(thing : String)= s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary= new Person("Marry","Inception")
  println(mary.likes("Inception"))
  println(mary likes("Inception"))
  val tom= new Person("Tom","Fight Club")
  println(mary+tom)
  println(mary.+(tom))
  println(1.+(2), 1+2) // All operations are methods
  // prefix notation
  val x = -1
  val y = 1.unary_- // unary op are valid with - + ~ !
  println(x==y) // above two lines are equivalent
  println(!mary)
  // postfix notation
  //apply
  println(mary.apply())
  println(mary())
  println((mary + "the RockStar").apply())
  println((+mary).age)
  println(mary.learnsScala)
  println(mary(2))
  /*
  overload + operator
   */
}
