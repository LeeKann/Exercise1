
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.net.Socket;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //System.out.println(br.readLine());
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            //out.printf("This is my message\n");
            while(true) {
                System.out.print("Client> ");
                InputStreamReader input = new InputStreamReader(System.in, "UTF-8");
                BufferedReader binput = new BufferedReader(input);
                out.printf(binput.readLine() + "\n");
                String line;
                line = br.readLine();
                System.out.println("Server> " + line);
            }
        }
    }
}















