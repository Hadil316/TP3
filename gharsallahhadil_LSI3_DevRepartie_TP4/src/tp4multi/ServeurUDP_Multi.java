package tp4multi;
import java.net.*;
import java.util.*;
public class ServeurUDP_Multi {
	
	    public static void main(String[] args) throws Exception {
	        DatagramSocket socket = new DatagramSocket(1234);
	        System.out.println("Serveur UDP multi-clients démarré sur le port 1234...");

	        Set<SocketAddress> clients = new HashSet<>();
	        byte[] buffer = new byte[1024];

	        while (true) {
	            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	            socket.receive(packet);
	            String message = new String(packet.getData(), 0, packet.getLength());
	            SocketAddress exp = packet.getSocketAddress();

	           
	            clients.add(exp);
	            System.out.println("Message reçu de " + exp + " : " + message);

	            // Diffusion à tous les autres clients
	            for (SocketAddress client : clients) {
	                if (!client.equals(exp)) {
	                    DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), client);
	                    socket.send(outPacket);
	                }
	            }
	        }
	    }
	}



