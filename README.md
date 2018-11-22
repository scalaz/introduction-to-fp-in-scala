# Introduction to FP in Scala [![Build Status](https://travis-ci.org/ambiata/introduction-to-fp-in-scala.svg)](https://travis-ci.org/ambiata/introduction-to-fp-in-scala)
This is the base project for the workshop.

__note__: please test your environment before you arrive so we can get started quickly on the day.


## Getting started

Before you attend you will need to get a few things
ready and ensure everything is setup properly. `sbt`
is going to do all the heavy lifting though, so
hopefully it is all straight forward, if not, send
us an email via <mark@hibberd.id.au>.


Pre-requisites.

 1. A valid install of java 6+
 2. git
 3. **if you are windows only** install sbt using the [msi installer](http://scalasbt.artifactoryonline.com/scalasbt/sbt-native-packages/org/scala-sbt/sbt/0.13.0/sbt.msi)


Getting scala and validating your environment:

    git clone https://github.com/markhibberd/introduction-to-fp-in-scala.git
    cd introduction-to-fp-in-scala
    ./sbt "test:compile"
    # For windows users just use your installed `sbt` directly without the `./`

For either platform this may take a few minutes. It will:

 1. Download the sbt build tool.
 2. Download the required versions of scala.
 3. Compile the main and test source code.

You should see no errors and an exit code of 0.


## Running the tests

If you want to run all the tests once then you can simple run

    ./sbt test

However this can be tedious, and requires waiting for SBT to start and for _all_ the tests to run
(which might get noisy if they fail).
It is recommend to start the SBT console and run specific tests in a loop,
which is handy when working on a particular exercise:


    # Start the SBT terminal once, and then subsequent commands are run within that program
    # Be careful not to type Ctrl^C as this kills the entire session
    ./sbt

    # Run the tests from a package containing the name "Id"
    > testOnly "*Id*"

    # Add a ~ for starting a watch daemon to recompile and re-run tests when files are changed
    > ~testOnly "*Result*"


## Working with scala.

Any good text editor will be sufficient for the course. If you
prefer an IDE, you can use the eclipse based scala-ide,
intellij, or emacs with ensime. There are commented out lines
in `project/plugins.sbt` that will help you get started:

You can generate project files for eclipse with (after uncommenting sbteclipse-plugin plugin):

    ./sbt eclipse

If you want to use ensime (after uncommenting ensime-sbt-cmd):

    ./sbt 'ensime generate'

You can import the project files for intellij if you have the latest
[scala plugin](https://plugins.jetbrains.com/plugin/1347-scala) enabled.

Just note that if you choose eclipse or intellij, have a
backup texteditor as well, because there won't be enough
time to debug any editor issues.


## The Plan

There is about two weeks worth of material available, people with
different backgrounds will progress through at different rates.


## Answers

If get get stuck there is an [answers](src/main/scala/answers) folder where you can look for
help or inspiration. Don't feel bad if you need to peek. ;-)


### Just enough scala

 - [src/main/scala/intro/Scala.scala](src/main/scala/intro/Scala.scala)
 - [src/main/scala/intro/CheatSheet.scala](src/main/scala/intro/CheatSheet.scala)


### What is functional programming?

 - [src/main/scala/intro/Intro.scala](src/main/scala/intro/Intro.scala)


### Introduction to data structures and higher order functions

#### Examples

 - [src/main/scala/intro/Id.scala](src/main/scala/intro/Id.scala)
 - [src/main/scala/intro/Optional.scala](src/main/scala/intro/Optional.scala)

#### Exercises

 - Lists - [src/main/scala/intro/List.scala](src/main/scala/intro/List.scala)
 - Errors without exceptions - [src/main/scala/intro/Result.scala](src/main/scala/intro/Result.scala)


### Intro to Type Classes

#### Example

 - [src/main/scala/intro/Equal.scala](src/main/scala/intro/Equal.scala)

#### Exercises

NOTE: `Applicative` can often prove more tricky than `Monad`, feel free to flip the order if you get stuck.

 - [src/main/scala/intro/Functor.scala](src/main/scala/intro/Functor.scala)
 - [src/main/scala/intro/Applicative.scala](src/main/scala/intro/Applicative.scala)
 - [src/main/scala/intro/Monad.scala](src/main/scala/intro/Monad.scala)

### Algebra for fun and profit

 - [src/main/scala/intro/Monoid.scala](src/main/scala/intro/Monoid.scala)

### Parsers

 - [src/main/scala/challenge/Parser.scala](src/main/scala/challenge/Parser.scala)

 If you enjoyed parsing and want some more then writing a json parser is also fun:

 - [src/main/scala/challenge/MoreParser.scala](src/main/scala/challenge/MoreParser.scala)
 - [src/main/scala/challenge/JsonParser.scala](src/main/scala/challenge/JsonParser.scala)

### Zippers

 - [src/main/scala/challenge/Zippers.scala](src/main/scala/challenge/Zippers.scala)

### Patterns in Types

 - [src/main/scala/patterns/Reader.scala](src/main/scala/patterns/Reader.scala)
 - [src/main/scala/patterns/Writer.scala](src/main/scala/patterns/Writer.scala)
 - [src/main/scala/patterns/State.scala](src/main/scala/patterns/State.scala)
 - [src/main/scala/patterns/Http.scala](src/main/scala/patterns/Http.scala)
 - [src/main/scala/patterns/ReaderT.scala](src/main/scala/patterns/ReaderT.scala)
 - [src/main/scala/patterns/WriterT.scala](src/main/scala/patterns/WriterT.scala)
 - [src/main/scala/patterns/StateT.scala](src/main/scala/patterns/StateT.scala)
 - [src/main/scala/patterns/MonadTrans.scala](src/main/scala/patterns/MonadTrans.scala)
 - [src/main/scala/patterns/HttpT.scala](src/main/scala/patterns/HttpT.scala)
