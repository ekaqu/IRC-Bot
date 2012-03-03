package com.wamad;

import com.wamad.impl.IRCModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.IOException;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Date: 3/3/12
 * Time: 1:10 PM
 */
@Guice(modules = IRCModule.class)
@Test(groups = "unit")
public class TestIRCClient {
  private final IRCClient client;

  @Inject
  public TestIRCClient(final IRCClient client) {
    this.client = checkNotNull(client);
  }

  @Test(groups = "connect")
  public void testConnect() throws IOException {
    client.init();
  }

  @Test(groups = "join", dependsOnGroups = "connect")
  public void testJoin() {
    client.join("##gibson");
  }
  
  @Test(groups = "msg", dependsOnGroups = "join")
  public void testPingMinh() {
    for (int i = 0; i < 10; i++) {
      client.privateMessage("the_minh_net", "("+i+") this is the test: " + new Date());
    }
  }

  @Test(dependsOnGroups = "msg")
  public void close() throws IOException {
    client.close();
  }

}
