package com.noted {
  package snippet {

    import scala.xml.NodeSeq
    import net.liftweb.util.Helpers
    import scala.xml.Text
    import net.liftweb.http.SHtml
    import Helpers._
    import net.liftweb.http.RequestVar
    import com.noted.model._
    import net.liftweb.http.S
    import net.liftweb.common.Full
    import net.liftweb.common.Empty
    
    class Notes {
      var desc : String = _
      def add(in: NodeSeq): NodeSeq = {

        bind("entry", in,
          "desc" -> SHtml.text(desc, desc = _, "placeholder" -> "what do you want to note?", "class" -> "xxlarge","value"->""),
          "submit" -> SHtml.submit("Note It !", addNotes, "class" -> "btn primary"))
      }

      def listAll(in: NodeSeq): NodeSeq = {
        Note.allNotes.flatMap(note =>
          bind("note", in,
            "desc" -> note.description))
      }

      
      def addNotes() = {

        if(desc.isEmpty()){
          S.error("Please enter a note")
        }else{
        Note.create.description(desc).save
        S.redirectTo("/")
        }
        
      }

      def addnotes() = S.param("desc") match {
        case Full(desc) => {
          Note.create.description(desc).save
        S.redirectTo("/")}
        
        case Empty => S.error("Please enter a note.")
      }
      
    


    }

//    object desc extends RequestVar[String]("")
  }
}

