package utils;

import dto.Response;

import java.nio.ByteBuffer;

public class Encoder {

    public static byte[] encodeResponse(Response response){

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024)
                .putInt(response.getCorrelationId())
                .putShort(response.getErrorCode())
                .put((byte) response.getArrayLength())
                .putShort(response.getApiKey())
                .putShort(response.getMinVersion())
                .putShort(response.getMaxVersion())
                .put(response.getArrayTaggedFields())
                .putInt(response.getThrottleTime())
                .put(response.getOuterTaggedFields())
                .flip() ;

        byte[] resp = new byte[byteBuffer.remaining()] ;


        byteBuffer.get(resp) ;

        return resp ;
    }

}
