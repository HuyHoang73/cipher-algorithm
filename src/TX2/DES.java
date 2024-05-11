package TX2;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {

	public static SecretKey generateKey() throws Exception {
		// Tạo một khóa DES ngẫu nhiên
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		return keyGenerator.generateKey();
	}

	public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
		// Tạo một đối tượng Cipher cho DES
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		// Mã hóa văn bản đơn giản thành mảng byte
		byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

		// Encode các byte đã mã hóa sang chuỗi Base64 để dễ dàng lưu trữ và truyền
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
		// Giải mã dữ liệu đã được mã hóa
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		// Giải mã các byte đã mã hóa từ chuỗi Base64
		byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

		// Giải mã và chuyển đổi về dạng chuỗi ban đầu
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		return new String(decryptedBytes);
	}

	public static void main(String[] args) {
		try {
			// Tạo một khóa DES ngẫu nhiên
			SecretKey secretKey = generateKey();

			// Chuỗi ban đầu cần được mã hóa
			String originalText = "Hello, world!";

			// Mã hóa văn bản
			String encryptedText = encrypt(originalText, secretKey);
			System.out.println("Encrypted: " + encryptedText);

			// Giải mã văn bản
			String decryptedText = decrypt(encryptedText, secretKey);
			System.out.println("Decrypted: " + decryptedText);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
