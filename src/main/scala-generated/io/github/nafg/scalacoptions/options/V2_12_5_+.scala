// AUTO-GENERATED — edit versions.yaml or generator code, then run `sbt generate`

package io.github.nafg.scalacoptions.options

trait V2_12_5_+ extends V2_12_4_+ {
  /**
   * <n>                maximum worker threads for backend
   */
  def YbackendParallelism = List("-Ybackend-parallelism")

  /**
   * <n>               backend threads worker queue size
   */
  def YbackendWorkerQueue = List("-Ybackend-worker-queue")

  def YcacheMacroClassLoader(policy: String) = List("-Ycache-macro-class-loader:" + policy)

  def YcachePluginClassLoader(policy: String) = List("-Ycache-plugin-class-loader:" + policy)

  /**
   * <n>              compression level to use when writing jar files
   */
  def YjarCompressionLevel = List("-Yjar-compression-level")

  /**
   * [:phases]                Run a GC between phases - this allows heap size to be accurate at the expense of more time. Specify a list of phases, or all <phases> (default: _)
   */
  override def YprofileRunGc = List("-Yprofile-run-gc")

  def Ystatistics = List("-Ystatistics")

  def YwarnSelfImplicit = List("-Ywarn-self-implicit")
}
