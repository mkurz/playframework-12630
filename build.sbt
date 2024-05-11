//import de.johoop.jacoco4sbt._

//name := """product-code-api"""
name := """pricing_prcsetup_v1_svc"""

//version := "11.0.1"

// enablePlugins(SonarRunnerPlugin)

// addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")

//sonarProperties ++= Map(
//  "sonar.host.url" -> "http://10.38.5.143:9000",
//  "sonar.java.source" -> "1.8",
//  "sonar.junit.reportsPath" -> "target/test-reports",
//  "sonar.jacoco.reportPaths" -> "target/scala-2.11/jacoco/jacoco.exec"
//)

// coverageEnabled := "true"

val playPac4jVersion = "3.0.0"
val pac4jVersion = "5.7.2"
//val playVersion = "2.6.6"
val playVersion = "2.9.2"


/*
val artifHost = sys.env("ARTIF_HOST")
val artifPort = sys.env("ARTIF_PORT")
val artifRepokey = sys.env("ARTIF_REPOKEY")
val artifUser = sys.env("ARTIF_USER")
val artifPassword = sys.env("ARTIF_PASSWORD")


publishArtifact in (Compile, packageBin) := false
publishArtifact in (Compile, packageDoc) := false
publishArtifact in (Compile, packageSrc) := false

val packageZip = taskKey[File]("package-zip")
packageZip := (baseDirectory in Compile).value / "target" / "universal" / (name.value + "-" + version.value + ".zip")
artifact in (Universal, packageZip) ~= { (art:Artifact) => art.copy(`type` = "zip", extension = "zip") }
addArtifact(artifact in (Universal, packageZip), packageZip in Universal)

publishTo := Some("Artifactory Realm" at "http://"+artifHost+":"+artifPort+"/artifactory/"+artifRepokey)
credentials += Credentials("Artifactory Realm", artifHost, artifUser, artifPassword)*/

lazy val root = project in file(".") enablePlugins(PlayJava) configs(ITest) settings( inConfig(ITest)(Defaults.testSettings) : _*)
lazy val ITest = config("it") extend(Test)

//scalaVersion := "2.11.8"
scalaVersion := "2.13.13"
//dependencyOverrides += "com.typesafe.play" % "scalaVersion" % "2.13.13"
//crossScalaVersions := Seq("2.13.13")
//scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked")

//jacoco.settings

// Jacoco code coverage metrics thresholds
//jacoco.thresholds in jacoco.Config := Thresholds(instruction = 45, method = 55, branch = 40, complexity = 50, line = 55, clazz = 65)

//parallelExecution in jacoco.Config := false
//crossTarget in jacoco.Config := baseDirectory.value / "test-results"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

javaOptions in test += "-Dconfig.file=conf/application-test.conf"

/*jacoco.excludes in jacoco.Config := Seq("views*",
  "*Routes*",
  "controller*routes*",
  "controller*Reverse*",
  "controller*javascript*",
  "controller*ref*",
  "*javascript*",
  "*ReverseUploaderController*",
  "*ReverseCurrencyController*",
  "*ReverseFundsInController*",
  "*ReverseLoyaltyController*",
  "*ReverseCountryController*",
  "*ReverseOptionsController*",
  "*ReverseFundsOutController*",
  "*ReverseUserController*",
  "*ReverseProductCodeController*",
  "*ReverseCorridorListController*",
  "*ReverseLocationListController*",
  "*ReverseStaticDataController",
  "*ReverseAlertController",
  "*ReverseFeeSchemaController",
  "*ReverseFXSpreadSchemaController",
  "*ReversePricingSchemaController",
  "*ReversePricingProfileController",
  "*ReverseExportController",
  "*UploaderController*",
  "*UploaderReq*",
  "*uploader*actor*",
  "*uploader*service*",
  "*S3UploaderServiceImpl*",
  "*S3Actor*",
  "*routes*"
)*/

Defaults.itSettings

sourceDirectory in ITest := baseDirectory.value / "integration"
javaSource in ITest := baseDirectory.value / "integration"

javaOptions in ITest += "-Dconfig.file=conf/application-it.conf"
val akkaVersion = "2.8.1-M1"

libraryDependencies ++= Seq(
//  "com.google.inject" % "guice" % "4.2.3",
  "com.amazonaws" % "aws-java-sdk" % "1.11.46",
  "commons-io" % "commons-io" % "2.4",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.13.5",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.5",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.13.5",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-csv" % "2.13.5",
//  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.17" % "test",
//  "com.typesafe.akka" % "akka-testkit_2.13" % "2.6.21" % "test",
  javaWs,
//  cache,
//  "net.sf.ehcache" % "ehcache" % "2.10.9.2",
  "com.typesafe.play" %% "play-cache" % "2.8.16",
  filters,
  "org.mockito" % "mockito-core" % "2.8.9",
  "com.couchbase.client" % "java-client" % "3.3.1",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.typesafe.play" % "play-mailer_2.13" % "7.0.2",
  "io.vavr" % "vavr" % "0.9.0",
  "io.vavr" % "vavr-jackson" % "0.9.0",
  "io.vavr" % "vavr-match" % "0.9.0",
  "com.google.code.gson" % "gson" % "2.8.1" % "it",
  "junit" % "junit" % "4.12" % "it",
//  "com.typesafe.play" % "play-test_2.13" % "2.5.14" % "it",
  "com.typesafe.play" % "play_2.13" % "2.8.16" % "it",
  "io.reactivex" % "rxjava" % "1.3.0" % "it",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-csv" % "2.9.0",
  "io.vavr" % "vavr-match" % "0.9.0",
  "org.pac4j" % "play-pac4j" % playPac4jVersion,
  "org.pac4j" % "pac4j-oidc" % pac4jVersion exclude("commons-io" , "commons-io"),
  "commons-io" % "commons-io" % "2.4",
  "com.googlecode.jsontoken" % "jsontoken" % "1.0",
  "com.google.guava" % "guava" % "31.1-jre",
  "org.mongodb" % "bson" % "3.3.0",
  "org.apache.tika" % "tika-core" % "1.18",
  "org.apache.tika" % "tika-parsers" % "1.18",
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j"  % akkaVersion,
  // Only if you are using Akka Testkit
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion
)
///val akkaVersion = "2.6.21"
//
//libraryDependencies ++= Seq(
//  ehcache
//)
//libraryDependencies ++= Seq(
//  cacheApi
//)

//libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
//libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion

//libraryDependencies ++= Seq(
//  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
//  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
//  "com.typesafe.akka" %% "akka-slf4j"  % akkaVersion,
//  // Only if you are using Akka Testkit
//  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
//)

Test / javaOptions ++= Seq(
  "--add-exports=java.base/sun.security.x509=ALL-UNNAMED",
  "--add-opens=java.base/sun.security.ssl=ALL-UNNAMED",
  "--add-opens java.base/java.lang=ALL-UNNAMED"
)

//libraryDependencies += "org.playframework" %% "play-cache" % "3.0.0"
libraryDependencies += guice
libraryDependencies += caffeine
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.13.5"
//val playVersionPlayTest = play.core.PlayVersion.current
//val TestDeps = Seq(
//  "org.playframework"       %% "play-test"           % playVersionPlayTest     % Test
//)

//libraryDependencies += "org.ehcache" % "ehcache" % "3.10.6"

//libraryDependencies += "com.typesafe.play" %% "play-async-cache" % "3.0.2"