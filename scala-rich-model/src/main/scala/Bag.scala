package scrabble


trait Bag {
  def take: (Tile, Bag)
}

class NoTilesLeft extends Exception

class DeterministicBag(tiles: Tile*) extends Bag {
  private val leftTiles = tiles.toList

  def take = {
    (leftTiles.head, new DeterministicBag(leftTiles.tail: _*))
  }

  override def toString =
    "Bag("+leftTiles.map(_.letter).mkString(",")+")"
}
