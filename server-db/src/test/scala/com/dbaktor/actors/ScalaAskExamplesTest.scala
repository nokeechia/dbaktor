package com.dbaktor.actors

/**
  * Created by Keech on 09/10/2016.
  */
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
class ScalaAskExamplesTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val pongActor = system.actorOf(Props(classOf[PongActor]))
  describe("Pong actor") { it("should respond with Pong") {
    val future: Future[Any] = pongActor ? "Ping" //uses the implicit timeout
    val result = Await.result(future.mapTo[String], 1 second)
    assert(result == "Pong") }
    it("should fail on unknown message") {
      val future = pongActor ? "unknown"
      intercept[Exception]{
        val result = Await.result(future.mapTo[String], 1 second)
        result shouldBe "unknown"
      }
    }
  }
  describe("FutureExamples"){
    import scala.concurrent.ExecutionContext.Implicits.global
    it("should print to console"){
      (pongActor ? "Ping").onSuccess({
        case x: String => println("replied with: " + x)
      })
      Thread.sleep(100)
    }
    it("should fail"){
      (pongActor ? "nope").onFailure({
        case e: Exception => println("did not work: " + e.getMessage)
      })
    }
    it("should transform async"){
      val f: Future[String] = askPong("Ping").flatMap(x => {
        assert(x == "Pong")
        askPong("Ping")
      })
      val c = Await.result(f, 1 second)
      c should equal("Pong")
    }
    it("Shows recovery logic"){
      askPong("causeError").recoverWith({
        case t: Exception => println("fails once")
          askPong("causeError").recoverWith({
          case e: Exception => println("fails once")
            val r = askPong("Ping")
            r shouldBe "Pong"
            r
        })
      })
    }
    it("demonstrates chaining of Future ops"){
      val f = askPong("Ping").
        flatMap{x =>
          println(x)
          askPong("Ping" + x)
        }.
        recover({
          case e: Exception => println("There was an error")
        })
    }
    it("Combines futures"){
      val f1 = Future{4}
      val f2 = Future{5}
      val futureAddition: Future[Int] = for( res1 <- f1; res2 <- f2 ) yield res1 + res2
      val res = futureAddition.onSuccess({
        case i: Int => println(i)
          i shouldBe 9
      })
    }
    it("Deals with lists of futures"){
      val listOfFutures =  List("Pong", "Pong", "failed").map(x => askPong(x))
      val futureOfList: Future[List[String]] = Future.sequence(listOfFutures)
      Future.sequence(listOfFutures.map(f => f.recover{case e: Exception => ""}))
    }
  }
  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]
  //val futureFuture: Future[Future[String]] = askPong("Ping").map(x => { askPong(x) })
}