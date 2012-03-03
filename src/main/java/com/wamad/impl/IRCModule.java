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
