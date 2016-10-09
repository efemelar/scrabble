package scrabble

import util.Random


case class Tile private(letter: Char) extends AnyVal

object Tile {
  private val all = ('A' to 'Z').map(Tile(_))
}

class Bag {
  def take(number: Int = 1) =
    Tile(('A' + Random.nextInt(26)).toChar)
}
