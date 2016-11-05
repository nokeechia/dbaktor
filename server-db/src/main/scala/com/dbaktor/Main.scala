package com.dbaktor

import akka.actor.{ActorSystem, Props}
import com.dbaktor.actors.Dbaktor

/**
  * Created by Keech on 09/10/2016.
  */
object Main extends App {
  val system = ActorSystem("akkademy")
  system.actorOf(Props[Dbaktor], name = "dbaktor")
}