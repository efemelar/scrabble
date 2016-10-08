package scrabble


case class Player private(id: Id[Person], score: Score)

class Game private(players: Set[Player])

object Game {
  private val adequateNumberOfPlayers = 2 to 4

  private def player(p: Person) =
    Player(p.id, Score(0))

  def start(persons: Set[Person]): Game = {
    ensure(adequateNumberOfPlayers.contains(persons.size), new InadequateNumberOfPlayers)
    new Game(persons.map(player))
  }
}

class InadequateNumberOfPlayers extends Exception(
  "Only 2 to 4 players can enjoy the game")
