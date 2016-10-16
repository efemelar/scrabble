package scrabble


class StandardBag private(
  leftTiles: Seq[Tile]
) extends Bag {
  def this() = this(StandardBag.initialTiles)

  def take: (Tile, Bag) = {
    if (leftTiles.isEmpty) throw new NoTilesLeft
    else {
      val i = util.Random.nextInt(leftTiles.size)
      val drawn = leftTiles(i)
      val l = leftTiles.takeWhile(_ != drawn)
      val r = leftTiles.dropWhile(_ != drawn).drop(1)

      (drawn, new StandardBag(l ++ r))
    }
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
