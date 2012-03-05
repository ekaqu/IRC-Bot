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
package com.wamad;

import com.wamad.impl.IRCModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    client.join("##gibson-2");
  }
  
  @Test(groups = "msg", dependsOnGroups = "join")
  public void testPingMinh() {
    for (int i = 0; i < 10; i++) {
      client.privateMessage("the_minh_net", "("+i+") this is the test: " + new Date());
    }
  }
  
  @Test(dependsOnGroups = "msg")
  public void messages() throws InterruptedException {
    TimeUnit.SECONDS.sleep(10);
    List<Message> messageList = this.client.getAndClearMessages();
    System.out.println(messageList);
  }

  @Test(dependsOnGroups = "msg")
  public void close() throws IOException {
    client.close();
  }

}
