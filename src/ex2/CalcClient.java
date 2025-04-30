/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

import java.io.*;
import java.net.*;

public class CalcClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while (true) {
                System.out.print("Opération : ");
                message = userInput.readLine();
                if (message.equalsIgnoreCase("exit")) break;

                out.println(message);
                String response = in.readLine();
                System.out.println("Serveur : " + response);
            }

            System.out.println("Déconnexion.");
        } catch (IOException e) {
            System.out.println("Erreur client : " + e.getMessage());
        }
    }
}

