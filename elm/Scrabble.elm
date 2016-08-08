module Scrabble exposing (
  game,
  player)

import List exposing (..)
import Set exposing (fromList, size)


type alias Player = {id: Int}


player: Int -> Player
player id =
  { id = id
  }


type alias Game =
  { players: List Player
  }


adequateNumber: List Player -> Bool
adequateNumber candidates =
  let
    lowerBound = 2
    upperBound = 4
    proposedAmount = size <| Set.fromList <| map .id candidates
  in
    lowerBound <= proposedAmount && proposedAmount <= upperBound


type Error = InadequateNumberOfPlayers


game: List Player -> Result Error Game
game players =
  if adequateNumber players then
    Ok (Game players)
  else
    Err InadequateNumberOfPlayers
