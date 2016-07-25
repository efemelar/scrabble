package scrabble.service

import scrabble.dao.GameDao
import scrabble.model.{Game, Player}


class GameService(
  gameDao: GameDao
) {
  def createGame(players: List[Player]): Long = {
    if ((2 to 4).contains(players.length)) {
      val game = Game(players = players)
      gameDao.create(game).id
    } else {
      throw new Exception("Number of players in game is not from 2 to 4")
    }
  }
}
