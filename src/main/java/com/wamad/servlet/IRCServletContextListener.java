package com.wamad.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.wamad.impl.IRCModule;

/**
 * Date: 3/3/12
 * Time: 2:51 PM
 */
public class IRCServletContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
      new IRCModule(),
      new ServletContextModule());
  }
}
