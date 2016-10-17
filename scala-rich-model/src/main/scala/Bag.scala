package scrabble


trait Bag {
  def take: (Tile, Bag)
}

object EmptyBag extends Bag {
  def take: (Tile, Bag) =
    throw new NoTilesLeft
}

class NoTilesLeft extends Exception

class DeterministicBag(
  tiles: List[Tile]
) extends Bag {
  def this(ts: Tile*) = this(ts.toList)

  def take = {
    tiles match {
      case Nil => throw new NoTilesLeft
      case h :: Nil => (h, EmptyBag)
      case h :: ts  => (h, new DeterministicBag(ts: _*))
    }
  }

  override def toString =
    "Bag("+tiles.map(_.letter).mkString(",")+")"
}
