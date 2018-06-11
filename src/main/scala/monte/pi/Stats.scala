package monte.pi

class Stats(val all: Int, val acc: Int) {
  def update(accurate: Boolean): Stats = new Stats(all + 1, if (accurate) acc + 1 else acc)
  def getPi() : Double = 4.0 * acc.toDouble / all.toDouble;

  override def toString = s"Stats(All shots: $all, Accurate ones $acc)"
}
