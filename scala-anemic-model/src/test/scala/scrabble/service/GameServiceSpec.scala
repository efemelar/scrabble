package scrabble.service

import org.specs2._, mutable.Specification, mock.Mockito
import scrabble.dao.GameDao
import scrabble.model.{Game, Player}


class GameServiceSpec extends Specification with Mockito {

  "Game service" should {
    "create game" >> {
      val mockGameDao = mock[GameDao]
      val gameService = new GameService(mockGameDao)

      val players = (0 to 3).map(Player(_)).toList

      mockGameDao.create(Game(0, players)) returns Game(1, players)

      gameService.createGame(players) mustEqual 1L
    }

    "not create game when less then 2 players" >> {
      val mockGameDao = mock[GameDao]
      val gameService = new GameService(mockGameDao)

      gameService.createGame(List(Player(0))) must throwAn[Exception]
    }

    "not create game when more then 4 players" >> {
      val mockGameDao = mock[GameDao]
      val gameService = new GameService(mockGameDao)

      gameService.createGame((0 to 5).map(Player(_)).toList) must throwAn[Exception](
        "Number of players in game is not from 2 to 4")
    }
  }

}
