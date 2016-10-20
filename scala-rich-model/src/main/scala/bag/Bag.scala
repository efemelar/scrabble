package scrabble
package bag


trait Bag {
  type Tiles = List[Tile]

  protected def tiles: Tiles
  protected def shuffle(tiles: Tiles): Tiles
  protected def cons(tiles: Tiles): Bag

  def draw: (Tile, Bag) = {
    shuffle(tiles) match {
      case Nil => throw new NoTilesLeft
      case h :: Nil => (h, EmptyBag)
      case h :: ts  => (h, cons(ts))
    }
  }

  def draw(n: Int): (Tiles, Bag) = {
    val (ts_, b_) = (1 to n).foldLeft[(Tiles, Bag)]((Nil, this)) {
      case ((ts, b), _) =>
        val (t, bb) = b.draw
        (t :: ts, bb)
    }
    (ts_.reverse, b_)
  }

  def isEmpty = tiles.isEmpty

  override def toString =
    "Bag("+tiles.map(_.letter).mkString(",")+")"
}

object EmptyBag extends Bag {
  protected val tiles: Tiles = Nil
  protected def shuffle(ts: Tiles): Tiles = ts
  protected def cons(ts: Tiles): Bag = this
}

class NoTilesLeft extends Exception

