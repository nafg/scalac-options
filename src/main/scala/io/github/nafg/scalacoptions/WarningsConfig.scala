package io.github.nafg.scalacoptions

import scala.languageFeature.experimental.macros
import scala.languageFeature.{dynamics, existentials}
import scala.util.matching.Regex

object WarningsConfig {
  sealed trait Category

  object Category {
    case object `deprecation` extends Category

    case object `feature` extends Category

    case object `feature-dynamics` extends Category

    case object `feature-existentials` extends Category

    case object `feature-higher-kinds` extends Category

    case object `feature-implicit-conversions` extends Category

    case object `feature-macros` extends Category

    case object `feature-postfix-ops` extends Category

    case object `feature-reflective-calls` extends Category

    case object `java-source` extends Category

    case object `lint` extends Category

    case object `lint-adapted-args` extends Category

    case object `lint-byname-implicit` extends Category

    case object `lint-constant` extends Category

    case object `lint-delayedinit-select` extends Category

    case object `lint-deprecation` extends Category

    case object `lint-doc-detached` extends Category

    case object `lint-eta-sam` extends Category

    case object `lint-eta-zero` extends Category

    case object `lint-implicit-not-found` extends Category

    case object `lint-inaccessible` extends Category

    case object `lint-infer-any` extends Category

    case object `lint-missing-interpolator,` extends Category

    case object `lint-multiarg-infix` extends Category

    case object `lint-nonlocal-return` extends Category

    case object `lint-nullary-unit` extends Category

    case object `lint-option-implicit` extends Category

    case object `lint-package-object-classes` extends Category

    case object `lint-poly-implicit-overload` extends Category

    case object `lint-private-shadow` extends Category

    case object `lint-recurse-with-default` extends Category

    case object `lint-serial` extends Category

    case object `lint-stars-align,` extends Category

    case object `lint-type-parameter-shadow` extends Category

    case object `lint-unit-specialization` extends Category

    case object `optimizer` extends Category

    case object `other` extends Category

    case object `other-debug` extends Category

    case object `other-match-analysis` extends Category

    case object `other-migration` extends Category

    case object `other-non-cooperative-equals` extends Category

    case object `other-nullary-override` extends Category

    case object `other-pure-statement` extends Category

    case object `other-shadowing` extends Category

    case object `scaladoc` extends Category

    case object `unchecked` extends Category

    case object `unused` extends Category

    case object `unused-imports` extends Category

    case object `unused-locals` extends Category

    case object `unused-nowarn` extends Category

    case object `unused-params` extends Category

    case object `unused-pat-vars` extends Category

    case object `unused-privates` extends Category

    case object `w-flag` extends Category

    case object `w-flag-dead-code` extends Category

    case object `w-flag-extra-implicit` extends Category

    case object `w-flag-numeric-widen` extends Category

    case object `w-flag-self-implicit` extends Category

    case object `w-flag-value-discard` extends Category
  }

  sealed trait Filter {
    override def toString = this match {
      case Filter.Any                => "any"
      case Filter.Category(category) => s"cat=$category"
      case Filter.Message(pattern)   => s"msg=$pattern"
      case Filter.Site(pattern)      => s"site=$pattern"
      case Filter.Source(pattern)    => s"src=$pattern"
      case Filter.Origin(pattern)    => s"origin=$pattern"
      case Filter.Since(op, version) => "since" + op + version
      case Filter.And(left, right)   => s"$left&$right"
    }

    def error = FilterAndAction(this, Action.error)

    def warning = FilterAndAction(this, Action.warning)

    def `warning-summary` = FilterAndAction(this, Action.`warning-summary`)

    def `warning-verbose` = FilterAndAction(this, Action.`warning-verbose`)

    def info = FilterAndAction(this, Action.info)

    def `info-summary` = FilterAndAction(this, Action.`info-summary`)

    def `info-verbose` = FilterAndAction(this, Action.`info-verbose`)

    def silent = FilterAndAction(this, Action.silent)

    def &(that: Filter) = Filter.And(this, that)
  }

  object Filter {
    object Any extends Filter

    case class Category(category: WarningsConfig.Category) extends Filter

    object Deprecation extends Category(WarningsConfig.Category.`deprecation`) {
      def origin(pattern: Regex) = this & Origin(pattern)

      def since(version: String) = this & Since === version

      def sinceBefore(version: String) = this & Since < version

      def sinceAfter(version: String) = this & Since > version
    }

    case class Message(pattern: Regex) extends Filter

    case class Site(pattern: Regex) extends Filter

    case class Source(pattern: Regex) extends Filter

    case class Origin(pattern: Regex) extends Filter

    case class Since(op: String, version: String) extends Filter

    object Since {
      def >(version: String) = Since(">", version)

      def <(version: String) = Since("<", version)

      def ===(version: String) = Since("=", version)
    }

    case class And(left: Filter, right: Filter) extends Filter
  }

  sealed trait Action

  object Action {
    case object error extends Action

    case object warning extends Action

    case object `warning-summary` extends Action

    case object `warning-verbose` extends Action

    case object info extends Action

    case object `info-summary` extends Action

    case object `info-verbose` extends Action

    case object silent extends Action
  }

  case class FilterAndAction(filter: Filter, action: Action) {
    override def toString = s"$filter:$action"
  }

  def buildString(filterAndActions: FilterAndAction*): String =
    filterAndActions.mkString(",")

  def apply(o: options.V2_13_2_+)(
      filterAndActions: FilterAndAction*
  ): List[String] =
    o.Wconf(buildString(filterAndActions: _*))

  def apply(o: options.V2_12_13_+)(
      filterAndActions: FilterAndAction*
  ): List[String] =
    o.Wconf(buildString(filterAndActions: _*))

  def apply(filterAndActions: FilterAndAction*): VersionOptionsFunction =
    VersionOptionsFunction.Partial {
      case o: options.V2_12_13_+ => apply(o)(filterAndActions: _*)
      case o: options.V2_13_2_+  => apply(o)(filterAndActions: _*)
    }
}
