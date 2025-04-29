/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author yassinesalihi
 */
public class Client {
    
    public static void main(String[] args) {
        
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur démarré, en attente d'une connexion...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté.");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                   
            String message;
            while((message = in.readLine()) != null) {  
                if(message.equals("exit")){
                    System.out.println("byyyyye!");
                    break;
                }
                
             System.out.println("[Client] : " + message);
             out.println("Message reçu : " + message);
                
            }
            
            System.out.println("connexion fermée");
                    
        } catch (Exception e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}
