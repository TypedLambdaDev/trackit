package com.noted.comet
import net.liftweb.http.ListenerManager
import net.liftweb.actor.LiftActor
import com.noted.model._
import net.liftweb.http.RequestVar
object NotesServer extends LiftActor with ListenerManager {
  
  def createUpdate =  Note.allNotes
  
  
  override def lowPriority = {
    case note: Note => note.save ; updateListeners()
    case s: String =>  updateListeners()
  }

}