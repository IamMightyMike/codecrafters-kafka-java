package utils;

import java.nio.ByteBuffer;

public class Utils {

    public static int fromByteArrayToInt(byte[] input){
        int output =  0;
        for(byte b : input){ output = (output<<8)+(b&0xFF) ; }
        return  output ;
    }

    public static Short fromByteArrayToShort(byte[] input){
        return (short) ((input[0]<<8) | (input[1]&0xFF)) ;
    }

    public static byte[] fromIntToByteArray(int input){
        ByteBuffer buffer = ByteBuffer.allocate(4).putInt(input).flip() ;
        byte[] b = new byte[buffer.remaining()];
        buffer.get(b) ;
        return b ;
    } ;
    public static byte[] fromShortToByteArray(short input){
        ByteBuffer buffer = ByteBuffer.allocate(2).putShort(input).flip() ;
        byte[] b = new byte[buffer.remaining()];
        buffer.get(b) ;
        return b ;
    }

}
