package scrabble

import bag.Bag


case class Player private(id: Id[Person], score: Score)

class Invitation private (
  val persons: Seq[Person]
) {
  import Invitation._

  def join(person: Person): Invitation = {
    ensure(adequateNumberOfPlayers.contains(persons.size + 1), new InadequateNumberOfPlayers)
    new Invitation(persons :+ person)
  }

  def startGame(bag: Bag) = {
    ensure(adequateNumberOfPlayers.contains(persons.size), new InadequateNumberOfPlayers)
    Game.start(this, bag)
  }
}

object Invitation {
  private val adequateNumberOfPlayers = 2 to 4

  def apply(offering: Person) = new Invitation(Seq(offering))
}

class InadequateNumberOfPlayers extends Exception(
  "Only 2 to 4 players can enjoy the game")
