package intro

import Monoid._
import test._

object MonoidSpecification extends Properties("Monoid") {

  include(
    monoidLaws(Monoid.SumMonoid, arbitrary[Int].map(Sum)),
    "monoid: sum"
  )

  include(
    monoidLaws(Monoid.ProductMonoid, arbitrary[Int].map(Product)),
    "monoid: product"
  )

  include(
    monoidLaws(Monoid.MinMonoid, arbitrary[Int].map(Min)),
    "monoid: min"
  )

  include(
    monoidLaws(Monoid.MaxMonoid, arbitrary[Int].map(Max)),
    "monoid: max"
  )

  include(
    monoidLaws(Monoid.FirstMonoid[Int], arbitrary[Option[Int]].map(First(_))),
    "monoid: first"
  )

  include(
    monoidLaws(Monoid.LastMonoid[Int], arbitrary[Option[Int]].map(Last(_))),
    "monoid: last"
  )

  include(
    monoidLaws(Monoid.ListMonoid[Int], arbitrary[List[Int]]),
    "monoid: list"
  )

  include(
    monoidLaws(Monoid.MapMonoid[Int, List[Int]], arbitrary[Map[Int, List[Int]]]),
    "monoid: map"
  )

  property("monoid: foldMap sum") =
    Monoid.foldMap(List(1, 2, 3, 4, 5))(x => Sum(x)) ?= Sum(15)

  property("monoid: foldMap list") =
    Monoid.foldMap(List(1, 2, 3, 4, 5))(x => List(x)) ?= List(1, 2, 3, 4, 5)

  property("monoid: sum") =
    Monoid.sum(List(1, 2, 3, 4, 5).map(Sum)) ?= Sum(15)

  def monoidLaws[A](m: Monoid[A], gen: Gen[A]): Properties =
    new Properties("MonoidLaws") {

      property("left identity") =
        forAll(gen) { a =>
          m.op(m.identity, a) ?= a
        }

      property("right identity") =
        forAll(gen) { a =>
          m.op(a, m.identity) ?= a
        }

      property("associativity") =
        forAll(gen, gen, gen) { (a1, a2, a3) =>
          m.op(a1, m.op(a2, a3)) ?= m.op(m.op(a1, a2), a3)
        }
    }
}
