package tp4multi;
import java.net.*;
import java.util.Scanner;
public class ClientUDP_Multi {

	    public static void main(String[] args) throws Exception {
	        DatagramSocket socket = new DatagramSocket();
	        InetAddress serveurAdresse = InetAddress.getByName("localhost");
	        int serveurPort = 1234;
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Entrez votre nom d'utilisateur : ");
	        String nom = scanner.nextLine();

	        Thread reception = new Thread(() -> {
	            try {
	                byte[] buffer = new byte[1024];
	                while (true) {
	                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	                    socket.receive(packet);
	                    String message = new String(packet.getData(), 0, packet.getLength());
	                    System.out.println("\n" + message + "\n> ");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        });
	        reception.start();

	        
	        while (true) {
	            System.out.print("> ");
	            String message = scanner.nextLine();
	            String msgFinal = "[" + nom + "] : " + message;
	            byte[] data = msgFinal.getBytes();
	            DatagramPacket packet = new DatagramPacket(data, data.length, serveurAdresse, serveurPort);
	            socket.send(packet);
	        }
	    }
}



