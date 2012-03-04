package com.wamad.impl;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.wamad.Message;
import org.schwering.irc.lib.IRCEventListener;
import org.schwering.irc.lib.IRCModeParser;
import org.schwering.irc.lib.IRCUser;

import java.util.List;

/**
 * Date: 3/3/12
 * Time: 1:47 PM
 */
public class StateListener implements IRCEventListener {
  private final Stopwatch stopwatch = new Stopwatch().start();

  private boolean serverConnected = false;
  private String mode = null;

  private List<Message> messages = Lists.newLinkedList();

  public boolean isConnectedToServer() {
    return this.serverConnected;
  }

  public String getMode() {
    return this.mode;
  }

  public void onRegistered() {
    log("Registered");
    log("Time since start: " + stopwatch.elapsedMillis());
    serverConnected = true;
  }

  public void onDisconnected() {
    log("Disconnected");
    log("Time since start: " + stopwatch.elapsedMillis());
    serverConnected = false;
  }

  public void onError(final String msg) {
    log("Error: %s", msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onError(final int num, final String msg) {
    log("Error: code %d, msg %s", num, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onInvite(final String chan, final IRCUser user, final String passiveNick) {
    log("Invite: chat %s, user %s, pass %s", chan, user, passiveNick);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onJoin(final String chan, final IRCUser user) {
    log("Join: chat %s, user %s", chan, user);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onKick(final String chan, final IRCUser user, final String passiveNick, final String msg) {
    log("Kick: chat %s, user %s, pass %s, msg %s", chan, user, passiveNick, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }
  public void onMode(final String chan, final IRCUser user, final IRCModeParser modeParser) {
    log("Mode: chat %s, user %s, parser %s", chan, user, modeParser);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onMode(final IRCUser user, final String passiveNick, final String mode) {
    log("Mode: user %s, pass %s, mode %s", user, passiveNick, mode);
    this.mode = mode;
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onNick(final IRCUser user, final String newNick) {
    log("Nick: user %s, newNick %s", user, newNick);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onNotice(final String target, final IRCUser user, final String msg) {
    log("Notice: target %s, user %s, msg %s", target, user, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onPart(final String chan, final IRCUser user, final String msg) {
    log("Part: chat %s, user %s, msg %s", chan, user, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onPing(final String ping) {
    log("Ping: ping %s", ping);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onPrivmsg(final String target, final IRCUser user, final String msg) {
    log("Private Message: target %s, user %s, msg %s", target, user, msg);
    messages.add(new MessageImpl(user, msg, target));
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onQuit(final IRCUser user, final String msg) {
    log("Quit: user %s, msg %s", user, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onReply(final int num, final String value, final String msg) {
    log("Reply: num %d, value %s, msg %s", num, value, msg);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void onTopic(final String chan, final IRCUser user, final String topic) {
    log("Topic: chat %s, user %s, topic %s", chan, user, topic);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  public void unknown(final String prefix, final String command, final String middle, final String trailing) {
    log("Unknown: prefix %s, command %s, middle %s, trailing %s", prefix, command, middle, trailing);
    log("Time since start: " + stopwatch.elapsedMillis());
  }

  private static void log(String msg, Object... args) {
    System.out.println("-- "+ String.format(msg, args)+" --");
  }


  public List<Message> getAndClearMessages() {
    List<Message> msgs = ImmutableList.copyOf(this.messages);
    this.messages.clear();
    return msgs;
  }
}
