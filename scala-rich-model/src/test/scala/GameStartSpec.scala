package scrabble

import org.specs2.mutable.Specification


class GameStartSpec extends Specification {
  def individuals(n: Int) = Seq.fill(n)(Player()).toSet

  "A game of scrabble" >> {
    "can be played by 2 to 4 players" >> {
      val games =
        (2 to 4)
        .map(individuals)
        .map(Game.start)

      games must haveSize(3)
    }

    "can't be played solitary" >> {
      Game.start(individuals(1)) must throwAn[InadequateNumberOfPlayers]
    }

    "can't be played by more than 4 players" >> {
      Game.start(individuals(5)) must throwAn[InadequateNumberOfPlayers]
    }
  }

}
