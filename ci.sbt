import _root_.io.github.nafg.mergify.dsl._


inThisBuild(
  List(
    homepage                            := Some(uri("https://github.com/nafg/scalac-options")),
    licenses                            := List(
      "Apache-2.0" -> uri("https://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers                          := List(
      Developer(
        "nafg",
        "Naftoli Gugenheim",
        "98384+nafg@users.noreply.github.com",
        uri("https://github.com/nafg")
      )
    ),
    dynverGitDescribeOutput ~=
      (_.map(o =>
        o.copy(dirtySuffix = sbtdynver.GitDirtySuffix(""))
      )),
    dynverSonatypeSnapshots             := true,
    githubWorkflowJavaVersions          := Seq(JavaSpec.zulu("17")),
    githubWorkflowScalaVersions         := githubWorkflowScalaVersions.value.map(_.replaceFirst("\\d+$", "x")),
    githubWorkflowTargetTags ++= Seq("v*"),
    // sbt 2's `test` only reruns tests affected by changes, so CI needs testFull
    githubWorkflowBuild                 := Seq(WorkflowStep.Sbt(List("testFull"), name = Some("Build project"))),
    githubWorkflowPublishTargetBranches := Seq(
      RefPredicate.StartsWith(Ref.Tag("v"))
    ),
    githubWorkflowPublish               := Seq(
      WorkflowStep.Sbt(
        List("ci-release"),
        env = Map(
          "PGP_PASSPHRASE"    -> "${{ secrets.PGP_PASSPHRASE }}",
          "PGP_SECRET"        -> "${{ secrets.PGP_SECRET }}",
          "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
          "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
        )
      )
    )
  )
)

mergifyExtraConditions := Seq(
  (Attr.Author :== "scala-steward") ||
    (Attr.Author :== "nafg-scala-steward[bot]")
)
