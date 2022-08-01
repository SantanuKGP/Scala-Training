import scala.io.Source.fromFile
import scala.collection.mutable.{Map => mMap}
import java.time.LocalDate
import java.time.Month._
object harvestDataPrevCode extends App{
  val person_fruit=fromFile("harvest.csv")
  val fruit_price=fromFile("prices.csv")
  var pf_data= Array[Array[String]]()
  var fp_data=Array[Array[String]]()
  for(line <- person_fruit.getLines()) pf_data= pf_data :+ line.split(",")
  for(line <- fruit_price.getLines()) fp_data= fp_data :+ line.split(",")

  // filtering
  pf_data=pf_data.tail
  fp_data=fp_data.tail


  val months=Array(JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER).map(_.toString)
  val gatherers=(pf_data map(_(0))).distinct
  val fruits= (pf_data map(_(2))).distinct

  monthlyFruitCollection(pf_data)
  incomeCollection(pf_data,fp_data)

  def monthlyFruitCollection(x: Array[Array[String]]):Unit={
    val m_p_f= mMap[List[String],Double]().withDefaultValue(0)
    val p_f=mMap[List[String],Double]().withDefaultValue(0)
    val p=mMap[String,Double]().withDefaultValue(0)
    x.foreach{ data =>
      val month_name= LocalDate.parse(data(1)).getMonth
      val key= List(month_name.toString,data(0),data(2))
      m_p_f(key)=m_p_f(key)+ data(3).toDouble

      val pf_key=List(data(0),data(2))
      p_f(pf_key)=p_f(pf_key)+ data(3).toDouble

      p(data(0))=p(data(0))+data(3).toDouble
    }

    for(i <- months){
      println("Data for Month : "+ i)
      var maximum=0.0
      var name=""
      for(j <- gatherers){
        var total=0.0
        for(k<- fruits){
          total=total+ m_p_f(List(i,j,k))

        }
        if(total>maximum){
          maximum=total
          name=j
        }
      }
      println(f"Max fruit gainer: $name, amount:  $maximum%.2f")
    }
    println("-"*100 + "\n"+"-"*100)

    val (best,amount)= p.maxBy(_._2)
    println(f"Best Gatherer by year $best with amount = $amount%.2f")

    println("-"*100 + "\n"+"-"*100)

    for(i <- fruits){
      var maximum=0.0
      var name=""
      for(j<- gatherers){
        if(p_f(List(j,i))> maximum){
          maximum=p_f(List(j,i))
          name=j
        }
      }
      println(f"For fruit $i, best gatherer is $name, his amount is $maximum%.2f")
    }
    println("-"*100 + "\n"+"-"*100)
  }


  def incomeCollection(x:Array[Array[String]], y:Array[Array[String]]):Unit={
    val dfp= mMap[List[String],Double]().withDefaultValue(0) //Daily fruit price
    //    val monthly_person_fruit_income=mMap[List[String],Double]().withDefaultValue(0)
    val monthly_fruit_income= mMap[List[String],Double]().withDefaultValue(0)
    val monthly_person_income=mMap[List[String],Double]().withDefaultValue(0)
    val person_income=mMap[String,Double]().withDefaultValue(0)
    val fruit_income=mMap[String,Double]().withDefaultValue(0)

    y.foreach{
      data=>
        val date=LocalDate.parse(data(1)).minusDays(1).toString
        val key=List(date,data(0))
        dfp(key)=data(2).toDouble
    }
    x.foreach{data=>
      val month_name= LocalDate.parse(data(1)).getMonth.toString
      val price= data(3).toDouble*dfp(List(data(1),data(2)))
      //      val key= List(month_name.toString,data(0),data(2))
      //      monthly_person_fruit_income(key)=monthly_person_fruit_income(key)+ price

      val key1=List(month_name,data(0))
      monthly_person_income(key1)=monthly_person_income(key1)+price

      val key2=List(month_name,data(2))
      monthly_fruit_income(key2)=monthly_fruit_income(key2)+ price

      fruit_income(data(2))=fruit_income(data(2))+price
      person_income(data(0))=person_income(data(0))+price
    }

    for(i<- months){
      var maximum=0.0
      var minimum=Double.PositiveInfinity
      var maxName=""
      var minName=""
      for(j<-fruits){
        if(monthly_fruit_income(List(i,j))>maximum){
          maximum=monthly_fruit_income(List(i,j))
          maxName=j
        }
        if(monthly_fruit_income(List(i,j))<minimum){
          minimum=monthly_fruit_income(List(i,j))
          minName=j
        }
      }
      println(f"For month $i best profitable fruit: $maxName, profit= $maximum%.4f")
      println(f"For month $i least profitable fruit: $minName, profit= $minimum%.4f")
    }
    println("-"*100 + "\n"+"-"*100)
    val (best,amt1)=fruit_income.maxBy(_._2)
    val (worst,amt2)=fruit_income.minBy(_._2)
    println(f"Best fruit by year $best with profit = $amt1%.4f")
    println(f"Worst fruit by year $worst with profit = $amt2%.4f")
    println("-"*100 + "\n"+"-"*100)
    for(i<- months){
      var maximum=0.0
      var maxName=""
      for(j<-gatherers){
        if(monthly_person_income(List(i,j))>maximum){
          maximum=monthly_person_income(List(i,j))
          maxName=j
        }
      }
      println(f"For month $i best employee: $maxName, profit= $maximum%.4f")

    }
    println("-"*100 + "\n"+"-"*100)
    val (top,amt3)=person_income.maxBy(_._2)
    println(f"Best employee by year $top with profit = $amt3%.4f")

    println("-"*100 + "\n"+"-"*100)
  }


  person_fruit.close()
  fruit_price.close()
}
