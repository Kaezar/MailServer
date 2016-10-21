/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender;
import java.io.*;
import java.net.*;
/**
 * A simple program to send an email with SMTP
 * @author Kyle. Starter code created by Jussi Kangasharju
 */
public class EmailSender {

    /**
     * <h3>The entire program is run from the "main" method. </h3>
     * It first establishes a TCP connection to the mail server by creating a Socket.
     * <br>
     * It then creates a BufferedReader from an InputStream to read a line at a time.
     * <br>
     * It then reads the initial response from the server and throws an exception if it doesn't receive a reply.
     * <br>
     * It then creates a reference to the socket's OutputStream which will be used to send data to the server.
     * <br>
     * It then sends a HELO command to greet the server (throws exception if invalid response).
     * <br>
     * It then sends the "MAIL TO" header information and the source email address (throws exception if invalid response).
     * <br>
     * It then sends the "RCPT TO" header information and the destination email address(throws exception if invalid response).
     * <br>
     * It then sends the "DATA" Command which indicates that the following lines are message data (throws exception if invalid response).
     * <br>
     * It then sends the message data line by line which includes "to", "from", and "subject" header lines which most email servers require to be a part of the message data.
     * <br>
     * When all message data has been sent, it then sends a line with a single period which tells the server that the message is over (throws exception if invalid response).
     * <br>
     * It then sends the "QUIT" command which tells the server to close its connection (throws exception if invalid response).
     * <br>
     * Finally, it closes the socket on the client side, terminating the connection.
     * <br>
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
      // Establish a TCP connection with the mail server.
      Socket socket = new Socket("" + "aspmx.l.google.com", 25);

      // Create a BufferedReader to read a line at a time.
     
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      
      // Read greeting from the server.
  
      String response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("220")) {
         socket.close();
         throw new Exception("220 reply not received from server.");
      }

      // Get a reference to the socket's output stream.
    
      OutputStream os = socket.getOutputStream();

      // Send HELO command and get server response.
     
      String helocommand = "HELO kyle\r\n";
      System.out.print(helocommand);
      os.write(helocommand.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         socket.close();
         throw new Exception("250 reply not received from server.");
      }

      // Send MAIL FROM command.
  
      String mailfromcommand = "MAIL FROM: <Email@email.com>\r\n";
      System.out.print(mailfromcommand);
      os.write(mailfromcommand.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         socket.close();
         throw new Exception("250 reply not received from server.");
      }

      // Send RCPT TO command.
    
      String rcptcommand = "RCPT TO: <Email@email.com>\r\n";
      System.out.print(rcptcommand);
      os.write(rcptcommand.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("250")) {
         socket.close();
         throw new Exception("250 reply not received from server.");
      }

      // Send DATA command.
  
       String datacommand = "DATA\r\n";
      System.out.print(datacommand);
      os.write(datacommand.getBytes("US-ASCII"));
      response = br.readLine();
      System.out.println(response);
      if (!response.startsWith("354")) {
         socket.close();
         throw new Exception("354 reply not received from server.");
      }

      // Send message data.
  
       String msgLine1 = "to: <Email@email.com>\r\n";
    System.out.print(msgLine1);
    os.write(msgLine1.getBytes("US-ASCII"));
    
        String msgLine2 = "from: <Email@email.com>\r\n";
    System.out.print(msgLine2);
    os.write(msgLine2.getBytes("US-ASCII"));
    
        String msgLine3 = "Subject: Test\r\n";
    System.out.print(msgLine3);
    os.write(msgLine3.getBytes("US-ASCII"));
    
      String msgLine4 = "test\r\n";
    System.out.print(msgLine4);
    os.write(msgLine4.getBytes("US-ASCII"));
    
      // End with line with a single period.
   
      String msgLine5 = ".\r\n";
    System.out.print(msgLine5);
    os.write(msgLine5.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);
    if (!response.startsWith("250")) {
        socket.close();
        throw new Exception("250 reply not received from server.");
    }

      // Send QUIT command.
       String commandQUIT = "QUIT\r\n";
    System.out.print(commandQUIT);
    os.write(commandQUIT.getBytes("US-ASCII"));
    response = br.readLine();
    System.out.println(response);
    if (!response.startsWith("221")) {
        socket.close();
        throw new Exception("221 reply not received from server.");
    }
    // Close the socket
    socket.close();
    }
    
}
