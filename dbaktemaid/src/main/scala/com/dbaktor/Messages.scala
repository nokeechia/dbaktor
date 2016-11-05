package com.dbaktor

/**
  * Created by nokee on 05/11/2016.
  */

case class ParseArticle(url: String)

case class ParseHtmlArticle(url: String, htmlString: String)

case class HttpResponse(body: String)

case class ArticleBody(url: String, body: String)


