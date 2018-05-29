package com.mybank.chat.server;

import java.io.*;
import java.net.*;

class Connection implements Runnable {

  ChatServer server = null;
  private Socket communicationSocket = null;
  private OutputStreamWriter out = null;
  private BufferedReader in = null;

  public Connection(ChatServer server, Socket s) {
    this.server = server;
    this.communicationSocket = s;
  }       

  public void sendMessage(String message) {
    try {
      out.write(message);
      out.flush();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    OutputStream socketOutput = null;
    InputStream socketInput = null;
    String magic = server.getMagicPassphrase();

    try {
      socketOutput = communicationSocket.getOutputStream();
      out = new OutputStreamWriter(socketOutput);
      socketInput = communicationSocket.getInputStream();
      in = new BufferedReader(new InputStreamReader(socketInput));

        
      InetAddress address = communicationSocket.getInetAddress();
      String hostname = address.getHostName();

      String welcome = 
	"Connection made from host: "+hostname+"\nEverybody say hello";
      String student = server.getStudentName(hostname);
      if (student != null) welcome += " to "+student;
      welcome+="!\n";
      server.sendToAllClients(welcome);
      System.out.println("Connection made "+student+"@"+hostname);
      sendMessage("Welcome "+student+" the passphrase is "+magic+"\n");
      String input = null;

      while ((input = in.readLine()) != null) {
	if (input.contains(magic)) {
	  server.playMagicSound();
	  sendMessage("Congratulations "+student+" you sent the passphrase!\n");
	  System.out.println(student+" sent the passphrase!");
	} else {
	  server.sendToAllClients(input+"\n");
	}
      }
    } catch(IOException e) {
      e.printStackTrace(System.err);
    } finally {
      try {
	if (in != null) in.close();
	if (out != null) out.close();
	communicationSocket.close();
      }  catch(IOException e) {
	e.printStackTrace();
      }
      server.closeConnection(this);
    }
  }
}
