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

        return request;

    }

    public static Response parseResponseFromRequest(Request request){

        Response response = Response.builder()
                .setCorrelationId(request.getCorrelationId())
                .setErrorCode(request.getApiVersion() < 0 ||  request.getApiVersion() >4 ?(short) 35:(short)0)
                .setArrayLength(2)
                .setApiKey((short) 18)
                .setMaxVersion((short)4)
                .setMinVersion((short)3)
                .setArrayTaggedFields((byte)0)
                .setThrottleTime(0)
                .setOuterTaggedFields((byte)0)
                .build();

        return response;
    }



}
