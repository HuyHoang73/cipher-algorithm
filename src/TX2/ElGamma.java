package TX2;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamma {

    private static SecureRandom random = new SecureRandom();

    public static BigInteger[] generateKey(int bits) {
        BigInteger p, g;
        do {
            p = BigInteger.probablePrime(bits, random);
            g = new BigInteger(bits, random).mod(p); // Choose random g < p
        } while (g.equals(BigInteger.ZERO) || g.equals(BigInteger.ONE));
        BigInteger x = new BigInteger(bits - 1, random).mod(p.subtract(BigInteger.ONE)); // Private key x
        BigInteger h = g.modPow(x, p); // Public key h = g^x mod p
        return new BigInteger[]{p, g, h, x};
    }

    public static BigInteger[] encrypt(String msg, BigInteger p, BigInteger g, BigInteger h) {
        byte[] bytes = msg.getBytes();
        BigInteger[] cipherText = new BigInteger[bytes.length];
        BigInteger k = new BigInteger(p.bitLength() - 1, random).mod(p.subtract(BigInteger.ONE).subtract(BigInteger.ONE)).add(BigInteger.ONE);
        BigInteger p1 = g.modPow(k, p);
        BigInteger p2 = h.modPow(k, p);
        for (int i = 0; i < bytes.length; i++) {
            cipherText[i] = p2.multiply(BigInteger.valueOf(bytes[i])).mod(p);
        }
        return new BigInteger[]{p1, cipherText[0]};
    }

    public static String decrypt(BigInteger[] cipherText, BigInteger p, BigInteger x) {
        BigInteger p1 = cipherText[0];
        BigInteger p2 = cipherText[1];
        BigInteger s = p1.modPow(x, p);
        BigInteger invS = s.modInverse(p);
        byte[] bytes = new byte[p2.bitLength()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = invS.multiply(p2).mod(p).byteValue();
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
        String msg = "encryption";
        System.out.println("Original Message: " + msg);

        BigInteger[] keys = generateKey(256); // Generate keys with 256-bit prime modulus
        BigInteger p = keys[0];
        BigInteger g = keys[1];
        BigInteger h = keys[2];
        BigInteger x = keys[3];

        System.out.println("g used: " + g);
        System.out.println("g^x used: " + h);

        BigInteger[] encrypted = encrypt(msg, p, g, h);
        BigInteger p1 = encrypted[0];
        BigInteger p2 = encrypted[1];

        String decrypted = decrypt(new BigInteger[]{p1, p2}, p, x);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
