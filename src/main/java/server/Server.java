package server;

import dto.Request;
import dto.Response;
import utils.Encoder;
import utils.Parser;
import utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private int port;
    ServerSocket socket;

    private Parser parser;


    public Server(int port) throws IOException {
        this.port = port;
        this.socket = new ServerSocket(port);
        this.parser = new Parser();

        // Since the tester restarts your program quite often, setting SO_REUSEADDR
        // ensures that we don't run into 'Address already in use' errors

        this.socket.setReuseAddress(true);
    }


    @Override
    public void run(){

        runServer();
    }

    public void attemptToCloseClientSocket(){

        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void runServer(){

        try {

            //while(true) {
                Socket clientSocket = socket.accept();

                System.out.println("Incoming connection ---> " + clientSocket.getInetAddress().getHostAddress() + " | " + clientSocket.getInetAddress().getHostName());

                while (clientSocket != null) {
                    InputStream inputStream = clientSocket.getInputStream();

                    Request daRequest = parser.parseRequest(inputStream);

                    System.out.println("---> " + daRequest.getCorrelationId());
                    Response daResponse = parser.parseResponseFromRequest(daRequest);


                    byte[] outpuBytes = Encoder.encodeResponse(daResponse);


                    OutputStream outputStream = clientSocket.getOutputStream();
                    outputStream.write(Utils.fromIntToByteArray(outpuBytes.length));
                    outputStream.write(outpuBytes);
                    outputStream.flush();
                }
            //}

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
