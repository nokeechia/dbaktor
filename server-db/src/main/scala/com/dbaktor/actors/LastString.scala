package com.dbaktor.actors

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by Keech on 09/10/2016.
  */
class LastString extends Actor {
  var lastString = ""
  val log = Logging(context.system, this)
  override def receive = {
    case msg:String => {
      log.info("received Msg {}", msg)
      lastString = msg
    }
    case o => log.info("received unknown message: {}", o)
  }

}
