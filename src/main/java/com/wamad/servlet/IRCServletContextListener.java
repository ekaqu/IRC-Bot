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
    System.out.println("Creating Injector");
    return Guice.createInjector(
      new IRCModule(),
      new ServletContextModule());
  }
}
