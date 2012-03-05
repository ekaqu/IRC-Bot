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
