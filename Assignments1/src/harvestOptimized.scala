import java.time.LocalDate
import scala.io.Source.fromFile

object harvestOptimized extends App{
  def person_fruit_csv = fromFile("harvest.csv").getLines().drop(1).map(_.split(",").toList)
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

  def max_method(time:Int,select:String)={
    select match{
      case "fruit" if time==7 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._3))
        .map(x=> (x._1,x._2.values.sum))
        .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))
      case "person" if time==7 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._2))
        .map(x=> (x._1,x._2.values.sum))
        .groupBy(_._1._1).map(x=>(x._1,x._2.maxBy(_._2)._1._2))
      case "fruit" if time==4 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._3))
        .map(x=> (x._1,x._2.values.sum))
        .maxBy(_._2)._1._2
      case "person" if time==4 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._2))
        .map(x=> (x._1,x._2.values.sum))
        .maxBy(_._2)._1._2
    }
  }
  def min_method(time:Int,select:String)={
    select match{
      case "fruit" if time==7 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._3))
        .map(x=> (x._1,x._2.values.sum))
        .groupBy(_._1._1).map(x=>(x._1,x._2.minBy(_._2)._1._2))
      case "person" if time==7 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._2))
        .map(x=> (x._1,x._2.values.sum))
        .groupBy(_._1._1).map(x=>(x._1,x._2.minBy(_._2)._1._2))
      case "fruit" if time==4 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._3))
        .map(x=> (x._1,x._2.values.sum))
        .minBy(_._2)._1._2
      case "person" if time==4 => combined_data.groupBy(x=> (x._1._1.take(time),x._1._2))
        .map(x=> (x._1,x._2.values.sum))
        .minBy(_._2)._1._2
    }
  }

  val monthly_best_earning_fruit =max_method(7,"fruit")
  println(monthly_best_earning_fruit)

  val monthly_worst_earning_fruit=min_method(7,"fruit")
  println(monthly_worst_earning_fruit)

  val best_employee=max_method(7,"person")
  println(best_employee)

  val yearly_best_earning_fruit= max_method(4,"fruit")
  println(yearly_best_earning_fruit)

  val yearly_worst_earning_fruit=min_method(4,"fruit")
  println(yearly_worst_earning_fruit)

  val yearly_best_employee=max_method(4,"fruit")
  println(yearly_best_employee)
}
