package scrabble
package bag


class DeterministicBag(
  protected val tiles: Bag#Tiles
) extends Bag {
  def this(ts: Tile*) = this(ts.toList)

  protected def shuffle(ts: Tiles) = ts
  protected def cons(ts: Tiles) = new DeterministicBag(ts)
}
