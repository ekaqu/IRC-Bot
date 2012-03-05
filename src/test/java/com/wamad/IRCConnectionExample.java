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

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import org.schwering.irc.lib.IRCConnection;
import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/**
 * Date: 3/3/12
 * Time: 11:38 AM
 */
public class IRCConnectionExample {
  
  public static void main(String[] args) throws InterruptedException {
    Stopwatch timer = new Stopwatch().start();
    IRCConnection conn = new IRCConnection(
      "irc.freenode.net",
      6667,
      6669,
      null,
      "Foo-bot",
      "Mr-Foobar",
      "foo@bar.com"
    );
    
    final PrintStream out = System.out;

    conn.addIRCEventListener(new IRCEventListener() {
      private Stopwatch stopwatch = new Stopwatch().start();
      public void onRegistered() {
        out.println("Registered");
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onDisconnected() {
        out.println("Disconnected");
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onError(final String msg) {
        out.println("Error: " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onError(final int num, final String msg) {
        out.println("Error: num ("+num+"), msg ("+msg+")");
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onInvite(final String chan, final IRCUser user, final String passiveNick) {
        out.println("invite: chan ("+chan+"), user("+user+"), pass("+passiveNick+")");
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onJoin(final String chan, final IRCUser user) {
        out.println("join - " + chan + ", " + user);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onKick(final String chan, final IRCUser user, final String passiveNick, final String msg) {
        out.println("kick - " + chan + ", " + user + ", " + passiveNick + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onMode(final String chan, final IRCUser user, final IRCModeParser modeParser) {
        out.println("mode - " + chan + ", " + user + ", " + modeParser);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onMode(final IRCUser user, final String passiveNick, final String mode) {
        out.println("mode - " + user + ", " + passiveNick + ", " + mode);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onNick(final IRCUser user, final String newNick) {
        out.println("Nikc - " + user + ", " + newNick);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onNotice(final String target, final IRCUser user, final String msg) {
        out.println("notice - " + target + ", " + user + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onPart(final String chan, final IRCUser user, final String msg) {
        out.println("part - " + chan + ", " + user + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onPing(final String ping) {
        out.println("ping - "  + ping);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onPrivmsg(final String target, final IRCUser user, final String msg) {
        out.println("privmsg - " + target + ", " + user + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onQuit(final IRCUser user, final String msg) {
        out.println("quit - " + user + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onReply(final int num, final String value, final String msg) {
        out.println("reply - " + num + ", " + value + ", " + msg);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void onTopic(final String chan, final IRCUser user, final String topic) {
        out.println("topic - " + chan + ", " + user + ", " + topic);
        out.println("time ("+stopwatch.elapsedMillis());
      }

      public void unknown(final String prefix, final String command, final String middle, final String trailing) {
        out.println("unknown - " + prefix + ", " + command + ", " + middle + ", " + trailing);
        out.println("time ("+stopwatch.elapsedMillis());
      }
    });
    conn.setDaemon(true);
    conn.setColors(false);
    conn.setPong(true);

    try {
      out.println("Connecting");
      conn.connect();
      out.println("Connected?");
      
      conn.doJoin("##gibson");
      
      System.out.println("Time taken: " + timer.elapsedMillis());

      while(true) {
        conn.doPrivmsg("the_minh_net", "YOU ARE THE MINH");
        conn.doPrivmsg("##gibson", "Testing");
        TimeUnit.SECONDS.sleep(5);
      }
      
//      conn.run();
    } catch (IOException e) {
      Throwables.propagate(e);
    }
  }
}
