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

import com.google.common.base.Throwables;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.wamad.IRCClient;
import org.schwering.irc.lib.IRCEventListener;

import java.io.IOException;
import java.util.Properties;

/**
 * Date: 3/3/12
 * Time: 1:01 PM
 */
public class IRCModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(IRCClient.class).to(IRCClientImpl.class);
    bind(IRCEventListener.class).to(IRCEventListenerImpl.class);

    Properties properties = new Properties();
    try {
      properties.load(
        Thread.currentThread().getContextClassLoader().getResourceAsStream("wamad.properties"));
      Names.bindProperties(this.binder(), properties);
    } catch (IOException e) {
      Throwables.propagate(e);
    }
  }
}
