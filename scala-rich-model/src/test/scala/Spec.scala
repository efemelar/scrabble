package scala

import org.specs2._, specification.Tables

abstract class Spec extends mutable.Specification
abstract class TableSpec extends Specification with Tables
