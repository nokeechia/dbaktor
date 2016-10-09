package com.dbaktor

import akka.util.Timeout
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import akka.actor.ActorSystem
import com.dbaktor.messages.SetRequest
import akka.testkit.TestActorRef
import scala.concurrent.duration

/**
  * Created by Keech on 09/10/2016.
  */
class DbaktorTest  extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("dbaktor") {
    describe("given SetRequest"){
      it("should place key/value into map"){
        val actorRef = TestActorRef(new Dbaktor)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }

}
