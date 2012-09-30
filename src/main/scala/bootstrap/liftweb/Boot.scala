package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftweb.squerylrecord.RecordTypeMode._

import code.model._
import code.snippet._

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot extends Loggable {
  def boot {
    
    // where to search snippet
    LiftRules.addToPackages("code")
 
    /*un-comment and switch to db of your liking */
    MySchemaHelper.initSquerylRecordWithInMemoryDB
    //MySchemaHelper.initSquerylRecordWithMySqlDB
    //MySchemaHelper.initSquerylRecordWithPostgresDB

    Props.mode match {
      case Props.RunModes.Development => {
        logger.info("RunMode is DEVELOPMENT")
        /*OBS! recommendation do no use schemify in a production env*/
        if (Props.getBool("db.schemify", false)) {
          MySchemaHelper.dropAndCreateSchema
        }
      }
      case Props.RunModes.Production => logger.info("RunMode is PRODUCTION")
      case _                         => logger.info("RunMode is TEST, PILOT or STAGING")
    }

    // Build SiteMap
    def sitemap = SiteMap(
      Menu.i("Home") / "index",
      Countries.menu,
      ACountry.menu,
      ARegion.menu
     )

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap /*sitemapMutators(sitemap)*/ )

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    // Make a transaction span the whole HTTP request
    S.addAround(new LoanWrapper {
      override def apply[T](f: => T): T =
        {
          inTransaction { f }
        }
    })

    
  }
}
