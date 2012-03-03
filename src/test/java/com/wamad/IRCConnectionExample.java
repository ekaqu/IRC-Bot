package com.wamad;

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
      public void onRegistered() {
        out.println("Registered");
      }

      public void onDisconnected() {
        out.println("Disconnected");
      }

      public void onError(final String msg) {
        out.println("Error: " + msg);
      }

      public void onError(final int num, final String msg) {
        out.println("Error: num ("+num+"), msg ("+msg+")");
      }

      public void onInvite(final String chan, final IRCUser user, final String passiveNick) {
        out.println("invite: chan ("+chan+"), user("+user+"), pass("+passiveNick+")");
      }

      public void onJoin(final String chan, final IRCUser user) {
        out.println("join - " + chan + ", " + user);
      }

      public void onKick(final String chan, final IRCUser user, final String passiveNick, final String msg) {
        out.println("kick - " + chan + ", " + user + ", " + passiveNick + ", " + msg);
      }

      public void onMode(final String chan, final IRCUser user, final IRCModeParser modeParser) {
        out.println("mode - " + chan + ", " + user + ", " + modeParser);
      }

      public void onMode(final IRCUser user, final String passiveNick, final String mode) {
        out.println("mode - " + user + ", " + passiveNick + ", " + mode);
      }

      public void onNick(final IRCUser user, final String newNick) {
        out.println("Nikc - " + user + ", " + newNick);
      }

      public void onNotice(final String target, final IRCUser user, final String msg) {
        out.println("notice - " + target + ", " + user + ", " + msg);
      }

      public void onPart(final String chan, final IRCUser user, final String msg) {
        out.println("part - " + chan + ", " + user + ", " + msg);
      }

      public void onPing(final String ping) {
        out.println("ping - "  + ping);
      }

      public void onPrivmsg(final String target, final IRCUser user, final String msg) {
        out.println("privmsg - " + target + ", " + user + ", " + msg);
      }

      public void onQuit(final IRCUser user, final String msg) {
        out.println("quit - " + user + ", " + msg);
      }

      public void onReply(final int num, final String value, final String msg) {
        out.println("reply - " + num + ", " + value + ", " + msg);
      }

      public void onTopic(final String chan, final IRCUser user, final String topic) {
        out.println("topic - " + chan + ", " + user + ", " + topic);
      }

      public void unknown(final String prefix, final String command, final String middle, final String trailing) {
        out.println("unknown - " + prefix + ", " + command + ", " + middle + ", " + trailing);
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
