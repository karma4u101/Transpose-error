package code
package model

import net.liftweb._
import common._
import util._
import org.squeryl.annotations.Column
import net.liftweb.record._
import net.liftweb.record.field._
import net.liftweb.squerylrecord.KeyedRecord
import net.liftweb.squerylrecord.RecordTypeMode._


class Country private() extends Record[Country] with KeyedRecord[Long] with Loggable  {
  def meta = null
  val name = new StringField(this,50)
}
