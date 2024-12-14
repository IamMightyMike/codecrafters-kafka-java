package utils;

import dto.Request;
import dto.Response;


import java.io.IOException;
import java.io.InputStream;

public class Parser {

    public static Request parseRequest(InputStream inputStream) throws IOException {


        Request request = Request.builder()
                .setLength(Utils.fromByteArrayToInt(inputStream.readNBytes(4)))
                .setApikey(Utils.fromByteArrayToShort(inputStream.readNBytes(2)))
                .setApiVersion(Utils.fromByteArrayToShort(inputStream.readNBytes(2)))
                .setCorrelationId(Utils.fromByteArrayToInt(inputStream.readNBytes(4)))
                .build();

        //Request request = new Request();
        //request.setLength(Utils.fromByteArrayToInt(inputStream.readNBytes(4)));
        //request.setApikey(Utils.fromByteArrayToShort(inputStream.readNBytes(2)));
        //request.setApiVersion(Utils.fromByteArrayToShort(inputStream.readNBytes(2)));
        //request.setCorrelationId(Utils.fromByteArrayToInt(inputStream.readNBytes(4)));

        byte[] remainingBytes = inputStream.readAllBytes();

        return request;

    }

    public static Response parseResponseFromRequest(Request request){

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
