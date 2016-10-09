package com.dbaktor.actors

import akka.actor.{Actor, Status}


/**
  * Created by Keech on 09/10/2016.
  */
class PongActor extends Actor{
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case "Pong" => sender() ! "Ping"
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
