package monte.pi

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest._

class ActorTests() extends TestKit(ActorSystem("system"))
    with Matchers
    with FlatSpecLike
    with BeforeAndAfterAll
{

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

//  "Sampler actor" should "Send batch of samples upon request" in {
//    val probe = TestProbe()
//    val sampler = system.actorOf(Props[SamplerActor], name = "sampler-actor")
//    sampler.tell(Sampler.GetBatch, probe.ref)
//    val response = probe.expectMsgType[Sampler.Batch]
//
//    response.values.length should be (Sampler.batchSize)
//  }
}
