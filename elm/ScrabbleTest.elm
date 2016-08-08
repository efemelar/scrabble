import ElmTest exposing (..)

import Result exposing (Result(Ok, Err))

import Scrabble exposing (Error(InadequateNumberOfPlayers), game, player)

tests : Test
tests =
  suite "Scrabble" [
    test "Can be played with 2 players" (
      let
        players = [ player 1, player 2 ]
      in
        assertEqual (Ok { players = players }) <| game players
    ),
    test "Cannot be played alone" (
      assertEqual (Err InadequateNumberOfPlayers) <| game [ player 1 ]
    ),
    test "Cannot be played with more than 4 players" (
      let
        players = [ player 1, player 2, player 3, player 4, player 5 ]
      in
        assertEqual (Err InadequateNumberOfPlayers) <| game players
    )
  ]

main = runSuiteHtml tests