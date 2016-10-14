package scrabble

import util.Random


class Tile private(val letter: Char) extends AnyVal

object Tile {
  val blank = new Tile(' ')
  private val all = ('A' to 'Z').map(new Tile(_)).toSet + blank

  def apply(letter: Char) =
    all.find(_.letter == letter).getOrElse(throw new IllegalTileLetter(letter))

  val score = {
    Seq(
      0  -> Seq(blank.letter),
      1  -> Seq('A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U'),
      2  -> Seq('D', 'G'),
      3  -> Seq('B', 'C', 'M', 'P'),
      4  -> Seq('F', 'H', 'V', 'W', 'Y'),
      5  -> Seq('K'),
      8  -> Seq('J', 'X'),
      10 -> Seq('Q', 'Z'))
    .flatMap { case (score, cs) =>
      cs.map(Tile(_) -> score)
    }
    .toMap
  }

  implicit val ordering = Ordering.fromLessThan[Tile]((a, b) => a.letter < b.letter)

  class IllegalTileLetter(letter: Char) extends Exception(
    s"Found '$letter', but only 'A' to 'Z' and ' '(blank) tiles can be used in scrabble"
  )
}
