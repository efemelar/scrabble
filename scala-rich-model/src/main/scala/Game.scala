package scrabble


class Game private(persons: Seq[Person]) {
  val players = persons.map(p => Player(p.id, Score(0)))
}

object Game {
  def start(invitation: Invitation, bag: Bag) = {
    new Game(order(invitation, bag))
  }

  def order(i: Invitation, b: Bag) = {
    val (allDraws, _) = {
      val noDraws = (Seq.empty[(Person, Tile)], b)

      i.persons.foldLeft(noDraws) { case ((draws, bag), person) =>
        val (tile, bagWithoutTile) = bag.take
        (draws :+ (person -> tile), bagWithoutTile)
      }
    }

    allDraws.sortBy(_._2).map(_._1)
  }
}
