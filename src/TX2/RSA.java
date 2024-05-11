package TX2;

public class RSA {
	public static double gcd(double a, double b) {
		int gcd = -1;
		if (a * b != 0) {
			for (int i = 1; i <= a && i <= b; i++) {
				if (a % i == 0 && b % i == 0) {
					gcd = i;
				}
			}
		}
		return gcd;
	}

	public static boolean isPrime(double n) {
		boolean flag = true;
		if (n < 2) {
			flag = false;
		} else {
			for (int i = 2; i <= (int) (Math.sqrt(n)); i++) {
				if (n % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public static double timSoDu(double a, double b, double n) {
		double res = (double) (Math.pow(a, b));
		return res % n;
	}

	public static double eculid(double A, double M) {

		for (double X = 1; X < M; X++)
			if (((A % M) * (X % M)) % M == 1)
				return X;
		return 1;
	}

	public static double[] convertStringToArray(String str) {
		double[] myArray = new double[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				myArray[i] = ch - 'A';
			} else if (Character.isLowerCase(ch)) {
				myArray[i] = ch - 'a' + 26;
			} else {
				myArray[i] = -1;
			}
		}
		return myArray;
	}

	public static double encode(double data) {
		double p = 5;
		double q = 11;
		double n = p * q;
		double phi = (p - 1) * (q - 1);
		double e = 2;
		for (double i = 2; i < phi; i++) {
			if (gcd(i, phi) == 1) {
				e = i;
				break;
			}
		}
//		System.out.println(e);
//		System.out.println(n);
		double c = timSoDu(data, e, n);
		double d = eculid(e, phi);
		System.out.println("c = " + c);
		System.out.println("d = " + d);
		double result = timSoDu(c, d, n);
		return result;
	}

	public static void main(String[] args) {
		Object input = "C";
		String encryptedString = "";
		if (input instanceof String) {
			String str = (String) input;
			double[] myArray = convertStringToArray(str);
			for (int i = 0; i < myArray.length; i++) {
				System.out.println(myArray[i]);
				double c = encode(myArray[i]);
				System.out.println(c);
				char ch = (char) (c + 'A');
				encryptedString += ch;
			}
		}
		System.out.println("Message data = " + input);
		System.out.println(encryptedString);
//		double msg = 32;
//		double c = RSA.encode(msg);	
//		System.out.println(msg);
//		System.out.println(c);
	}
}
