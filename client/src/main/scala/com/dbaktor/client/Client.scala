package com.dbaktor.client

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.dbaktor.messages.{GetRequest, SetRequest}

import scala.concurrent.duration._
/**
  * Created by Keech on 09/10/2016.
  */
class Client(remoteAddress: String){
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/dbaktor")
  def set(key: String, value: Object) = { remoteDb ? SetRequest(key, value) }
  def get(key: String) = { remoteDb ? GetRequest(key) }
}