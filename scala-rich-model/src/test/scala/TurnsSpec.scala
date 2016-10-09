package scrabble

import org.specs2._, specification.Tables

class TurnSpec extends Specification with Tables {

  val John = new Person(Id(java.util.UUID.fromString("0-0-0-0-0")))
  val Jack = new Person(Id(java.util.UUID.fromString("1-1-1-1-1")))

  def is = s2"""
   Player who takes the closest tile to 'A' begins the game ${
    "John's tile"   | "Jack's tile" | "Expected turns" |>
        Tile('A')   !     Tile('C') !   Seq(John, Jack)|
        Tile('D')   !     Tile('J') !   Seq(John, Jack)|
        Tile('K')   !     Tile('I') !   Seq(Jack, John)|
        Tile('Z')   !     Tile('X') !   Seq(Jack, John)|
    {
      (johnsTile, jacksTile, expectedTurns) =>
        Game.order(Set((John, johnsTile), (Jack, jacksTile))) must_== expectedTurns
    }
  }
  """
}
