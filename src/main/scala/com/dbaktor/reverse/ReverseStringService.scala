package com.dbaktor.reverse

import akka.actor.{ActorSystem, Props}
import com.dbaktor.reverse.actors.ReverseString

/**
  * Created by Keech on 09/10/2016.
  */
object ReverseStringService {
  def main(args: Array[String]): Unit = {
    val system =  ActorSystem("akkademy")
    system.actorOf(Props[ReverseString], name = "reverse-string")
  }
}
