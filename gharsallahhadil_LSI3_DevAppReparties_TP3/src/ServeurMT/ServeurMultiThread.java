package ServeurMT;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ServeurMultiThread {

    public static void main(String[] args) {
        int port = 1234;
        AtomicInteger compteurClients = new AtomicInteger(0);

        try (ServerSocket serveur = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur le port " + port);
            System.out.println("En attente de connexions...");

            while (true) {
                Socket socketClient = serveur.accept();
                int numeroClient = compteurClients.incrementAndGet();

                System.out.println(" Nouveau client connecté :");
                System.out.println(" IP : " + socketClient.getRemoteSocketAddress());
                System.out.println(" Numéro de client : " + numeroClient);

                
                Thread t = new Thread(new GestionClient(socketClient, numeroClient));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class GestionClient implements Runnable {
    private Socket socket;
    private int numeroClient;

    public GestionClient(Socket socket, int numeroClient) {
        this.socket = socket;
        this.numeroClient = numeroClient;
    }

   
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("Bienvenue, vous êtes le client n°" + numeroClient);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[Client " + numeroClient + "] : " + message);
                out.println("Echo du serveur : " + message);
            }

        } catch (IOException e) {
            System.out.println("Client n°" + numeroClient + " déconnecté.");
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}

