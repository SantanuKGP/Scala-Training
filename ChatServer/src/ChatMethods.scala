object ChatMethods {
  val users: Array[String]=Array[String]()

  def broadCast(message : String="", name : String,id: Int,listener :Array[String]=users)={
    val text =id match{
      case 1 => s"$name has entered the chat"
      case 2 => s"$name has left the chat"
      case 3 => s"Message from $name : $message"
      case _ => ""
    }
    var i=0
    (1 to listener.length).foreach( _ => new Thread{
      // sends the text except it matches with the name
      // need concept how to connect with every user
      if(listener(i)!=name) // do something
        i +=1
    })
  }

  def sessionChat()={
    1
  }

  def shoot()={
    2
  }

  def tuner():Unit={

  }
}
