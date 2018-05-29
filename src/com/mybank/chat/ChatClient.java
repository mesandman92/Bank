/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mybank.chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author uladzimir
 */
public class ChatClient {

    private final JTextArea output;
    private final JTextField input;
    private final JButton sendButton;
    private final JButton quitButton;

    private Socket connection = null;
    private BufferedReader serverIn = null;
    private PrintStream serverOut = null;

    private void doConnect() {
        String serverIP = System.getProperty("serverIP", "127.0.0.1");
        String serverPort = System.getProperty("serverPort", "2000");

        try {
            connection = new Socket(serverIP, Integer.parseInt(serverPort));
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            serverIn = new BufferedReader(isr);
            serverOut = new PrintStream(connection.getOutputStream());
            Thread t = new Thread(new RemoteReader());
            t.start();
        } catch (IOException | NumberFormatException e) {
            System.err.println("ERROR: unable to connect to server!");
            e.printStackTrace();
        }
    }

    public ChatClient() {
        this.output = new JTextArea(10, 50);
        this.input = new JTextField(50);
        this.sendButton = new JButton("Send");
        this.quitButton = new JButton("Quite");
    }

    public void launchFrame() {
        JFrame frame = new JFrame("Bank Chat Room");
        frame.setLayout(new BorderLayout());

        frame.add(output, BorderLayout.CENTER);
        frame.add(input, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(sendButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel, BorderLayout.EAST);
        output.setEditable(false);

        input.addActionListener(new SendHandler());
        sendButton.addActionListener(new SendHandler());
        quitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);

        doConnect();
    }

    public static void main(String[] args) {
        ChatClient myChat = new ChatClient();
        myChat.launchFrame();
    }

    private class RemoteReader implements Runnable {

        @Override
        public void run() {
            try {
                while(true){
                    String nextLine =serverIn.readLine();
                    output.append(nextLine+"\n");
                }
            } catch (IOException e) {
                System.err.println("ERROR: can't read from the server!");
                e.printStackTrace();
            }
        }

    }

    private class SendHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = input.getText();
            //output.append(message + "\n");
            serverOut.print("New message: " + message + "\n");
            input.setText("");
        }
    }

}
