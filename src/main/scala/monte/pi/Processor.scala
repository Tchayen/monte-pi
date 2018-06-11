package monte.pi

import akka.Done
import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.Future

object Processor extends App {
  implicit val system = ActorSystem("processor_system")
  implicit val materializer = ActorMaterializer()
  implicit val size = 1

  def runCount(n: Int): Future[Done] = Source.repeat(new Shooter(size))
    .take(n)
    .map(_.fire())
    .map(_.isAccurate(size))
    .fold(new Stats(0, 0))((acc, x) => acc.update(x))
    .map(_.getPi)
    .runWith(Sink.foreach(println))

  def runProgressive(n: Int) = {
    // sent from actor to stream to "ack" processing of given element
    val AckMessage = StatsReducerActor.Ack

    // sent from stream to actor to indicate start, end or failure of stream:
    val InitMessage = StatsReducerActor.StreamInitialized
    val OnCompleteMessage = StatsReducerActor.StreamCompleted
    val onErrorMessage = (ex: Throwable) ⇒ StatsReducerActor.StreamFailure(ex)

    val receiver = system.actorOf(
      Props(new StatsReducerActor()))
    val sink = Sink.actorRefWithAck(
      receiver,
      onInitMessage = InitMessage,
      ackMessage = AckMessage,
      onCompleteMessage = OnCompleteMessage,
      onFailureMessage = onErrorMessage
    )

    Source.repeat(new Shooter(size))
      .take(n)
      .map(_.fire())
      .map(_.isAccurate(size))
      .runWith(sink)
  }

  runProgressive(1000000)
}
