package com.dbaktor.actors

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.dbaktor.Dbaktor
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

/**
  * Created by Keech on 09/10/2016.
  */
class LastStringTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("dbaktor") {
    describe("given SetRequest"){
      it("should place key/value into map"){
        val actorRef = TestActorRef(new LastString)
        actorRef ! "This is NOT the last string"
        actorRef ! "This is the last string"
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.lastString shouldBe "This is the last string"
      }
    }
  }

}