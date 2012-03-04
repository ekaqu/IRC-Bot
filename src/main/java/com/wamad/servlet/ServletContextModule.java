package com.wamad.servlet;

import com.google.inject.servlet.ServletModule;

/**
 * Date: 3/3/12
 * Time: 2:52 PM
 */
public class ServletContextModule extends ServletModule {
  @Override
  protected void configureServlets() {
    System.out.println("Adding mapping");

    serve("/post").with(Post.class);
    serve("/get").with(Post.class);
  }
}
