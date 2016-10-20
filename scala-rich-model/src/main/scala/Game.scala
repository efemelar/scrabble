package scrabble

import bag.Bag


class Game private(persons: Seq[Person]) {
  val players = persons.map(p => Player(p.id, Score(0)))
}

object Game {
  def start(invitation: Invitation, bag: Bag) = {
    new Game(order(invitation, bag))
  }

  def order(i: Invitation, b: Bag): Seq[Person] = {
    val (tiles, _) = b.draw(i.persons.size)
    i.persons.zip(tiles).sortBy(_._2).map(_._1)
  }
}
