import java.time.LocalDate
import java.time.Month._
import scala.collection.mutable
import scala.io.Source.fromFile

object harvestNew extends App {

  def person_fruit_csv = fromFile("harvest.csv").getLines().drop(1).map(_.split(","))
  def fruit_price_csv = fromFile("prices.csv").getLines().drop(1).map(_.split(","))
/*
  val months=Array(JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER).map(_.toString)
  val gatherers=person_fruit_csv.map(_.head).distinct
  val fruits= person_fruit_csv.map(_(2)).distinct

  val monthly_max_gatherer=person_fruit_csv.toList
    .groupMap(x=>(LocalDate.parse(x(1)).getMonth.toString,x.head))(x=> x(3).toDouble)
    .map(x=> (x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))

//  println(monthly_max_gatherer)
  months.foreach(x=> println(s"For month $x, max gatherer : " +monthly_max_gatherer(x)))
  println("=-*"*20)


  val fruit_max_gatherer= person_fruit_csv.toList
    .groupMap(x=>(x(2),x(0)))(x=> x(3).toDouble).map(x=> (x._1,x._2.sum))
    .groupBy(x => x._1._1).map(x=> (x._1,x._2.maxBy(_._2)._1._2))

  fruits.foreach(x=> println(s"For fruit $x, max gatherer : " +fruit_max_gatherer(x)))
  println("=-*"*20)

*/
  val prices=fruit_price_csv.toList.groupMap(x=> (x(0),LocalDate.parse(x(1)).minusDays(1).toString))(x=> x(2).toDouble).map(x=>(x._1,x._2.sum))
  def combined_data:mutable.Map[(String,String,String),Double]={
    val m=scala.collection.mutable.Map[(String,String,String),Double]().withDefaultValue(0.0)
    person_fruit_csv.toList.foreach{x=>
      val key=(x(1).take(7),x(0),x(2)) // data, person, fruit
      m(key)=m(key)+x(3).toDouble*prices(x(2),x(1))
    }
    m
  }
//  println(combined_data)

  //  val monthly_best_earning_fruit=combined_data.groupMap(x=> (x._1._1,x._1._2))(x=> x._2)
  val monthly_best_earning_fruit=combined_data.groupBy(x=> (x._1._1,x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))

  println(monthly_best_earning_fruit)



  val monthly_worst_earning_fruit=combined_data.groupBy(x=> (x._1._1,x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.minBy(_._2)._1._2))
  println(monthly_worst_earning_fruit)

  val best_employee=combined_data.groupBy(x=> (x._1._1,x._1._2))
    .map(x=> (x._1,x._2.values.sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))
  println(best_employee)

  val yearly_best_earning_fruit=combined_data.groupBy(x=>(x._1._1.take(4),x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .maxBy(_._2)
  println(yearly_best_earning_fruit._1._2)

  val yearly_worst_earning_fruit=combined_data.groupBy(x=>(x._1._1.take(4),x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .minBy(_._2)
  println(yearly_worst_earning_fruit._1._2)

  val yearly_best_employee=combined_data.groupBy(x=>(x._1._1.take(4),x._1._2))
    .map(x=> (x._1,x._2.values.sum))
    .maxBy(_._2)
  println(yearly_best_employee._1._2)

}
