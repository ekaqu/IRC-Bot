package com.wamad;

import org.schwering.irc.lib.IRCUser;

/**
 * Date: 3/3/12
 * Time: 4:47 PM
 */
public interface Message {
  public String getTarget();
  public String getMessage();
  public IRCUser getUser();
}
