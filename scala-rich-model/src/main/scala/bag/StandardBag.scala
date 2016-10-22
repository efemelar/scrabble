package scrabble
package bag

import scala.util.Random


class StandardBag private(
  protected val tiles: Bag#Tiles,
  seed: Long = System.nanoTime
) extends Bag {
  private val rnd = new Random(seed)

  def this() = this(StandardBag.initialTiles)

  protected def shuffle(ts: Tiles): Tiles = {
    val shuffled = rnd.shuffle(ts)
    rnd.setSeed(seed)
    shuffled
  }

  protected def cons(ts: Tiles): Bag = new StandardBag(ts, seed)
}

object StandardBag {

  private val initialTiles: List[Tile] =
    List(
      12 -> List('E'                                             )
      ,9 -> List('A', 'I'                                        )
      ,8 -> List('O'                                             )
      ,6 -> List('N', 'R', 'T'                                   )
      ,4 -> List('L', 'S', 'U', 'D'                              )
      ,3 -> List('G'                                             )
      ,2 -> List(' ', 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y')
      ,1 -> List('K', 'J', 'X', 'Q', 'Z'                         )
    ) flatMap { case (times, letters) =>
      letters.flatMap(l => List.fill(times)(Tile(l)))
    }
}
