package intro

import test._

object ApplicativeSpecification extends Properties("Applicative") {

  property("applicative: id") =
    Applicative[Id].ap(Id(1))(Id((i: Int) => i + 1)) ?= Id(2)

  property("applicative: option") =
    Applicative[Optional].ap(Optional.ok(1))(Optional.ok((i: Int) => i + 1)) ?= Optional.ok(2)

  property("applicative: list 1") =
    Applicative[List].ap(List(1, 2, 3))(List((i: Int) => i + 1)) ?= List(2, 3, 4)

  property("applicative: list 2") =
    Applicative[List].ap(List(1, 2, 3))(List((i: Int) => i + 1, (i: Int) => i - 1)) ?= List(2, 3, 4, 0, 1, 2)

  property("applicative: liftA2 some some") =
    Applicative.liftA2(Optional.ok(1), Optional.ok(2))((i, j) => i + j) ?= Optional.ok(3)

  property("applicative: liftA2 some none") =
    Applicative.liftA2(Optional.ok(1), Optional.empty)((i, j) => i + j) ?= Optional.empty

  property("applicative: liftA3 some some") =
    Applicative.liftA3(Optional.ok(1), Optional.ok(2), Optional.ok(3))((i, j, k) => i + j + k) ?= Optional.ok(6)

  property("applicative: liftA3 some none") =
    Applicative.liftA3(Optional.ok(1), Optional.empty, Optional.ok(3))((i, j, k) => i + j + k) ?= Optional.empty

  property("applicative: liftA1 some") =
    Applicative.liftA1(Optional.ok(1))(_ + 1) ?= Optional.ok(2)

  property("applicative: liftA1 none") =
    Applicative.liftA1(Optional.empty[Int])(_ + 1) ?= Optional.empty

  property("applicative: apRight some") =
    Applicative.apRight(Optional.ok(1), Optional.ok(2)) ?= Optional.ok(2)

  property("applicative: apRight none") =
    Applicative.apRight(Optional.ok(1), Optional.empty[Int]) ?= Optional.empty

  property("applicative: apRight list") =
    Applicative.apRight(List(1, 2), List(3, 4, 5)) ?= List(3, 4, 5, 3, 4, 5)

  property("applicative: apLeft some") =
    Applicative.apLeft(Optional.ok(1), Optional.ok(2)) ?= Optional.ok(1)

  property("applicative: apLeft none") =
    Applicative.apLeft(Optional.ok(1), Optional.empty[Int]) ?= Optional.empty

  property("applicative: apLeft list") =
    Applicative.apLeft(List(1, 2), List(3, 4, 5)) ?= List(1, 1, 1, 2, 2, 2)

  property("applicative: sequence some") =
    Applicative.sequence(List(Optional.ok(1), Optional.ok(2), Optional.ok(3))) ?= Optional.ok(List(1, 2, 3))

  property("applicative: sequence none") =
    Applicative.sequence(List(Optional.ok(1), Optional.empty[Int], Optional.ok(3))) ?= Optional.empty

  property("applicative: sequence empty") =
    Applicative.sequence(List[Optional[Int]]()) ?= Optional.ok(Nil)

  property("applicative: sequence some") =
    Applicative.sequence(List(List(1, 2, 3), List(1, 2))) ?= List(List(1, 1), List(1, 2), List(2, 1), List(2, 2), List(3, 1), List(3, 2))

  property("applicative: sequence some") =
    Applicative.traverse(List(1, 2, 3), (i: Int) => Optional.ok(i + 1)) ?= Optional.ok(List(2, 3, 4))

  property("applicative: replicateA some") =
    Applicative.replicateA(3, Optional.ok(1)) ?= Optional.ok(List(1, 1, 1))

  property("applicative: replicateA none") =
    Applicative.replicateA(3, Optional.empty[Int]) ?= Optional.empty

  property("applicative: replicateA list") =
    Applicative.replicateA(2, List(1, 2)) ?= List(List(1, 1), List(1, 2), List(2, 1), List(2, 2))

  property("applicative: filterM some") =
    Applicative.filterM(List(1, 2, 3), (a: Int) => if (a < 9) Optional.ok(a % 2 != 0) else Optional.empty[Boolean]) ?= Optional.ok(List(1, 3))

  property("applicative: filterM none") =
    Applicative.filterM(List(1, 2, 3), (a: Int) => if (a < 3) Optional.ok(a % 2 != 0) else Optional.empty[Boolean]) ?= Optional.empty
}
