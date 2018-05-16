package monte.pi

import akka.actor.Actor

object Verifier {
  final case class InCircleCount(value: Int)

  implicit class Power(val x: Double) extends AnyVal {
    def ^(y: Double): Double = math.pow(x, y)
  }
}

class VerifierActor extends Actor with Types {
  import Verifier._

  def receive = {
    case Sampler.Batch(values) =>
      sender ! InCircleCount(values.count(p => ((p._1 - 0.5) ^ 2) + ((p._2 - 0.5) ^ 2) < (0.5 ^ 2)))
  }
}
