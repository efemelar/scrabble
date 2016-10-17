package scrabble


class StandardBag private(
  tiles: Seq[Tile]
) extends Bag {
  def this() = this(StandardBag.initialTiles)

  private def excluding(t: Tile): Seq[Tile] =
    tiles.takeWhile(_ != t) ++ tiles.dropWhile(_ != t).drop(1)

  def take: (Tile, Bag) = {
    val drawn = tiles(util.Random.nextInt(tiles.size))
    val lessTiles = excluding(drawn)
    val smallerBag =
      if (lessTiles.isEmpty) EmptyBag
      else new StandardBag(lessTiles)

    (drawn, smallerBag)
  }
}

object StandardBag {

  private val initialTiles: Seq[Tile] =
    Seq(
      12 -> Seq('E'                                             )
      ,9 -> Seq('A', 'I'                                        )
      ,8 -> Seq('O'                                             )
      ,6 -> Seq('N', 'R', 'T'                                   )
      ,4 -> Seq('L', 'S', 'U', 'D'                              )
      ,3 -> Seq('G'                                             )
      ,2 -> Seq(' ', 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y')
      ,1 -> Seq('K', 'J', 'X', 'Q', 'Z'                         )
    ) flatMap { case (times, letters) =>
      letters.flatMap(l => Seq.fill(times)(Tile(l)))
    }
}
