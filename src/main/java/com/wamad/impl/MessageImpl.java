package com.wamad.impl;

import com.wamad.Message;
import org.schwering.irc.lib.IRCUser;

/**
* Date: 3/3/12
* Time: 4:48 PM
*/
public class MessageImpl implements Message {
  private final String target, message;
  private final IRCUser user;

  public MessageImpl(final IRCUser user, final String message, final String target) {
    this.user = user;
    this.message = message;
    this.target = target;
  }

  public String getTarget() {
    return target;
  }

  public String getMessage() {
    return message;
  }

  public IRCUser getUser() {
    return user;
  }
}
