package scrabble {
  import java.util.UUID

  case class Id[T](id: UUID = UUID.randomUUID)
}

package object scrabble {
  def ensure(cond: Boolean, ex: => Exception) = if (!cond) throw ex
}
