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
    
    class Notes {

      def noteForm(in: NodeSeq): NodeSeq = {

        bind("entry", in,
          "desc" -%> SHtml.text(note.description.is,note.description(_)),
          "submit" -> SHtml.submit("Note It !", add, "class" -> "btn primary"))
      }

      def listAll(in: NodeSeq): NodeSeq = {
        Note.allNotes.reverse.flatMap(note =>
          bind("note", in,
            "desc" -> note.description,
            "deleteLink" -%> SHtml.link("/", () => delete(note),Unparsed("&times;"))))
      }

      def add() = {
        if (note.description.isEmpty()) {
          S.error("Please enter a note.")
        } else {
          note.save
          S.redirectTo("/")
        }

      }
      
      def delete(note: Note) = {
        note.delete_!
        S.redirectTo("/")
      }

    }
    
    object note extends RequestVar[Note]({Note.create})

  }
}

