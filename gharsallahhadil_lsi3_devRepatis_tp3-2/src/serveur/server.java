package serveur;
import java.net.*;
import java.io.*;

public class server {

    public static void main(String[] args) {
        int count = 0;
        final int PORT = 1234;

        try (ServerSocket ss = new ServerSocket(PORT)) {
            System.out.println("Serveur Calculatrice Multi-Thread démarré sur le port " + PORT + "...");
            
            while (true) {
                Socket s = ss.accept(); // attente d'un client
                count++;
                System.out.println(" Client #" + count + " connecté : " + s.getRemoteSocketAddress());
                
                // Lancer un thread pour gérer ce client
                new ClientProcess(s, count).start();
            }

        } catch (IOException e) {
            System.err.println(" Erreur serveur : " + e.getMessage());
        }
    }
}