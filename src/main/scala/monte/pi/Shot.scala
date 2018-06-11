package monte.pi

class Shot(val x: Double, val y: Double) {
  def isAccurate(a: Double): Boolean = (x * x) + (y * y) <= (a * a)
}
