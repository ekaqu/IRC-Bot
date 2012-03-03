package com.wamad;

import com.google.common.io.CharStreams;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Date: 3/3/12
 * Time: 11:09 AM
 */
public class Example {
  public  static void main(String[] args) throws IOException {
    String server = "irc.freenode.net";
    int port = 6667;
    String nick = "irc-bot";
    String login = nick;

    Socket socket = new Socket(server, port);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    System.out.println("loging in with nick " + nick);
    bw.write("NICK " + nick + "\n");
    bw.flush();
//    System.out.println("loging in with login " + login);
//    bw.write("USER " + login + "\n");
//    bw.flush();
    System.out.println("join gibson");
    bw.write("JOIN ##gibson\n");
    bw.flush();
    System.out.println("print");
    bw.write("HELLO WORLD\n");
    bw.flush();
    
    String out = CharStreams.toString(br);
    System.out.println("output so far" + out);
  }
}
