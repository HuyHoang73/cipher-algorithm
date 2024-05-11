package TX2;

public class Caesar {
	// Encrypts text using a shift of s
	public static String encrypt(String input, int s) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			if (Character.isUpperCase(input.charAt(i))) {
				char ch = (char) (((int) input.charAt(i) + s - 65) % 26 + 65);
				result += ch;
			} else {
				char ch = (char) (((int) input.charAt(i) + s - 97) % 26 + 97);
				result += ch;
			}
		}
		return result;
	}

	// Driver code
	public static void main(String[] args) {
		String text = "AN";
		int s = 3;
		System.out.println("Text : " + text);
		System.out.println("Shift : " + s);
		System.out.println("Cipher: " + encrypt(text, s));
	}
}
