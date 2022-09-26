package concurrentprogramming

import scala.collection.mutable
import scala.util.Random

object ThreadCommunication4 extends App{
  class Consumer(id: Int, buffer : mutable.Queue[Int]) extends Thread{
    override def run():Unit = {
      val random= new Random()

      while(true){
        buffer.synchronized{
          while(buffer.isEmpty){
            println(s"[consumer $id] buffer empty, waiting...")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println(s"[consumer $id] consumed " + x)
          buffer.notify()
        }

        Thread.sleep(random.nextInt(500))
      }
    }
  }

  class Producer(id: Int, buffer : mutable.Queue[Int],capacity : Int) extends Thread{
    @Override
    override def run():Unit={
      val random= new Random()
      var i=0
      while(true){
        buffer.synchronized{
          while(buffer.size == capacity){
            println(s"[producer $id] buffer is full, waiting...")
            buffer.wait()
          }
          buffer.enqueue(i)
          println(s"[producer $id] producing " + i)
          buffer.notify()
          i +=1
        }


        Thread.sleep(random.nextInt(500))
      }
    }
  }

  def multiProdCons(nConsumers: Int, nProducers:Int): Unit={
    val buffer : mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3
    (1 to nConsumers).foreach( i=> new Consumer(i, buffer).start())
    (1 to nProducers).foreach( i=> new Producer(i, buffer,capacity).start())
  }
  multiProdCons(3,3)
}
