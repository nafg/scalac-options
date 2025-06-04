import _root_.io.github.nafg.mergify.dsl._


inThisBuild(
  List(
    homepage := Some(url("https://github.com/nafg/scalac-options")),
    licenses := List(
      "Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "nafg",
        "Naftoli Gugenheim",
        "98384+nafg@users.noreply.github.com",
        url("https://github.com/nafg")
      )
    ),
    dynverGitDescribeOutput ~= (_.map(o =>
      o.copy(dirtySuffix = sbtdynver.GitDirtySuffix(""))
    )),
    dynverSonatypeSnapshots := true,
    githubWorkflowScalaVersions := githubWorkflowScalaVersions.value.map(_.replaceFirst("\\d+$", "x")),
    githubWorkflowTargetTags ++= Seq("v*"),
    githubWorkflowPublishTargetBranches := Seq(
      RefPredicate.StartsWith(Ref.Tag("v"))
    ),
    githubWorkflowPublish := Seq(
      WorkflowStep.Sbt(
        List("ci-release"),
        env = Map(
          "PGP_PASSPHRASE" -> "${{ secrets.PGP_PASSPHRASE }}",
          "PGP_SECRET" -> "${{ secrets.PGP_SECRET }}",
          "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
          "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
        )
      )
    )
  )
)

sonatypeProfileName := "io.github.nafg"

mergifyExtraConditions := Seq(
  (Attr.Author :== "scala-steward") ||
    (Attr.Author :== "nafg-scala-steward[bot]")
)
