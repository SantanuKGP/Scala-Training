import java.time.LocalDate
import java.time.Month._
import scala.io.Source.fromFile

object practise extends App {
  val person_fruit=fromFile("harvest.csv")
  val fruit_price=fromFile("prices.csv")
  var pf_data= Array[Array[String]]()
  var fp_data=Array[Array[String]]()
  for(line <- person_fruit.getLines()) pf_data= pf_data :+ line.split(",")
  for(line <- fruit_price.getLines()) fp_data= fp_data :+ line.split(",")

  pf_data=pf_data.tail
  fp_data=fp_data.tail


  val months=Array(JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER).map(_.toString)
  val gatherers=(pf_data map(_(0))).distinct
  val fruits= (pf_data map(_(2))).distinct

  val prices=fp_data.groupMap(x=> (x(0),LocalDate.parse(x(1)).minusDays(1).toString))(x=> x(2).toDouble).map(x=>(x._1,x._2.sum))
  pf_data.foreach{x=>
    x(3)=(x(3).toDouble*prices(x(2),x(1))).toString
    x(1)=LocalDate.parse(x(1)).getMonth.toString
  }
  println(pf_data.head.toList)
  val monthly_best_earning_fruit=pf_data.groupMap(x=> (x(1),x(2)))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))

  months.foreach(x=> println(s"For month $x, max profitable : " +monthly_best_earning_fruit(x)))
  println("=-*"*20)



  val monthly_worst_earning_fruit=pf_data.groupMap(x=> (x(1),x(2)))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.minBy(_._2)._1._2))
  months.foreach(x=> println(s"For month $x, worst profitable : " +monthly_worst_earning_fruit(x)))
  println("=-*"*20)

  val monthly_best_employee_fruit=pf_data.groupMap(x=> (x(1),x(0)))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))
  months.foreach(x=> println(s"For month $x, best employee : " +monthly_best_employee_fruit(x)))
  println("=-*"*20)

  val best_earning_fruit=pf_data.groupMap(x=> x(2))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum)).maxBy(_._2)
  println(f"Best fruit: ${best_earning_fruit._1}, with profit= ${best_earning_fruit._2}%.4f")


  val worst_earning_fruit=pf_data.groupMap(x=> x(2))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum)).minBy(_._2)
  println(f"Worst fruit: ${worst_earning_fruit._1}, with profit= ${worst_earning_fruit._2}%.4f")

  val best_employee=pf_data.groupMap(_(0))(_(3).toDouble)
    .map(x=>(x._1,x._2.sum)).maxBy(_._2)
  println(f"Best employee: ${best_employee._1}, with profit= ${best_employee._2}%.4f")
  println("=-*"*20)

}
