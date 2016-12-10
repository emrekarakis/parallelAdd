/**
 * Created by emrekarakis on 15/12/15.
 */

import scala.util.{Failure, Success, Random}
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
object parallelAdd {

  val list=List(89,456,546,0,36,90,23,21,45,89,0,2,6,9,22,0,42,54,32,26)
  val l: List[Int] = (1 to 100).toList

  def main(args: Array[String]) {

    val groupedList: List[List[Int]] = l.grouped(10).toList

    val listOfFuture: List[Future[Int]] = groupedList.map { a =>
      val number: Future[Int]=Add(a)
      number
    }

    val futureOfList: Future[List[Int]] = Future.sequence(listOfFuture)

    val futureFinalResult: Future[Int] = futureOfList.map { x =>
     x.sum
    }

    val finalResult: Int = Await.result(futureFinalResult, Duration(5, "seconds"))
    println(s"finalResult $finalResult")
  }




  def Add(list:List[Int]):Future[Int] = {
    val futureValue: Future[Int] = Future {
      println("printing the addition of the elements...")
      val aa = list.sum
      println(aa)
      aa
    }

    futureValue

  }

  def sleep(time: Long) { Thread.sleep(time) }

}

/*val aa: Map[Char, Int] = a.mapValues { e: String =>
           e.length
         }

         val b: Map[Char, Int] = a.map { case (character: Char, str: String) =>
           (character -> str.size)
         }*/