package scrabble

import org.specs2.mutable.Specification


class InvitationSpec extends Specification {
  private def individuals(n: Int) = Seq.fill(n)(Person())
  private val bag = new DeterministicBag()

  "A game of scrabble" >> {
    "can be played by 2 to 4 players" >> {
      val invitation =
        (2 to 4)
        .map(individuals)
        .map(is => is.tail.foldLeft(Invitation(is.head))(_ join _))

      invitation must haveSize(3)
    }

    "can't be played solitary" >> {
      Invitation(Person()).startGame(bag) must throwAn[InadequateNumberOfPlayers]
    }

    "can't be played by more than 4 players" >> {
      val first = Person()
      val others = individuals(4)

      others.foldLeft(Invitation(Person()))(_ join _).startGame(bag) must throwAn[InadequateNumberOfPlayers]
    }
  }

}
