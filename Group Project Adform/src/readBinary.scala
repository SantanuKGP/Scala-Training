import java.io._
object readBinary extends App{
  var in = None: Option[FileInputStream]
  var out = None: Option[FileOutputStream]
  try {
    in = Some(new FileInputStream("Maxtemp_MaxT_2020.GRD"))
    var c = 0
    var s=""
    var t=0
    while ({c = in.get.read;c != -1}) {
      println(c)
    }

  } catch {
    case e: IOException => e.printStackTrace()
  } finally {
    println("\nentered finally ...")
    if (in.isDefined) in.get.close()
  }
}
/*
    var in = None: Option[FileInputStream]
    var out = None: Option[FileOutputStream]
    try {
        in = Some(new FileInputStream("/tmp/Test.class"))
        out = Some(new FileOutputStream("/tmp/Test.class.copy"))
        var c = 0
        while ({c = in.get.read; c != −1}) {
            out.get.write(c)
        }
    } catch {
        case e: IOException => e.printStackTrace
    } finally {
        println("entered finally ...")
        if (in.isDefined) in.get.close
        if (out.isDefined) out.get.close
    }
*/
