package com.dbaktor

import akka.actor.Actor
import akka.event.Logging
import com.dbaktor.messages.SetRequest

import scala.collection.mutable.HashMap

/**
  * Created by Keech on 09/10/2016.
  */
class Dbaktor  extends Actor{
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)
  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }
}