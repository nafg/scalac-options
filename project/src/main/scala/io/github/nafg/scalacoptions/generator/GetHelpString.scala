package io.github.nafg.scalacoptions.generator

import scala.concurrent.{ExecutionContext, Future, blocking}


object GetHelpString {
  def run(app: String, version: String, flag: String)(implicit ec: ExecutionContext) =
    Future {
      geny.ByteData
        .Chunks(
          blocking {
            os.proc("cs", "launch", "--quiet", s"$app:$version", "--", flag)
              .call(stderr = os.Pipe)
          }
            .chunks
            .map(_.merge)
        )
        .trim()
    }

  def run(version: String, flag: String)(implicit ec: ExecutionContext): Future[String] =
    run(
      if (version.split('.').head.toInt < 3) "scalac" else "scala3-compiler",
      version,
      flag
    )

  val helpFlags = Set("-help", "-X", "-Y")
  val helpFlags213 = helpFlags + "-V" + "-W"

  val versionsAndHelpFlags =
    (0 to 12).map(p => ("2.11." + p) -> helpFlags) ++
      (0 to 13).map(p => ("2.12." + p) -> helpFlags) ++
      (0 to 5).map(p => ("2.13." + p) -> helpFlags213) ++
      List("-RC2").map(p => ("3.0.0" + p) -> helpFlags)

  val versionsAndHelpFlagsForTesting = List(
    "2.11.0" -> helpFlags,
    "2.11.12" -> helpFlags,
    "2.12.0" -> helpFlags,
    "2.12.13" -> helpFlags,
    "2.13.0" -> helpFlags213,
    "2.13.5" -> helpFlags213,
    "3.0.0-RC2" -> helpFlags
  )
}
