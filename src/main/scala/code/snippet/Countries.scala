package code
package snippet

import model._
import lib.util._
import net.liftweb._
import common._
import util._
import Helpers._
import sitemap._

object Countries extends Loggable {
  logger.info("snippet.Countries obj start")
  
  val ccList:List[Country]  = Country.getAllList()
  
  lazy val menu = Menu.i("Countries") / "countries"

  def render = { 
    "a"        #> ccList.map( c => 
    "* [href]" #> ACountry.menu.calcHref(c) & 
    "* *"      #> (c.name) //in 2.4 the code worked without a added .is call, fails in 2.5-M1 without the added .get or .is call.
        )
  }
  
  logger.info("snippet.Countries obj stop")
}
