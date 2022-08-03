import scala.io.Source.fromFile
import java.time.LocalDate
import java.time.Month._

object harvestNew extends App {
  val person_fruit=fromFile("harvest.csv")
  val fruit_price=fromFile("prices.csv")
  var pf_data= Array[Array[String]]()
  var fp_data=Array[Array[String]]()
  for(line <- person_fruit.getLines()) pf_data= pf_data :+ line.split(",")
  for(line <- fruit_price.getLines()) fp_data= fp_data :+ line.split(",")

  // filtering
  pf_data=pf_data.tail
  fp_data=fp_data.tail


  val months=Array(JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER)
  val gatherers=(pf_data map(_(0))).distinct
  val fruits= (pf_data map(_(2))).distinct


  val monthly_max_gatherer= pf_data.groupMap(x=>(LocalDate.parse(x(1)).getMonth,x(0)))(x=> x(3).toDouble).map(x=> (x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))
  months.foreach(x=> println(s"For month $x, max gatherer : " +monthly_max_gatherer(x)))
  println("=-*"*20)

  val fruit_max_gatherer= pf_data.groupMap(x=>(x(2),x(0)))(x=> x(3).toDouble).map(x=> (x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))
  fruits.foreach(x=> println(s"For fruit $x, max gatherer : " +fruit_max_gatherer(x)))
  println("=-*"*20)

/*  val fruitEarning=for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }*/
  // fruitEarning fruit name, month, price
  val monthly_best_earning_fruit=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(x=> (x._2,x._1))(_._3).map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))


  months.foreach(x=> println(s"For month $x, max profitable : " +monthly_best_earning_fruit(x)))
  println("=-*"*20)

  val monthly_worst_earning_fruit=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(x=> (x._2,x._1))(_._3).map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.minBy(_._2)._1._2))
  months.foreach(x=> println(s"For month $x, worst profitable : " +monthly_worst_earning_fruit(x)))
  println("=-*"*20)

  val monthly_best_employee_fruit=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(x=> (x._2,x._4))(_._3).map(x=>(x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))
  months.foreach(x=> println(s"For month $x, best employee : " +monthly_best_employee_fruit(x)))
  println("=-*"*20)

  val best_earning_fruit=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(_._1)(_._3).map(x=>(x._1,x._2.sum)).maxBy(_._2)
  println(f"Best fruit: ${best_earning_fruit._1}, with profit= ${best_earning_fruit._2}%.4f")


  val worst_earning_fruit=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(x=> x._1)(_._3).map(x=>(x._1,x._2.sum)).minBy(_._2)
  println(f"Worst fruit: ${worst_earning_fruit._1}, with profit= ${worst_earning_fruit._2}%.4f")

  val best_employee=(for{
    x <- pf_data
    y <- fp_data  if (x(2) == y(0)) && (x(1)== LocalDate.parse(y(1)).minusDays(1).toString)
  } yield {
    (y(0), LocalDate.parse(x(1)).getMonth, x(3).toDouble * y(2).toDouble,x(0))
  }).groupMap(_._4)(_._3).map(x=>(x._1,x._2.sum)).maxBy(_._2)
  println(f"Best employee: ${best_employee._1}, with profit= ${best_employee._2}%.4f")
  println("=-*"*20)
  person_fruit.close()
  fruit_price.close()
}
