package monte.pi

class Stats(val all: Int, val acc: Int) {
  def update(accurate: Boolean): Stats = new Stats(all + 1, if (accurate) acc + 1 else acc)

  def getPi: Double = 4.0 * acc.toDouble / all.toDouble

  def getDelta: Double = Math.abs(getPi - realPi)

  val realPi = 3.1415926535897932

  override def toString = s"π = $getPi\tΔ = $getDelta\t($acc/$all)"
}
