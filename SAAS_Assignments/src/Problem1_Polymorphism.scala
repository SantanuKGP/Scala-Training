package com.sk.test1
import java.lang.Math.abs
import scala.io.StdIn.readLine

object Problem1_Polymorphism extends App{
  var str=readLine("Enter the values of side: ")
  while(str!=""){
    val x=str.split(" ").map(_.toDouble)
    new RightAngled(x)
    new Equilateral(x)
    new Isosceles(x)
    str=readLine("Enter the values of side: ")
  }
}

sealed class triangle(x: Array[Double]){
  val Array(x1,x2,x3)=x
  //  tolerance for dim 1
  def tol1(x : Double,y : Double) : Boolean = abs(x-y)<1e-3
  //  tolerance for dim 2
  def tol2(x : Double,y : Double) : Boolean = abs(x-y)<1e-6
  // logic for existence
  def logic(x: Double,y : Double, z: Double):Boolean= (x+y-z)> 0
  def isExists :Boolean= logic(x1,x2,x3) && logic(x2,x3,x1) && logic(x3,x1,x2)
  def IsRightAngled: Boolean= tol2(x1*x1+x2*x2,x3*x3) || tol2(x2*x2+x3*x3,x1*x1) || tol2(x3*x3+x1*x1,x2*x2)
  def IsEquilateral: Boolean=tol1(x1,x2) && tol1(x2,x3) && tol1(x3,x1)
  def IsIsosceles: Boolean=tol1(x1,x2) || tol1(x2,x3) || tol1(x3,x1)
  def Type : String= "Not Special"
}

sealed class RightAngled(x:Array[Double]) extends triangle(x: Array[Double]){
  override def Type="Right-Angled"
  if(isExists && IsRightAngled) Type else super.Type
}

sealed class Equilateral (x:Array[Double]) extends triangle(x: Array[Double]){
  override def Type="Equilateral"
  if(isExists && IsEquilateral) Type else super.Type
}

sealed class Isosceles (x:Array[Double]) extends triangle(x: Array[Double]){
  override def Type="Isosceles"
  if(isExists && IsIsosceles && !IsRightAngled && !IsEquilateral) Type
}
sealed class noTriangle(x:Array[Double]) extends triangle(x: Array[Double]){
  override def Type="Invalid"
  if(!isExists) Type
}
//
//class getType{
//  new RightAngled(x)
//  new Equilateral(x)
//  new Isosceles(x)
//
//}
