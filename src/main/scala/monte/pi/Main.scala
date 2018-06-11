package monte.pi

import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.duration._
import scala.concurrent.Await

case object Start

class SupervisorActor extends Actor {
  def receive = {
    case Start =>
      val sampler = context.actorOf(Props(new SamplerActor(math.random)), name = "sampler-actor")
      sampler ! Sampler.GetBatch

    case Sampler.Batch(batch) =>
      val verifier = context.actorOf(Props[VerifierActor], name = "verifier-actor")
      verifier ! Sampler.Batch(batch)

    case Verifier.InCircleCount(m) =>
      val n: Double = Sampler.batchSize
      val pi: Double = (4 * m) / n
      println(s"We estimate π to be equal $pi")
      context.system.terminate
  }
}

object Main extends App {
  val system = ActorSystem("system")
  val supervisor = system.actorOf(Props[SupervisorActor], name = "supervisor-actor")

  supervisor ! Start
}

// To be changed