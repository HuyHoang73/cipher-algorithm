package TX2;

public class Affine {

    // Key values of a and b
    static int a = 5;
    static int b = 3;

    static String encryptMessage(String msg) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char ch = msg.charAt(i);
            if (Character.isLetter(ch)) {
                int charIndex = Character.isUpperCase(ch) ? (ch - 'A') : (ch - 'a');
                int encryptedIndex = (a * charIndex + b) % 26;
                char encryptedChar = Character.isUpperCase(ch) ? (char) (encryptedIndex + 'A') : (char) (encryptedIndex + 'a');
                cipher.append(encryptedChar);
            } else {
                cipher.append(ch); // keep non-alphabet characters unchanged
            }
        }

        return cipher.toString();
    }

    static String decryptCipher(String cipher) {
        StringBuilder msg = new StringBuilder();

        int a_inv = 0;
        int flag = 0;

        // Find modular inverse of 'a' under modulo 26
        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;

            if (flag == 1) {
                a_inv = i;
            }
        }

        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (Character.isLetter(ch)) {
                int charIndex = Character.isUpperCase(ch) ? (ch - 'A') : (ch - 'a');
                // Apply modular arithmetic to get original index
                int decryptedIndex = (a_inv * (charIndex - b + 26)) % 26;
                if (decryptedIndex < 0) {
                    decryptedIndex += 26;
                }
                char decryptedChar = Character.isUpperCase(ch) ? (char) (decryptedIndex + 'A') : (char) (decryptedIndex + 'a');
                msg.append(decryptedChar);
            } else {
                msg.append(ch); // keep non-alphabet characters unchanged
            }
        }

        return msg.toString();
    }

    // Driver code
    public static void main(String[] args) {
        String msg = "Hello World!";

        String cipherText = encryptMessage(msg);
        System.out.println("Encrypted Message is: " + cipherText);

        String decryptedMessage = decryptCipher(cipherText);
        System.out.println("Decrypted Message is: " + decryptedMessage);
    }
}
