package monte.pi

import akka.Done
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.Future

object Processor extends App {
  implicit val system = ActorSystem("processor_system")
  implicit val materializer = ActorMaterializer()
  implicit val size = 10

  def runCount(n: Int): Future[Done] = Source.repeat(new Shooter(size))
    .take(n)
    .map(_.fire())
    .map(_.isAccurate(size))
    .fold(new Stats(0, 0))((acc, x) => acc.update(x))
    .map(_.getPi())
    .runWith(Sink.foreach(println))

  runCount(100000)
}
