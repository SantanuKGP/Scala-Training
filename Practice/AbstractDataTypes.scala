object AbstractDataTypes extends App{
  abstract class Animal {
    val creatureType : String= "wild"
    def eat: Unit
  }
  class Dog extends Animal {
    override val creatureType : String = "Canine"
    def eat : Unit = println("crunch crunch")
  }
//  traits
  trait Carnivore {
  def eat(animal : Animal) : Unit
  val preferredMeal : String = "meat"
}
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType : String = "croc"
    def eat : Unit = println("nomnomnom")
    def eat (animal : Animal) : Unit = println(s"I am a croc and I'm eating ${animal.creatureType}")
  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
  croc.eat
  println(croc.preferredMeal)
//  1-trait doesn't have constructor  parameters
//  At a time we can extend only one class but inherit multiple traits
//  traits = behavior, abstract class ="thing"

}
