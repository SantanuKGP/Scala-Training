object CBNvsCBV extends App{

  def calledByValue(x : Long): Unit={
    println("By value: " + x)
    println("By value: " + x)
  }

  def calledByName(x : =>Long): Unit={
    println("By name : " + x)
    println("By name : " + x)
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

}
