package concurrentprogramming

object ThreadCommunication2 extends App{
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
      container.synchronized{
        container.wait()
      }

      println("[consumer] I have consumed " + container.get )
    })

    val producer = new Thread ( ()=> {
      println("[producer] computing...")
      Thread.sleep(500)
      val value=1
      container.synchronized{
        println("[producer] I have produced the value "+ value)
        container.set(value)
        container.notify()
      }
      
    })
    consumer.start()
    producer.start()
  }
  naiveProdCons()
}
