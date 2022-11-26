import java.net.*;
import java.io.*;

public class TCPClient {
    public void run() {
        try {
            
            int server_port = 12000;
            InetAddress host = InetAddress.getByName("127.0.0.1");
            System.out.println("Server: " + server_port);

            Socket client_socket = new Socket(host,server_port);
            System.out.println("Connected to " + client_socket.getRemoteSocketAddress());

            PrintWriter output_text = new PrintWriter(client_socket.getOutputStream(),true);
            BufferedReader input_text = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));

            output_text.println("Hi from " + client_socket.getLocalSocketAddress());
            String response = input_text.readLine();
            System.out.println("Client received the message: " + response + "from Server");
            output_text.close();
            input_text.close();
            client_socket.close();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();

        }
        catch(IOException e) {
            e.printStackTrace();

        }
    }
public static void main(String[] args) {
    TCPClient client = new TCPClient();
    client.run();

    }

}



