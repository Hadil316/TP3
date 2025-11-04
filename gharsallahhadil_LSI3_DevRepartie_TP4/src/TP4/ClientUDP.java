package TP4;
import java.net.*;
import java.util.Scanner;
public class ClientUDP {

	    public static void main(String[] args) throws Exception {
	        DatagramSocket socket = new DatagramSocket();
	        InetAddress serveurAdresse = InetAddress.getByName("localhost");
	        int serveurPort = 1234;

	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Entrez votre nom d'utilisateur : ");
	        String nom = scanner.nextLine();

	        while (true) {
	            System.out.print("> ");
	            String message = scanner.nextLine();
	            String messageFinal = "[" + nom + "] : " + message;

	            byte[] data = messageFinal.getBytes();
	            DatagramPacket packet = new DatagramPacket(data, data.length, serveurAdresse, serveurPort);
	            socket.send(packet);
	        }
	    }
}
