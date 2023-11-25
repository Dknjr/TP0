/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ODG
 */
public class Server implements Runnable {
    
    List<Chat> listClients = new ArrayList<>();
    
    public static void main(String[] iren) throws IOException {
        new Thread(new Server()).start();
    }
    @Override
                public void run(){
                    try {
                            ServerSocket ss = new ServerSocket(1234);
                            System.out.println("Demarrage du Serveur");
                            
                            Socket s = ss.accept();
                            Chat c = new Chat(s, "Clara");
                                    c.start();
                                    listClients.add(c);
                           
                    } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public class Chat extends Thread{
              private Socket socket;
              private String pseudo;
              public Chat(Socket s, String p) {
                  this.socket=s;
                  this.pseudo = p;
                  
              }

        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public String getPseudo() {
            return pseudo;
        }

        public void setPseudo(String pseudo) {
            this.pseudo = pseudo;
        }
              
          
    @Override
            public void run() {
                
                  try {
                      //lecture de données
                      InputStream is = socket.getInputStream();
                      InputStreamReader isr = new InputStreamReader(is);
                      BufferedReader br = new BufferedReader(isr);
                      //Ecriture de données
                      OutputStream os = socket.getOutputStream();
                      PrintWriter pw = new PrintWriter(os, true);
                      String IP = socket.getRemoteSocketAddress().toString();
                      pw.println("Bienvenue client IP= " +IP);
                      pw.println("entrez votre pseudo");
                      
                      while(true){
                          String chaine = br.readLine();
                          int n = chaine.length();
                          pw.println("la longueur de la chaine est :" +n);
                      }
                  } catch (IOException ex) {
                      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                  }
}
    }
}
           
                
