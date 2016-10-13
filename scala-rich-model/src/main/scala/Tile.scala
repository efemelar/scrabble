package scrabble

import util.Random


class Tile private(val letter: Char) extends AnyVal

object Tile {
  private val blank = new Tile(' ')
  private val all = ('A' to 'Z').map(new Tile(_)).toSet + blank

  def apply(letter: Char) =
    all.find(_.letter == letter).getOrElse(throw new IllegalTileLetter(letter))

  implicit val ordering = Ordering.fromLessThan[Tile]((a, b) => a.letter < b.letter)

  class IllegalTileLetter(letter: Char) extends Exception(
    s"Found '$letter', but only 'A' to 'Z' and ' '(blank) tiles can be used in scrabble"
  )
}
