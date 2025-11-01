package Client;
import operation.operation;
import java.net.*;
import java.io.*;
import java.util.*;
public class Client {
	
	    public static void main(String[] args) {
	        try {
	            // Connexion au serveur local sur le port 1234
	            InetAddress serverIP = InetAddress.getLocalHost();
	            InetSocketAddress add = new InetSocketAddress(serverIP, 1234);
	            Socket s = new Socket();
	            s.connect(add);
	            // Pour recevoir les messages texte du serveur
	            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

	            // Affiche les messages envoyés par le serveur (par exemple, numéro d'ordre)
	            System.out.println(br.readLine());
	            System.out.println(br.readLine());

	            // Pour envoyer un objet au serveur
	            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

	            // Lecture des données utilisateur
	            Scanner sc = new Scanner(System.in);
	            System.out.print("Donner le premier nombre : ");
	            int x = sc.nextInt();
	            System.out.print("Donner l'opération (+, -, *, /) : ");
	            String y = sc.next();
	            System.out.print("Donner le deuxième nombre : ");
	            int z = sc.nextInt();

	            if (!y.equals("+") && !y.equals("-") && !y.equals("*") && !y.equals("/")) {
	                System.out.println("Opérateur invalide !");
	                s.close();
	              
	            }

	            // Création et envoi de l’objet au serveur
	            operation f = new operation(x, y, z);
	            oos.writeObject(f);
	            oos.flush();

	            // Lecture du résultat envoyé par le serveur
	            String resultat = br.readLine();
	            System.out.println("Résultat : " + resultat);

	            // Fermeture des flux et de la socket
	            oos.close();
	            br.close();
	            s.close();
	            sc.close();

	        } catch (UnknownHostException e) {
	            System.err.println("Erreur : hôte introuvable !");
	        } catch (IOException e) {
	            System.err.println("Erreur d’entrée/sortie : " + e.getMessage());
	        }
	    }
	}
