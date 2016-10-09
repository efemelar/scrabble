package scrabble


case class Player private(id: Id[Person], score: Score)

class Game private(players: Seq[Player])

object Game {
  private val adequateNumberOfPlayers = 2 to 4

  private def player(p: Person) =
    Player(p.id, Score(0))

  def start(persons: Set[Person]): Game = {
    ensure(adequateNumberOfPlayers.contains(persons.size), new InadequateNumberOfPlayers)
    val bag = new Bag
    val takes = persons.map((_, bag.take(1)))

    new Game(order(takes).map(player))
  }

  def order(takes: Set[(Person, Tile)]): Seq[Person] = {
    takes.toSeq.sortBy(_._2.letter).map(_._1)
  }
}

class InadequateNumberOfPlayers extends Exception(
  "Only 2 to 4 players can enjoy the game")
