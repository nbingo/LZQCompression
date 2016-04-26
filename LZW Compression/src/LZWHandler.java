import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LZWHandler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner k = new Scanner (System.in);
		System.out.println("Input filename:");
		String input = k.nextLine();
	
		System.out.println("Output filename:");
		String output = k.nextLine();
		
		LZWCompressor.compress(input, output);
		k.close();
	}

}
