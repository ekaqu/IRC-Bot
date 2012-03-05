/*
 * #%L
 * irc-bot
 * %%
 * Copyright (C) 2012 WAMAD
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.wamad.servlet;

import com.google.common.base.Throwables;
import com.wamad.IRCClient;
import com.wamad.Message;
import org.codehaus.jackson.map.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date: 3/3/12
 * Time: 3:39 PM
 */
@Singleton
public class Post extends HttpServlet {
  private final IRCClient client;
  private final String chatRoom;
  private final ObjectMapper mapper = new ObjectMapper();

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
    String data = mapper.writeValueAsString(messages);

//    response.setContentType("application/json");
    response.setContentType("text/html");
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().println(data);

  }

}
