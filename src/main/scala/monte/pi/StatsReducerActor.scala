package monte.pi

import akka.actor.{Actor, ActorLogging}

object StatsReducerActor {

  case object Ack

  case object StreamInitialized

  case object StreamCompleted

  final case class StreamFailure(ex: Throwable)

}

class StatsReducerActor() extends Actor with ActorLogging {

  import StatsReducerActor._

  var stats = new Stats(0, 0)

  def receive: Receive = {
    case StreamInitialized ⇒
      log.info("Stream initialized!")
      sender() ! Ack // ack to allow the stream to proceed sending more elements

    case el: Boolean ⇒
      stats = stats.update(el)
      println(stats)
      //      log.info("Received element: {}", el)
      sender() ! Ack // ack to allow the stream to proceed sending more elements

    case StreamCompleted ⇒
      println(s"Final effect: $stats")
      log.info("Stream completed!")
    case StreamFailure(ex) ⇒
      log.error(ex, "Stream failed!")
  }
}
