// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait Common {
  def `@`(file: String) = List("@" + file)

  def deprecation = List("-deprecation")

  def encoding(encoding: String) = List("-encoding", encoding)

  def feature = List("-feature")

  def help = List("-help")

  def language(features: String) = List("-language:" + features)

  def nowarn = List("-nowarn")

  def unchecked = List("-unchecked")

  def uniqid = List("-uniqid")

  def verbose = List("-verbose")

  def version = List("-version")

  def X = List("-X")

  def XnoForwarders = List("-Xno-forwarders")

  def XpluginList = List("-Xplugin-list")

  def Xprompt = List("-Xprompt")

  def Y = List("-Y")

  def YdumpClasses = List("-Ydump-classes")

  def YnoGenericSignatures = List("-Yno-generic-signatures")

  def YnoImports = List("-Yno-imports")

  def YnoPredef = List("-Yno-predef")
}
