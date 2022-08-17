import java.time.LocalDate

import scala.io.Source.fromFile

object harvestNew extends App {

  def person_fruit_csv = fromFile("harvest.csv").getLines().drop(1).map(_.split(","))
  def fruit_price_csv = fromFile("prices.csv").getLines().drop(1).map(_.split(","))

  def monthly_max_gatherer=person_fruit_csv.toList.groupBy(x=> (x(1).take(7),x.head))
    .map(x=> (x._1,x._2.map(_.head(3).toDouble).sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))
  println(monthly_max_gatherer)

  def specific_fruit=person_fruit_csv.toList.groupBy(x=> (x(2),x.head))
    .map(x=> (x._1,x._2.map(_(3).toDouble).sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))
  println(specific_fruit)

  val prices=fruit_price_csv.toList.groupMap(x=> (x(0),LocalDate.parse(x(1)).minusDays(1).toString))(x=> x(2).toDouble).map(x=>(x._1,x._2.sum))

  def combined_data= person_fruit_csv.toList
    .groupBy(x=>(x(1),x.head,x(2)))
    .map(x=> (x._1,x._2.head(3).toDouble*prices(x._1._3,x._1._1)))

  val monthly_best_earning_fruit =combined_data.groupBy(x=> (x._1._1.take(7),x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))

  println(monthly_best_earning_fruit)



  val monthly_worst_earning_fruit=combined_data.groupBy(x=> (x._1._1.take(7),x._1._3))
    .map(x=> (x._1,x._2.values.sum))
    .groupBy(_._1._1).map(x=>(x._1,x._2.minBy(_._2)._1._2))
  println(monthly_worst_earning_fruit)

  val best_employee=combined_data.groupBy(x=> (x._1._1.take(7),x._1._2))
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
