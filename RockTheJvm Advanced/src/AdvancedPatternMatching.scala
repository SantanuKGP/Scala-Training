object AdvancedPatternMatching extends App{
  val x=List(1) // try with other data types Array
  x match{
    case head :: Nil => println(s"the only element is $head.")
    case _ =>
  }
  class Person(val name : String,val age: Int)
//  apply vs unapply
  object PersonPattern {
    def unapply(person: Person):Option[(String,Int)]=if(person.age >18) Some((person.name,person.age)) else None
    def unapply(age:Int):Option[String]=Some(if(age<18)"minor" else "major")
  }//understand the importance this thing
  val bob=new Person("Bob",25)
  val greeting1=bob match{
    case PersonPattern(n,a) => s"Hi, my name is $n and I am $a years old"
  }
  println(greeting1)
  val legalStatus= bob.age match{
    case PersonPattern(status) => s"My legal status is $status"
  }

  val john=new Person("John",14)
  val greeting2=john match{
    case PersonPattern(n,a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know you."
  }
  println(greeting2)


}
