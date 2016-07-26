package scrabble

class Game private(players: Set[Player])

object Game {
  private val adequateNumberOfPlayers = 2 to 4

  def start(ps: Set[Player]): Game = {
    ensure(adequateNumberOfPlayers.contains(ps.size), new InadequateNumberOfPlayers)
    new Game(ps)
  }
}

class InadequateNumberOfPlayers extends Exception(
  "Only 2 to 4 players can enjoy the game")
