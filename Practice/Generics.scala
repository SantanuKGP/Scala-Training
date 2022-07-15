object Generics extends App{
  class MyList[A] {

  }
  class MyMap[ Key, Value]
  val listofIntegers = new MyList[Int]
  val listofStrings= new MyList [String]

  // generic methods
  object MyList{
    def empty[A] :MyList[A]= ???
  }
  val emptyListofIntegers = MyList.empty[Int]


  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class CovariantList[+A]
  val
}
