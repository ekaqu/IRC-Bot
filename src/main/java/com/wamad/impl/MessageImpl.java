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
