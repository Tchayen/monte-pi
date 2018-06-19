package monte.pi

import org.scalatest._

class BigDecimalPiApproximatorTest extends FlatSpec {
  "BigDecimalPiApproximator" should "have pi equal 3.1415926535897932 when prec is 16" in {
    val bigDecimalPiApproximator = new BigDecimalPiApproximator(0, 0, 16)
    assert(bigDecimalPiApproximator.realPi.toString() == "3.1415926535897932")
  }

  it should "have pi equal 3.14159265 when prec is 8" in {
    val bigDecimalPiApproximator = new BigDecimalPiApproximator(0, 0, 8)
    assert(bigDecimalPiApproximator.realPi.toString() == "3.14159265")
  }

}
