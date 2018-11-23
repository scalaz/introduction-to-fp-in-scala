package intro

trait Applicative[F[_]] {

  def point[A](a: => A): F[A]

  def ap[A, B](a: F[A])(f: F[A => B]): F[B]
}

object Applicative {

  /**
   * Convenience for summoning an Applicative instance.
   *
   * usage: Applicative[Int].pure(1)
   */
  def apply[F[_]: Applicative]: Applicative[F] =
    implicitly[Applicative[F]]

  /**
   * Applies a function on the Id applicative.
   *
   * scala> Applicative[Id].ap(Id(1))(Id((i: Int) => i + 1))
   * resX: Id[Int] = Id(2)
   */
  implicit def IdApplicative: Applicative[Id] =
    new Applicative[Id] {
      def point[A](a: => A): Id[A] =
        ???
      def ap[A, B](a: Id[A])(f: Id[A => B]): Id[B] =
        ???
    }

  /**
   * Applies a function on an Optional.
   *
   * scala> Applicative[Option].ap(Some(1))(Some((i: Int) => i + 1))
   * resX: Option[Int] = Some(2)
   */
  implicit def OptionalApplicative: Applicative[Optional] =
    new Applicative[Optional] {
      def point[A](a: => A): Optional[A] =
        ???
      def ap[A, B](a: Optional[A])(f: Optional[A => B]): Optional[B] =
        ???
    }

  /**
   * Applies a function on a List.
   *
   * scala> Applicative[List].ap(List(1, 2, 3))(List((i: Int) => i + 1))
   * resX: List[Int] = List(2, 3, 4)
   *
   * scala> Applicative[List].ap(List(1, 2, 3))(List((i: Int) => i + 1, (i: Int) => i - 1))
   * resX: List[Int] = List(2, 3, 4, 0, 1, 2)
   */
  implicit def ListApplicative: Applicative[List] =
    new Applicative[List] {
      def point[A](a: => A): List[A] =
        ???
      def ap[A, B](a: List[A])(f: List[A => B]): List[B] =
        ???
    }

  /**
   * Apply a binary function in the environment
   *
   * scala> Applicative.liftA2(Option(1), Option(2))((i, j) => i + j)
   * resX: Option[Int] = Some(2)
   *
   * scala> Applicative.liftA2(Option(1), None)((i, j) => i + j)
   * resX: Option[Int] = None
   */
  def liftA2[F[_]: Applicative, A, B, C](a: F[A], b: F[B])(f: (A, B) => C): F[C] =
    ???

  /**
   * Apply a ternary function in the environment
   *
   * scala> Applicative.liftA2(Option(1), Option(2), Option(3))((i, j, k) => i + j + k)
   * resX: Option[Int] = Some(6)
   *
   * scala> Applicative.liftA2(Option(1), None, Option(3))((i, j, k) => i + j + k)
   * resX: Option[Int] = None
   */
  def liftA3[F[_]: Applicative, A, B, C, D](a: F[A], b: F[B], c: F[C])(f: (A, B, C) => D): F[D] =
    ???

  /**
   * Apply a unary function in the environment, also known as `map`
   *
   * scala> Applicative.liftA1(Option(1))(_ + 1)
   * resX: Option[Int] = Some(2)
   *
   * scala> Applicative.liftA1(Option.empty[Int])(_ + 1)
   * resX: Option[Int] = None
   */
  def liftA1[F[_]: Applicative, A, B](a: F[A])(f: A => B): F[B] =
    ???

  /**
   * Apply, discarding the value of the first argument.
   *
   * scala> Applicative.apRight(Option(1), Option(2))
   * resX: List[Int] = Some(2)
   *
   * scala> Applicative.apRight(Option(1), None)
   * resX: List[Int] = None
   *
   * scala> Applicative.apRight(List(1, 2), List(3, 4, 5))
   * resX: List[List] = List(3, 4, 5, 3, 4, 5)
   */
  def apRight[F[_]: Applicative, A, B](a: F[A], b: F[B]): F[B] =
    ???

  /**
   * Apply, discarding the value of the second argument.
   *
   * scala> Applicative.apLeft(Option(1), Option(2))
   * resX: List[Int] = Some(1)
   *
   * scala> Applicative.apLeft(Option(1), None)
   * resX: List[Int] = None
   *
   * scala> Applicative.apLeft(List(1, 2), List(3, 4, 5))
   * resX: List[List] = List(1, 1, 1, 2, 2, 2)
   */
  def apLeft[F[_]: Applicative, A, B](a: F[A], b: F[B]): F[A] =
    ???

  /**
   * Sequences a list of structures to a structure of list.
   *
   * scala> Applicative.sequence(List(Option(1), Option(2), Option(3)))
   * resX: Option[List[Int]] = Some(List(1, 2, 3))
   *
   * scala> Applicative.sequence(List(Option(1), None, Option(3)))
   * resX: Option[List[Int]] = None
   *
   * scala> Applicative.sequence(List[Option[Int]())
   * resX: Option[List[Int]] = Some(List())
   *
   * scala> Applicative.sequence(List(List(1, 2, 3), List(1, 2)))
   * resX: List[List[Int]] = List(List(1, 1), List(1, 2), List(2, 1), List(2, 2), List(3, 1), List(3, 2))
   */
  def sequence[F[_]: Applicative, A](xs: List[F[A]]): F[List[A]] =
    ???

  /**
   * Applies a function to a list, preserving the resulting structure
   *
   * scala> Applicative.sequence(List(1, 2, 3), (i: Int) => Option(i + 1))
   * resX: Option[List[Int]] = Some(List(1, 2, 3))
   */
  def traverse[F[_]: Applicative, A, B](xs: List[A], f: A => F[B]): F[List[B]] =
    ???

  /**
   * Replicate an effect a given number of times.
   *
   * scala> Applicative.replicateA(3, Option(1))
   * resX: Option[List[Int]] = Some(List(1, 1, 1))
   *
   * scala> Applicative.replicateA(3, Option.empty[Int])
   * resX: Option[List[Int]] = None
   *
   * scala> Applicative.replicateA(2, List(1, 2))
   * resX: List[List[Int]] = List(List(1, 1), List(1, 2), List(2, 1), List(2, 2))
   */
  def replicateA[F[_]: Applicative, A](n: Int, xs: F[A]): F[List[A]] =
    ???

  /**
   * Filter a list with a predicate that produces an effect.
   *
   * scala> Applicative.filterM(List(1, 2, 3), (a: Int) => if (a < 9) Some(a % 2 != 0) else None)
   * resX: Option[List[Int]] = Some(List(1, 3))
   *
   * scala> Applicative.filterM(List(1, 2, 3), (a: Int) => if (a < 3) Some(a % 2 != 0) else None)
   * resX: Option[List[Int]] = None
   */
  def filterM[F[_]: Applicative, A](xs: List[A], p: A => F[Boolean]): F[List[A]] =
    ???
}

