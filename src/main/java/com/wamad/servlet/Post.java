package com.wamad.servlet;

import com.google.common.base.Throwables;
import com.wamad.IRCClient;
import com.wamad.Message;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String name = request.getParameter("name");
    String say = request.getParameter("say");
    this.client.say(say);

    List<Message> messages = client.getAndClearMessages();
    // convert

//    response.setContentType("application/json");
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println("{'say' : '"+say+"'}");

  }

}
