package com.wamad.servlet;

import com.google.common.base.Throwables;
import com.wamad.IRCClient;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.inject.Inject;
import javax.inject.Named;
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
  private final IRCClient client;
  private final String chatRoom;

  @Inject
  public Post(final IRCClient client, @Named("wamad.irc.chat") final String chatRoom) {
    this.client = client;
    this.chatRoom = chatRoom;
  }

  @Override
  public void init() throws ServletException {
    super.init();
    try {
      this.client.init();
      this.client.join(this.chatRoom);
    } catch (IOException e) {
      Throwables.propagate(e);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println(request.getParameter("content"));
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("<h1>Hello Servlet</h1>");
    response.getWriter().println("session=" + request.getSession(true).getId());
    //response.getWriter().println("<h1>Hello Servlet</h1>");
//    response.sendRedirect("index.html?hello");
  }

}
