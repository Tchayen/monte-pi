package monte.pi

import akka.actor.{Actor, Props}

class ReducerActor extends Actor with Types {
  val child = context.actorOf(Props[VerifierActor], name = "reducer-actor")

  def receive = {
    case _ => println("Dunno")
  }
}

// To be deleted