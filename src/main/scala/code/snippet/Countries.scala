package code
package snippet

import model._
import net.liftweb._
import common._
import util._
import Helpers._

object Countries {
  val ccList:List[Country]  = Nil

  def render = {
    "a"        #> ccList.map( c =>
      "* *"      #> (c.name) //in 2.4 the code worked without a added .is call, fails in 2.5-M1 without the added .get or .is call.
        )
  }

}
