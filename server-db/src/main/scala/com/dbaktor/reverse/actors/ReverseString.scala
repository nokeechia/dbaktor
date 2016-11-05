package com.dbaktor.reverse.actors

import akka.actor.{Actor, Status}

/**
  * Created by Keech on 09/10/2016.
  */
class ReverseString extends Actor{
  override def receive: Receive = {
    case s: String => sender() ! s.reverse
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }
}
