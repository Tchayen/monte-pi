import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

case object Start
case object Stop
case object Fetch

class SamplerActor extends Actor {
  def receive = {
    case Stop => context.stop(self)
  }
}

class VerifierActor extends Actor {
  def receive = {
    case Stop => context.stop(self)
  }
}

class ReducerActor extends Actor {
  val child = context.actorOf(Props[VerifierActor], name = "reducer-actor")

  def receive = {
    case Start => println("Start!")
    case Stop  => context.stop(self)
  }
}

object Main extends App {
  val system = ActorSystem("system")
  val reducerActor = system.actorOf(Props[ReducerActor], name = "reducer-actor")

  reducerActor ! Start

  system.terminate
}
