package concurrentprogramming

object ThreadCommunication extends App{
  class Container {
    private var value: Int = 0

    def isEmpty: Boolean = value == 0

    def set(newVal: Int) = value = newVal

    def get = {
      val result = value
      value = 0
      result
    }
  }
    def naiveProdCons():Unit ={
      val container = new Container
      val consumer = new Thread( ()=>{
        println("[consumer] waiting...")
        while(container.isEmpty) println("[consumer] actively waiting...")
        println("[consumer] I have consumed " + container.get )
      })

      val producer = new Thread ( ()=> {
        println("[producer] computing...")
        Thread.sleep(500)
        val value=1
        println("[producer] I have produced the value "+ value)
        container.set(value)
      })
      consumer.start()
      producer.start()
    }
  naiveProdCons()
}
