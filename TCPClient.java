import java.net.*;
import java.io.*;

public class TCPClient {
    public void run() {
        try {
            
            int server_port = 12000;
            InetAddress host = InetAddress.getByName("127.0.0.1");
            System.out.println("Server: " + server_port);

            Socket socket = new Socket(host,server_port);
            System.out.println("Connected to " + socket.getRemoteSocketAddress());

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hi from " + socket.getLocalSocketAddress());
            String response = in.readLine();
            System.out.println("Client received the message: " + response + "from Server");
            out.close();
            in.close();
            socket.close();
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



