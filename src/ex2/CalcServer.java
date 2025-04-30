/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;
import java.io.*;
import java.net.*;

public class CalcServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur de calcul prêt...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                try {
                    String[] parts = line.trim().split(" ");
                    if (parts.length != 3) {
                        out.println("Erreur : syntaxe incorrecte. Format attendu : op a b");
                        continue;
                    }

                    String op = parts[0];
                    int a = Integer.parseInt(parts[1]);
                    int b = Integer.parseInt(parts[2]);
                    int result = 0;

                    switch (op) {
                        case "add": result = a + b; break;
                        case "sub": result = a - b; break;
                        case "mul": result = a * b; break;
                        case "div":
                            if (b == 0) {
                                out.println("Erreur : division par zéro.");
                                continue;
                            }
                            result = a / b; break;
                        default:
                            out.println("Erreur : opération inconnue.");
                            continue;
                    }

                    out.println("Résultat : " + result);
                } catch (NumberFormatException e) {
                    out.println("Erreur : les arguments doivent être des entiers.");
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());
        }
    }
}

