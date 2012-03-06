package com.noted {
  package model {
    import net.liftweb.mapper.LongKeyedMapper
    import net.liftweb.mapper.IdPK
    import net.liftweb.mapper.LongKeyedMetaMapper
    import net.liftweb.mapper.MappedString
    
    class Note extends LongKeyedMapper[Note] with IdPK {
      def getSingleton = Note

      object description extends MappedString(this, 140)
      
    }

    object Note extends Note with LongKeyedMetaMapper[Note] {
    	def allNotes = Note.findAll();
    }
  } //model

}//package com.noted