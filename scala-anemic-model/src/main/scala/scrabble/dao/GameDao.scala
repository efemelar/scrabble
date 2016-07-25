package scrabble.dao

import scrabble.model.{Game, Player, GamePlayer}

case class GameDto(id: Long)


class GameDao {
  private var games = Set.empty[GameDto]
  private var games2players = Set.empty[GamePlayer]
  private var idsIdx = Set.empty[Long]

  private def nextId =
    if (idsIdx.isEmpty) 1
    else idsIdx.max + 1

  def create(game: Game): Game = {
    val dto = GameDto(nextId)
    games += dto
    games2players ++= game.players.map(_.id).map(GamePlayer(dto.id, _))

    game.copy(id = dto.id)
  }
}
