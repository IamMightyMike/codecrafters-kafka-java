import server.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Main {
  public static void main(String[] args){

    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");

    try {
      Server server = new Server(9092);
      server.run();

    }catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
    }
  }
}
