
import java.io.*;
import java.net.*;

public class TCPserver {
    public void run() {
        try {
            int server_port = 12000;
            ServerSocket server_socket = new ServerSocket(server_port);
            server_socket.setSoTimeout(100000);
            while(true) {
                System.out.println("Waiting for client to connect" + server_socket.getLocalPort() + "...");

                Socket client_socket = server_socket.accept();
                System.out.println("Just connected to " + client_socket.getRemoteSocketAddress());

                PrintWriter output_text = new PrintWriter(client_socket.getOutputStream(),true);
                BufferedReader input_text = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
                    
                String response = input_text.readLine();
                System.out.println("server received: " + response);
                output_text.println("Thanks for connecting to " + client_socket.getLocalSocketAddress());
                server_socket.close();
            }
            
        }
        catch (UnknownHostException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }
public static void main(String[] args) {
    TCPserver server = new TCPserver();
        server.run();

    }
}



