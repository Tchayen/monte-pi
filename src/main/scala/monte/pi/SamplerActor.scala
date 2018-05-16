package monte.pi

import akka.actor.Actor

object Sampler extends Types {
  val batchSize = 10000
  final case object GetBatch
  final case class Batch(values: Seq[Point])
}

class SamplerActor extends Actor with Types {
  import Sampler._

  def generateSamples(n: Int) = {
    Seq.fill(n)((math.random, math.random))
  }
  def receive = {
    case GetBatch => sender ! Batch(generateSamples(batchSize))
  }
}
