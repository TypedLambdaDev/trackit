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
    import net.liftweb.http.js._
    import JsCmds._
    import JsCmd._

    class Notes {

        def noteForm(in: NodeSeq): NodeSeq = {

          bind("f", in,
            "desc" -%> SHtml.text(note.description.is, note.description(_)),
            "submit" -%> SHtml.submit("Note It !", add)) ++ SHtml.hidden(add _) 

        }

        def listAll(in: NodeSeq): NodeSeq = {
          Note.allNotes.reverse.flatMap(note =>
            bind("note", in,
              "desc" -> note.description,
              "deleteLink" -%> SHtml.link("/", () => delete(note), Unparsed("&times;"))))
        }

        def renderList = SHtml.idMemoize{outer => "ul" #> {"li *+" #> Note.allNotes.reverse.map(_.description)}}

        def add(): JsCmd = {
          if (note.description.isEmpty()) {
            S.error("descError", "Please enter a note")
          } else {
            note.save
            SetHtml("notesList",renderList.applyAgain())
           S.error("descError", "note saved")
          }

        }
        
        def render = {
          "#notesList" #> renderList
        }

        def delete(note: Note): JsCmd = {
          note.delete_!
          S.error("descError", "note deletd")
        }
       
    }

    object note extends RequestVar[Note]({ Note.create })

  }
}

