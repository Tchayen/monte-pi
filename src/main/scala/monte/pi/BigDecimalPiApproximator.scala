package monte.pi

import java.math.MathContext

class BigDecimalPiApproximator(val all: Int, val acc: Int, val prec: Int) {
  def update(accurate: Boolean): BigDecimalPiApproximator = BigDecimalPiApproximator(all + 1, if (accurate) acc + 1 else acc, prec)

  val mathContext = new MathContext(prec + 1)

  def getPi: BigDecimal = BigDecimal(4.0, mathContext) * BigDecimal(acc, mathContext) / BigDecimal(all, mathContext)

  def getDelta: BigDecimal = getPi - realPi

  val stringPi = "3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381964428810975665933446128475648233"
  val realPi = BigDecimal(stringPi.take(2 + prec), mathContext)

  override def toString = s"π = $getPi\tΔ = $getDelta\t($acc/$all)"
}

object BigDecimalPiApproximator {
  def apply(all: Int, acc: Int, prec: Int): BigDecimalPiApproximator =
    new BigDecimalPiApproximator(all, acc, prec)

//  def main(args: Array[String]): Unit = {
//    println(BigDecimalPiApproximator(1, 1, 16).realPi.toString() == "3.1415926535897932")
//    println(BigDecimalPiApproximator(1, 1, 16).realPi.toString())
//  }
}
