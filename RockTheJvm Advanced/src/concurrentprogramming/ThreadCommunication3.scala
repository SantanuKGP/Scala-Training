package concurrentprogramming

import scala.collection.mutable
import scala.util.Random

object ThreadCommunication3 extends App{
  def prodConsLargeBuffer():Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3
    val consumer = new Thread(( )=> {
      val random= new Random()

      while(true){
        buffer.synchronized{
          if(buffer.isEmpty){
            println("[consumer] buffer empty, waiting...")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println("[consumer] consumed " + x)
          buffer.notify()
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    val producer = new Thread(( )=> {
      val random= new Random()
      var i=0
      while(true){
        buffer.synchronized{
          if(buffer.size == capacity){
            println("[producer] buffer is full, waiting...")
            buffer.wait()
          }
          buffer.enqueue(i)
          println("[producer] producing " + i)
          buffer.notify()
          i +=1
        }


        Thread.sleep(random.nextInt(500))
      }
    })
    consumer.start()
    producer.start()
  }
  prodConsLargeBuffer()
}
