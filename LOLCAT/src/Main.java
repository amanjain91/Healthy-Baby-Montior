import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		// Settings
		// The number of bits if the data
		final int depth = 16;
		// The size of the memory in words.
		final int size = 512;
		StringBuffer sin = new StringBuffer();
		StringBuffer cos = new StringBuffer();
		sin.append("\n-- Sin look up table implemented");
		cos.append("\n-- cos look up table implemented");
		// adding depth and width
		sin.append("\nDEPTH = " + depth + ";\t-- The size of data in bits");
		cos.append("\nDEPTH = " + depth + ";\t-- The size of data in bits");
		sin.append("\nWIDTH = " + size + ";\t-- The size of memory in words");
		cos.append("\nWIDTH = " + size + ";\t-- The size of memory in bits");

		// Setting Address radix to be unsigned decimal values.
		sin.append("\nADDRESS_RADIX = UNS;\t-- The radix for address values ");
		cos.append("\nADDRESS_RADIX = UNS;\t-- The radix for address values ");
		// The data radix has to be binary.
		sin.append("\nDATA_RADIX = HEX;\t-- The radix for data values");
		sin.append("\nCONTENT");
		sin.append("\nBEGIN");
		cos.append("\nDATA_RADIX = HEX;\t-- The radix for data values");
		cos.append("\nCONTENT");
		cos.append("\nBEGIN");

		// Iterating through 1 to 360, taking each address and putting it's sin
		// value in 1.8.7 format
		for (int i = 0; i < size; i++) {
			sin.append("\n" + pad(i) + ": "
					+ format(Math.sin(Math.toRadians(wrap(i)))) + ";");
			cos.append("\n" + pad(i) + ": "
					+ format(Math.cos(Math.toRadians(wrap(i)))) + ";");
		}
		sin.append("\nEND;");
		cos.append("\nEND;");
		try {
			BufferedWriter bws = new BufferedWriter(new FileWriter(new File(
					"sin.mif")));
			BufferedWriter bwc = new BufferedWriter(new FileWriter(new File(
					"cos.mif")));
			bws.write(sin.toString());
			bws.flush();
			bws.close();
			// System.out.println(sin);
			System.out.println(cos);
			bwc.write(cos.toString());
			bwc.flush();
			bwc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sin.toString());
		System.out.println(cos.toString());
	}

	private static String format(double value) {
		String answer = Integer.toHexString(0xffff & ((int) (value * 128)));
		return answer;
	}

	private static String pad(int i) {
		String ans = Integer.toString(i);
		while (ans.length() < 3) {
			ans = "0" + ans;
		}
		return ans;
	}

	private static String padZeros(String x) {
		while (x.length() < 16) {
			x = "0" + x;
		}
		return x;
	}

	private static int wrap(int x) {
		if (x > 255) {
			return (x - 512);
		}
		return x;
	}

}
