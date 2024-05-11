package TX2;

public class Affine {

	// Key values of a and b
	static int a = 5;
	static int b = 3;

	static String encryptMessage(char[] msg) {
		/// Cipher Text initially empty
		String cipher = "";
		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != ' ') {
				cipher = cipher + (char) ((((a * (msg[i] - 'A')) + b) % 26) + 'A');
			} else // else simply append space character
			{
				cipher += msg[i];
			}
		}
		return cipher;
	}

	static String decryptCipher(String cipher) {
		String msg = "";
		int a_inv = 0;
		int flag = 0;

		for (int i = 0; i < 26; i++) {
			flag = (a * i) % 26;

			if (flag == 1) {
				a_inv = i;
			}
		}
		for (int i = 0; i < cipher.length(); i++) {
			if (cipher.charAt(i) != ' ') {
				msg = msg + (char) (((a_inv * ((cipher.charAt(i) + 'A' - b)) % 26)) + 'A');
			} else // else simply append space character
			{
				msg += cipher.charAt(i);
			}
		}

		return msg;
	}

	// Driver code
	public static void main(String[] args) {
		String msg = "ATTACK";

		String cipherText = encryptMessage(msg.toCharArray());
		
		System.out.println("Encrypted Message is : " + cipherText);
		System.out.println("Decrypted Message is: " + decryptCipher(cipherText));

	}
}
