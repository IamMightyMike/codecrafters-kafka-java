package utils;

import dto.Request;
import dto.Response;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Parser {

    public Request parseRequest(InputStream inputStream) throws IOException {

        DataInputStream dataInputStream = new DataInputStream(inputStream);


        int messageSize = dataInputStream.readInt();
        System.out.println("Message size: " + messageSize);


        ByteBuffer byteBuffas = ByteBuffer.wrap(dataInputStream.readNBytes(messageSize));

        short apiKey = byteBuffas.getShort();
        short requestApiVersion = byteBuffas.getShort();
        int correlationId = byteBuffas.getInt();
        short clientIdLength = byteBuffas.getShort();
        String clientId = null;
        if (clientIdLength >= 0) {
            clientId = new String(byteBuffas.get(new byte[clientIdLength]).array());
        }

        Request request = Request.builder()
                .setLength(messageSize)
                .setApikey(apiKey)
                .setApiVersion(requestApiVersion)
                .setCorrelationId(correlationId)
                .build();

        //Request request = Request.builder()
        //        .setLength(Utils.fromByteArrayToInt(inputStream.readNBytes(4)))
        //        .setApikey(Utils.fromByteArrayToShort(inputStream.readNBytes(2)))
        //        .setApiVersion(Utils.fromByteArrayToShort(inputStream.readNBytes(2)))
        //        .setCorrelationId(Utils.fromByteArrayToInt(inputStream.readNBytes(4)))
        //        .build();



        // Read remaining bytes manually

        inputStream.readNBytes(request.getLength());
        return request;

    }

    public Response parseResponseFromRequest(Request request){

        Response response = Response.builder()
                .setCorrelationId(request.getCorrelationId())
                .setErrorCode(request.getApiVersion() < 0 ||  request.getApiVersion() >4 ?(short) Constants.ERROR_CODE_UNSUPPORTED_VERSION:(short)Constants.ERROR_CODE_OK)
                .setArrayLength(2)
                .setApiKey((short) Constants.API_KEY_REQUEST)
                .setMaxVersion((short)4)
                .setMinVersion((short)3)
                .setArrayTaggedFields((byte)0)
                .setThrottleTime(0)
                .setOuterTaggedFields((byte)0)
                .build();

        return response;
    }



}
