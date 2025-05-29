package iticbcn.xifratge;

import java.util.Base64;

public class TextXifrat {
    private final  byte[] bytesXifrats;

    public TextXifrat(byte[] bytesXifrats) {
        this.bytesXifrats = bytesXifrats;
    }

    public byte[] getBytes() {
        return bytesXifrats;
    }

    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(bytesXifrats);
    }
}