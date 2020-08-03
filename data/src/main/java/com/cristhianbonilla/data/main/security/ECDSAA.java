package com.cristhianbonilla.data.main.security;

public class ECDSAA {
    public static int getSignatureByteArrayLength(final String alg) {

        if (alg.equals("ES256")) {
            return 64;
        } else if (alg.equals("ES384")) {
            return 96;
        } else if (alg.equals("ES512")) {
            return 132;
        } else {
            throw new IllegalArgumentException("Unsupported");
        }
    }

    public static byte[] transcodeSignatureToConcat(final byte[] derSignature, int outputLength){
        if (derSignature.length < 8 || derSignature[0] != 48) {
            throw new IllegalArgumentException("Invalid  ECDSA signature format");
        }

        int offset;
        if (derSignature[1] > 0) {
            offset = 2;
        } else if (derSignature[1] == (byte) 0x81) {
            offset = 3;
        } else {
            throw new IllegalArgumentException("Invalid  ECDSA signature format");
        }

        byte rLength = derSignature[offset + 1];

        int i;
        for (i = rLength; (i > 0) && (derSignature[(offset + 2 + rLength) - i] == 0); i--) {
            // do nothing
        }

        byte sLength = derSignature[offset + 2 + rLength + 1];

        int j;
        for (j = sLength; (j > 0) && (derSignature[(offset + 2 + rLength + 2 + sLength) - j] == 0); j--) {
            // do nothing
        }

        int rawLen = Math.max(i, j);
        rawLen = Math.max(rawLen, outputLength / 2);

        if ((derSignature[offset - 1] & 0xff) != derSignature.length - offset
                || (derSignature[offset - 1] & 0xff) != 2 + rLength + 2 + sLength
                || derSignature[offset] != 2
                || derSignature[offset + 2 + rLength] != 2) {
            throw new IllegalArgumentException("Invalid  ECDSA signature format");
        }

        final byte[] concatSignature = new byte[2 * rawLen];

        System.arraycopy(derSignature, (offset + 2 + rLength) - i, concatSignature, rawLen - i, i);
        System.arraycopy(derSignature, (offset + 2 + rLength + 2 + sLength) - j, concatSignature, 2 * rawLen - j, j);

        return concatSignature;
    }
}