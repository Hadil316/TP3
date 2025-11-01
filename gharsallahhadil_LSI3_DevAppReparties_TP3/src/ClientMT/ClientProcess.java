package ClientMT;
import java.io.*;
import java.net.*;

public class ClientProcess {
    public static void main(String[] args) {
        String serveurIP = "localhost"; 
        int port = 1234;

        try (Socket socket = new Socket(serveurIP, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Connecté au serveur " + serveurIP + ":" + port);
            System.out.println("Message du serveur : " + in.readLine());

            String ligne;
            while (true) {
                System.out.print("Vous : ");
                ligne = clavier.readLine();
                if (ligne == null || ligne.equalsIgnoreCase("exit")) {
                    System.out.println(" Déconnexion...");
                    break;
                }
                out.println(ligne);
                System.out.println("Serveur : " + in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

