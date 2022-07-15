object OOBasics extends App{
  val person= new Person("John",26)
  println(person.age)
//  println(person.name)
  println(person.x)
  person.greet("Daniel")
  person.greet
  println("Person-2")
  val person2=new Person("Hope")
  person2.greet
  println("Person-3")
  val person3 = new Person()
  person3.greet

  val author= new Writer("Charles","Dickens", 1812)
  val imposter=new Writer("Charles","Dickens", 1812)
  val novel = new Novel(" Great Expectations",1861,author)
  println(novel.authorAge)
  println(novel.isWrittenBy(imposter))
  val counter =new Counter
  counter.inc.print
  counter.inc(3).print
}
// name , age are class parameters, to convert int class member or fields use val, var

// overloading
 class Person(name: String,var age : Int) // constructor
{ // body
  val x=2
  println(1+2+3) //it is evaluating first
 // method
  def greet(name: String): Unit=println(s"${this.name} says: Hi, $name")
//  overloading
  def greet: Unit= println(s"Hi,I am $name. I am $age.")
//  multiple constructor
  def this(name: String)= this(name,0)
  def this() =this("John Doe")
}

class Writer( firstname: String, surname: String,val year: Int){
  def fullName :String = firstname + " " + surname
}

class Novel(name : String, year : Int, author : Writer){
  def authorAge= year - author.year
  def isWrittenBy(author : Writer)= author==this.author
  def copy(newYear : Int): Novel= new Novel(name, newYear, author)
}

class Counter(val count :Int= 0){
  def inc= new Counter(count+1) // immutability
  def dec= new Counter(count-1)
  /*
  def inc(n: Int)= new Counter(count+n)
  def dec(n: Int)= new Counter(count-n)
  */
   def inc(n:Int): Counter= {
     if(n<=0) this
     else inc.inc(n-1)
   }
  def dec(n:Int): Counter={
    if(n<=0) this
    else dec.dec(n-1)
  }
  def print=println(count)
}