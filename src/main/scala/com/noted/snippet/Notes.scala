package com.noted {
  package snippet {

    import scala.xml.NodeSeq
  import scala.xml.Unparsed
  import net.liftweb.util.Helpers
  import scala.xml.Text
  import net.liftweb.http.SHtml
  import Helpers._
  import net.liftweb.http.RequestVar
  import com.noted.model._
  import net.liftweb.http.S
  import net.liftweb.common._
  import net.liftweb.http.js.JsCmd
    
    class Notes {

      def noteForm(in: NodeSeq): NodeSeq = {

        bind("f", in,
          "desc" -%> SHtml.text(note.description.is,note.description(_)),
          "submit" -%> SHtml.ajaxSubmit("Note It !", add))
      }

      def listAll(in: NodeSeq): NodeSeq = {
        Note.allNotes.reverse.flatMap(note =>
          bind("note", in,
            "desc" -> note.description,
            "deleteLink" -%> SHtml.a(() => delete(note),Unparsed("&times;"))))
      }

      def add(): JsCmd = {
        if (note.description.isEmpty()) {
          S.error("descError","Please enter a note")
        } else {
          note.save
          S.redirectTo("/")
        }

      }
      
      def delete(note: Note):JsCmd = {
        note.delete_!
        S.redirectTo("/")
      }

    }
    
    object note extends RequestVar[Note]({Note.create})

  }
}

