import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
    public void run() {
        try {
            Scanner scan = new Scanner(System.in);
            int server_port = 12000;
            InetAddress host = InetAddress.getByName("127.0.0.1");
            System.out.println("Server: " + server_port);

            Socket client_socket = new Socket(host,server_port);
            System.out.println("Connected to " + client_socket.getRemoteSocketAddress());

            PrintWriter output_text = new PrintWriter(client_socket.getOutputStream(),true);
            BufferedReader input_text = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));

            Thread outgoing_text = new Thread(new Runnable() {
                String response;
                public void run() {
                    while(true) {
            
                        response = scan.nextLine();
                        output_text.println(response);
                        output_text.flush();

                    }
                }
            });

            outgoing_text.start();
            
            Thread incoming_text = new Thread(new Runnable() {
                String response;
                public void run() {
                    try {

                        response = input_text.readLine();
                        while(response != null) {

                            System.out.println(response);
                            response = input_text.readLine();

                        }
                    
                        System.out.println("Server disconnected");
                        output_text.close();
                        client_socket.close();
                        scan.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                    }
                   
                });
             
            incoming_text.start();
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




