package com.noted.snippet
import net.liftweb.http._
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmd
import scala.xml.NodeSeq

object IdMemoize {

  def render =
    "div" #> SHtml.idMemoize(outer =>
      // redraw the whole div when this button is pressed
     // "#refresh_all [onclick]" #> SHtml.ajaxInvoke(outer.setHtml _) &
        // deal with the "one" div
        "#one" #> SHtml.idMemoize(one =>
          "span *+" #> now.toString & // display the time
            "button [onclick]" #> SHtml.ajaxInvoke(()=>add(one)) & // redraw
        // deal with the "two" div
        "#two" #> SHtml.idMemoize(two => // the "two" div
          "ul" #> {
            // display a bunch of items
            "li *+" #> (0 to randomInt(6)).map(_.toString)
          }))) 
            // update the "two" div on button press
      //      "button [onclick]" #> SHtml.ajaxInvoke(two.setHtml _)))

          def add(imt: IdMemoizeTransform):JsCmd ={
    		imt.setHtml()
    }

}