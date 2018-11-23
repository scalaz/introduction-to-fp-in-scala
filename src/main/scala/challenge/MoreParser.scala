package challenge

import intro.Applicative

object MoreParser {

  /**
   * Write a parser that will parse zero or more spaces, tabs and newlines.
   *
   * scala> MoreParser.whitespace.run(" \n\t a")
   *  = Ok(ParseState(a,())
   */
  def whitespace: Parser[Unit] =
    ???

  /**
   * Write a function that parses the given string (fails otherwise).
   *
   * Tip: Use `is` and `traverse`.
   *
   * scala> MoreParser.string("test").run("testing")
   *  = Ok(ParseState(ing,test))
   */
  def string(s: String): Parser[String] =
    ???

  /**
   * Write a function that produces a non-empty list of values coming off the given parser (which must succeed at least
   * once), separated by the second given parser.
   */
  def sepBy1[A, B](ap: Parser[A], bp: Parser[B]): Parser[List[A]] =
    ???

  /**
   * Write a function that produces a list of values coming off the given parser,
   * separated by the second given parser.
   *
   * scala> MoreParser.sepBy(Parser.alpha, Parser.is(' ')).run("1 2 10!")
   *  = Ok(ParseState(!,List(1,2,10))
   *
   * scala> MoreParser.sepBy(Parser.alpha, Parser.is(' ')).run("!")
   *  = Ok(ParseState(!,List())
   */
  def sepBy[A, B](ap: Parser[A], bp: Parser[B]): Parser[List[A]] =
    ???
}
