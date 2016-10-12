package scrabble

import org.specs2._, specification.Tables

class GameStartSpec extends Specification with Tables {

  val John = new Person(Id(java.util.UUID.fromString("0-0-0-0-0")))
  val Jack = new Person(Id(java.util.UUID.fromString("1-1-1-1-1")))

  val invitation = Invitation(John).join(Jack)

  def is = s2"""

    Player who draws the 'A' tile begins the game ${
                                           "Bag" | "Expected turns" |>
      new DeterministicBag(Tile('A'), Tile('C')) !  Seq(John, Jack) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }

    Or the closest tile to 'A' ${
                                           "Bag" | "Expected turns" |>
      new DeterministicBag(Tile('D'), Tile('J')) !  Seq(John, Jack) |
      new DeterministicBag(Tile('K'), Tile('I')) !  Seq(Jack, John) |
      new DeterministicBag(Tile('Z'), Tile('X')) !  Seq(Jack, John) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }

    First one to draw the same tile wins over follower ${
                                             "Bag" | "Expected turns" |>
        new DeterministicBag(Tile('B'), Tile('B')) !  Seq(John, Jack) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }
    """
}
