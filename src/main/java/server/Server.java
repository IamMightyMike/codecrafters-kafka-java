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


    public Server(int port) throws IOException {
        this.port = port;
        this.socket = new ServerSocket(port);

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

            while(true) {
                Socket clientSocket = socket.accept();

                System.out.println("Incoming connection ---> " + clientSocket.getInetAddress().getHostAddress() + " | " + clientSocket.getInetAddress().getHostName());

                while(!clientSocket.isClosed()) {
                    InputStream inputStream = clientSocket.getInputStream();

                    Request daRequest = Parser.parseRequest(inputStream);
                    System.out.println("----------> Recibido CORR ID " + daRequest.getCorrelationId());

                    Response daResponse = Parser.parseResponseFromRequest(daRequest);

                    System.out.println("----------> Enviando CORR ID " + daResponse.getCorrelationId());
                    byte[] outpuBytes = Encoder.encodeResponse(daResponse);


                    OutputStream outputStream = clientSocket.getOutputStream();
                    outputStream.write(Utils.fromIntToByteArray(outpuBytes.length));
                    outputStream.write(outpuBytes);
                    outputStream.flush();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
