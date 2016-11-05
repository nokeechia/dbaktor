import com.dbaktor.client.Client
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by Keech on 09/10/2016.
  */
class ClientIntegrationSpec extends FunSpecLike with Matchers {
  val client = new Client("127.0.0.1:2553")
  describe("akkademyDbClient") {
    it("should set a value"){
      client.set("123", new Integer(123))
      val futureResult = client.get("123")
      val result = Await.result(futureResult, 10 seconds)
      println(result)
      result should equal(123)
    }
  }
}