/*
*Eric Kannampuzha
*Exercise 1
*Class EchoServer
*CS 380
*Nima
*/
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
    
        
        
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try {
                Socket socket = serverSocket.accept();
                Runnable clientHandler = () -> {
                        try {
                            String address = socket.getInetAddress().getHostAddress();
                            System.out.printf("Client connected: %s%n", address);
                            OutputStream os = socket.getOutputStream();
                            PrintStream out = new PrintStream(os, true, "UTF-8");
                            InputStream is = socket.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                            BufferedReader br = new BufferedReader(isr);
                            String line;
                            while((line = br.readLine()) != null) {
                                out.printf(line + "\n");
                                //System.out.println(line);
                            }
                            socket.close();
                            System.out.printf("Client disconnected: %s%n", address);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    };
                    Thread clientHandlerThread = new Thread(clientHandler);
                    clientHandlerThread.start();
                }
                catch( Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
