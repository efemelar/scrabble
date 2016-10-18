package scrabble

import bag.DeterministicBag


class GameStartSpec extends TableSpec {

  val John = new Person(Id(java.util.UUID.fromString("0-0-0-0-0")))
  val Jack = new Person(Id(java.util.UUID.fromString("1-1-1-1-1")))

  val invitation = Invitation(John).join(Jack)

  def bag(tiles: Char*) = new DeterministicBag(tiles.map(Tile(_)): _*)

  def is = s2"""
    Player who draws the 'A' tile begins the game ${
              "Bag" | "Expected turns" |>
      bag('A', 'C') !  Seq(John, Jack) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }
    Or the closest tile to 'A' ${
              "Bag" | "Expected turns" |>
      bag('D', 'J') !  Seq(John, Jack) |
      bag('K', 'I') !  Seq(Jack, John) |
      bag('Z', 'X') !  Seq(Jack, John) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }
    But blank tile wins the start of the game ${
              "Bag" | "Expected turns" |>
      bag('A', ' ') !  Seq(Jack, John) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }
    First one to draw the same tile wins over follower ${
                "Bag" | "Expected turns" |>
        bag('B', 'B') !  Seq(John, Jack) |
      {
        (bag, expectedTurns) =>
          Game.order(invitation, bag) must_== expectedTurns
      }
    }
    """
}
