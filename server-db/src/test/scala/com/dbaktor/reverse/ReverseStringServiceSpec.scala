package com.dbaktor.reverse

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.dbaktor.reverse.actors.ReverseString
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, FunSuite, Matchers}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by Keech on 09/10/2016.
  */
class ReverseStringServiceSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val actorRef = TestActorRef(new ReverseString)
  describe("Reverse String Service") {
    describe("sends String"){
      import scala.concurrent.ExecutionContext.Implicits.global
      it("should reverse Ping to gniP"){
        val future: Future[Any] = actorRef ? "Ping" //uses the implicit timeout
        val result = Await.result(future.mapTo[String], 1 second)
        println(result)
        result shouldBe "gniP"
      }
      it("The message is unable to be processed"){
        val future: Future[Any] = actorRef ? 5
        intercept[Exception] {
          val result = Await.result(future.mapTo[String], 1 second)
          println(result)
          result shouldBe ""
        }
      }
      it("Deals with a list of messages with some failures"){
        val listOfFutures =  List("Pong", "Pong", "failed",1,2,3).map(x => askReverseString(x))
        val futureOfList: Future[List[String]] = Future.sequence(listOfFutures)
        Future.sequence(listOfFutures.map(f => f.recover{
          case e: Exception => println(e.getMessage)
            e.getMessage shouldBe "unknown message"
        }))
      }
    }
    def askReverseString(message: Any): Future[String] = (actorRef ? message).mapTo[String]
  }

}