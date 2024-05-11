package TX2;

public class GFG {
	public static double gcd(double a, double h) {
		/*
		 * This function returns the gcd or greatest common divisor
		 */
		double temp;
		while (true) {
			temp = a % h;
			if (temp == 0)
				return h;
			a = h;
			h = temp;
		}
	}

	public static void main(String[] args) {
		double p = 7;
		double q = 11;

		// Stores the first part of public key:
		double n = p * q;

		// Finding the other part of public key.
		// double e stands for encrypt
		double e = 2;
		double phi = (p - 1) * (q - 1);
		while (e < phi) {
			/*
			 * e must be co-prime to phi and smaller than phi.
			 */
			if (gcd(e, phi) == 1)
				break;
			else
				e++;
		}
		System.out.println(e);
		System.out.println(n);
		int k = 2; // A constant value
		long d = (long) ((1 + (k * phi)) / e);
		System.out.println(d);
		// Message to be encrypted
		double msg = 2;

		System.out.println("Message data = " + msg);

		// Encryption c = (msg ^ e) % n
		double c = Math.pow(msg, e);
		c = c % n;
		System.out.println("Encrypted data = " + c);

		// Decryption m = (c ^ d) % n
		double m = Math.pow(c, d);
		m = m % n;
		System.out.println("Original Message Sent = " + m);
	}
}

// This code is contributed by Pranay Arora.
