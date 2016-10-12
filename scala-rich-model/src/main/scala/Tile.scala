package scrabble

import util.Random


case class Tile private(letter: Char) extends AnyVal

object Tile {
  private val all = ('A' to 'Z').map(Tile(_))

  implicit val ordering = Ordering.fromLessThan[Tile]((a, b) => a.letter < b.letter)
}
