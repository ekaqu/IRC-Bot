package com.wamad;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Date: 3/3/12
 * Time: 12:42 PM
 */
public interface IRCClient extends Closeable {
  void init() throws IOException;
  void join(String chatName);
  void say(String msg);
  void privateMessage(String user, String msg);
  List<Message> getAndClearMessages();
}
