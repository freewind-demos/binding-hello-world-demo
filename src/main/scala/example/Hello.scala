package example

import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Table
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExport

object Hello {

  @JSExport
  def main(args: Array[String]): Unit = {
    dom.render(document.body, table)
  }

  case class Contact(name: Var[String], email: Var[String])
  val data = Vars.empty[Contact]

  data.value ++= List(
    Contact(Var("Hello"), Var("aaa@mail.com")),
    Contact(Var("World"), Var("bbb@mail.com"))
  )

  @dom
  def table: Binding[Table] = {
    <table border="1" cellPadding="5">
      <thead>
        <tr>
          <th>Name</th>
          <th>E-mail</th>
        </tr>
      </thead>
      <tbody>
        {for (contact <- data) yield {
        <tr>
          <td>
            {contact.name.bind}
          </td>
          <td>
            {contact.email.bind}
          </td>
        </tr>
      }}
      </tbody>
    </table>
  }

}
