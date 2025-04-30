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
        // ********* localhost and port *************
        try(Socket socket = new Socket("localhost", 1234) ) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
             
            while(true){
                System.out.print("Vous : ");
                String message = userInput.readLine();
                out.println(message);
                
                String response = in.readLine();
                System.out.println("Serveur : " + response);
                
                if(message.equals("exit")){
                    System.out.println("byyyyye!");
                    break;
                }
            }
            
            
            System.out.println("Déconnexion du client.");
            
            
        } catch (Exception e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}
