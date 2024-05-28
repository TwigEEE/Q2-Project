import java.net.*;
import java.io.*;


public class Server {
    public static void main(String[] args) throws IOException {

        int portNumber = 1023;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Manager manager = new Manager();

        int id = 0;
		while(true) {
            System.out.println("Waiting for client connection...");

            Socket clientSocket = serverSocket.accept();

            ServerThread serverThread = new ServerThread(clientSocket, manager, id);
            manager.addServerThread(serverThread);
            Thread thread = new Thread(serverThread);
            thread.start();

            id++;
        }
    }
}
