package monte.pi

import scala.util.Random

class Shooter(a: Double) {
  private def draw(): Double = (Random.nextDouble() * (2 * a)) - a

  def fire(): Shot = new Shot(draw(), draw())
}
