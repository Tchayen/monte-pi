package monte.pi

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

object Processor extends App {
  implicit val system = ActorSystem("processor_system")
  implicit val materializer = ActorMaterializer()
  implicit val size = 10

  val source = Source.repeat(new Shooter(size))
    .map(_.fire())
    .map(_.isAccurate(size))
    .runWith(Sink.foreach(println))
}
