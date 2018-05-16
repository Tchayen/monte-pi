package monte.pi

import akka.actor.Actor
import scala.collection.GenSeq

object Sampler extends Types {
  val batchSize = 10000
  final case object GetBatch
  final case class Batch(values: GenSeq[Point])
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
