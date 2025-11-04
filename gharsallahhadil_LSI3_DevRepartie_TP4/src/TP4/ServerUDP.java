package TP4;
import java.net.*;
public class ServerUDP {
	    public static void main(String[] args) throws Exception {
	    	
	        DatagramSocket socket = new DatagramSocket(new InetSocketAddress(1234));
	        System.out.println("Serveur UDP en écoute sur le port 1234...");

	        byte[] buffer = new byte[1024];
	        while (true) {
	            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	            socket.receive(packet);
	            String message = new String(packet.getData(), 0, packet.getLength());
	            System.out.println("Message reçu de " + packet.getAddress() + ":" + packet.getPort() + "" + message);
	        }
	    }
	

}
