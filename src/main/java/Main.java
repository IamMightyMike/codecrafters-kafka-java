import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Main {
  public static void main(String[] args){

    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");



    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 9092;

    try {

      serverSocket = new ServerSocket(port);
      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors
      serverSocket.setReuseAddress(true);
      // Wait for connection from client.
      clientSocket = serverSocket.accept();
      InputStream input = clientSocket.getInputStream();
      OutputStream out = clientSocket.getOutputStream();

      //Para conectar desde WSL sacar la IP con cat /etc/resolv.conf | grep nameserver

      byte[] buffer = new byte[1024];
      int byteRead;
      if ((byteRead = input.read(buffer)) != -1) {


          byte[] output = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          int offsetOrigin = 8;
          int offsetDestination = 4;
          IntStream.range(0,6).forEach(i -> {

          output[i + offsetDestination] = buffer[i + offsetOrigin];
        });

        System.arraycopy(buffer, 8, output, 4, 6);
        out.write(output);
      } else {
        System.out.println("Nothing to read from input stream");
      }


    }catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
    }finally {

      try{

        if(clientSocket != null) {

          clientSocket.close();
        }

      }catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      }
    }
  }
}
