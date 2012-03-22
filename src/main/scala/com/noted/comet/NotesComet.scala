package com.noted.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import com.noted.model.Note
import net.liftweb.http.SHtml
import scala.xml.Unparsed
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers
import Helpers._
import net.liftweb.http.js.JsCmds
import SHtml._
import com.noted.snippet.Notes

class NotesComet extends CometActor with CometListener {
  private var notes: List[Note] = List()

  def registerWith = NotesServer

  override def lowPriority = {
    case n: List[Note] => notes = n; reRender()
  }



  def render = "#notes" #> {
    (notes.reverse.map(note => ".desc *" #> note.description &
      ".close [onClick]" #> ajaxInvoke(() => Notes.delete(note.id.toLong))))
  }
}