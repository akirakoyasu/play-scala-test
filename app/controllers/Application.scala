package controllers

import java.nio.charset.StandardCharsets

import play.api._
import play.api.libs.iteratee.Enumerator
import play.api.mvc._
import play.libs.Json

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def echo = Action { request =>
    Ok("Got request [" + request + "]")
  }

  def yet = TODO

  def hello(name: String) = Action {
    Ok("Hello, %s!!".format(name))
  }

  def index2 = Action {
    Result(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      body = Enumerator("Hello world!".getBytes(StandardCharsets.UTF_8))
    )
  }

  def ok = Action {
    Ok("Hello world!")
  }
  def json = Action {
    Ok(Json.toJson(Seq(1, 2, 3, 4)).toString).as(JSON)
  }
  def notFound = Action {
    NotFound
  }
  def pageNotFound = Action {
    NotFound(<h1>Page not found</h1>).as(HTML)
  }
  //  val badRequest = BadRequest(views.html.helper.form(formWithErrors))
  def oops = Action {
    InternalServerError("Oops")
  }
  def anyStatus = Action {
    Status(488)("Strange response type")
  }

  def r = Action {
    Redirect(routes.Application.hello("John"))
  }

  def v(version: Option[String]) = Action {
    Ok(version.getOrElse("None!"))
  }
}