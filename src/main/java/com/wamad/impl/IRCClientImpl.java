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
package com.wamad.impl;

import com.wamad.IRCClient;
import com.wamad.Message;
import org.schwering.irc.lib.IRCConnection;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkState;

/**
 * Date: 3/3/12
 * Time: 12:48 PM
 */
public class IRCClientImpl implements IRCClient {
  private final String server, nick, login, email;
  private final int minPort, maxPort;

  private String currentChat;
  private IRCConnection conn;
  private StateListener state;

  @Inject
  public IRCClientImpl(
    @Named("wamad.server.name") final String server,
    @Named("wamad.server.maxPort") final int maxPort,
    @Named("wamad.server.minPort") final int minPort,
    @Named("wamad.user.email") final String email,
    @Named("wamad.user.login") final String login,
    @Named("wamad.user.nick") final String nick) {
    this.maxPort = maxPort;
    this.minPort = minPort;
    this.email = email;
    this.login = login;
    this.nick = nick;
    this.server = server;
  }

  public void init() throws IOException {
    checkState(conn == null, "Can not call init twice");

    conn = new IRCConnection(server, minPort, maxPort, null, nick,
      login, email);

    this.state = new StateListener();
    conn.addIRCEventListener(state);

    conn.setDaemon(true);
    conn.setColors(false);
    conn.setPong(true);

    conn.connect();
    while (! conn.isConnected() || ! state.isConnectedToServer()) {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
    }
  }

  public void join(final String chatName) {
    this.currentChat = chatName;
    conn.doJoin(currentChat);
    while(this.state.getMode() == null) {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
    }
  }

  public void say(final String msg) {
    conn.doPrivmsg(currentChat, msg);
  }

  public void privateMessage(final String user, final String msg) {
    conn.doPrivmsg(user, msg);
  }

  @Override
  public List<Message> getAndClearMessages() {
    return state.getAndClearMessages();
  }

  public void close() throws IOException {
    conn.close();
  }
}
