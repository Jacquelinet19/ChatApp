import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPserver {
    
    private String name;
    
    public void run() {
        Scanner scan = new Scanner (System.in);
        

        
        System.out.println("Your Name: ");
        this.name = scan.nextLine();

        try {
            
            int server_port = 12000;
            ServerSocket server_socket = new ServerSocket(server_port);
            server_socket.setSoTimeout(100000);
            
            System.out.println("Waiting for client to connect" + server_socket.getLocalPort() + "...");

            Socket client_socket = server_socket.accept();
            System.out.println("Just connected to " + client_socket.getRemoteSocketAddress());
            
            

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

                        System.out.println("Client disconnected from chat");
                        output_text.close();
                        client_socket.close();
                        server_socket.close();
                        scan.close();
                
                    } catch (IOException i) {
                        i.printStackTrace(); 

                    }
                }   
            
            });
            incoming_text.start();
            
        }
        catch (UnknownHostException e) {
            e.printStackTrace();

        }
catch (IOException i) {
    i.printStackTrace();

        }
    }
public static void main(String[] args) {
    TCPserver server = new TCPserver();
    
    server.run();

    }
}



