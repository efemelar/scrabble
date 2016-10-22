package scrabble
package bag



class BagSpec extends Spec {
  "Standard bag has 100 tiles" >> {
    val bag = new StandardBag
    val (_, emptyBag) = bag.draw(100)

    emptyBag.isEmpty must beTrue
  }

  "Empty bag yields no tiles" >> {
    EmptyBag.draw must throwA[NoTilesLeft]
  }
}

abstract class ConsecutiveDrawsSpec(bag: Bag) extends Spec {
  "Drawing several tiles at once has same effect as one by one" >> {
    val atOnce = bag.draw(3)._1

    val oneByOne = {
      val fst = bag.draw
      val snd = fst._2.draw
      val trd = snd._2.draw

      List(fst, snd, trd).map(_._1)
    }

    atOnce mustEqual oneByOne
  }
}

class StandardBagSpec
  extends ConsecutiveDrawsSpec(new StandardBag)

class DeterministicBagSpec
  extends ConsecutiveDrawsSpec(new DeterministicBag(Tile('A'), Tile('B'), Tile('C')))
