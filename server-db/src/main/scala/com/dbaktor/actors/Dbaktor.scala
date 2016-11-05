package com.dbaktor.actors

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.dbaktor.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable.HashMap

/**
  * Created by Keech on 09/10/2016.
  */
class Dbaktor  extends Actor{
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)
  val actorName = self.path.name
  val actorPath = self.path
  println("Actor Name: {}",actorName)
  println("Actor Path: {}",actorPath)
  /*override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }*/
  override def receive = {
    case SetRequest(key, value) => log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) => log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match{
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    case o => Status.Failure(new ClassNotFoundException)
  }

}
