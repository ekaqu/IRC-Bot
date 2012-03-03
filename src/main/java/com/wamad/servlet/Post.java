package com.wamad.servlet;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date: 3/3/12
 * Time: 3:39 PM
 */
@Singleton
public class Post extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println(request.getParameter("content"));
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("<h1>Hello Servlet</h1>");
    response.getWriter().println("session=" + request.getSession(true).getId());
    //response.getWriter().println("<h1>Hello Servlet</h1>");
    response.sendRedirect("index.html?hello");
  }

  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
    System.out.println(request.getParameter("content"));
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("<h1>Hello Servlet</h1>");
    response.getWriter().println("session=" + request.getSession(true).getId());


    //response.getWriter().println("<h1>Hello Servlet</h1>");
//    response.sendRedirect("index.html?hello");
  }
}
