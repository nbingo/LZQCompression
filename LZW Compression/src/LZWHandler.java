import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LZWHandler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner k = new Scanner (System.in);
		
		System.out.println("Compress or decompress? (c/d)");
		String choice = k.nextLine();
		while (!(choice.equalsIgnoreCase("c") || choice.equalsIgnoreCase("d")))
		{
			System.out.println("Please enter a correct option.");
			choice = k.nextLine();
		}
		
		System.out.println("Input filename:");
		String input = k.nextLine();
	
		System.out.println("Output filename:");
		String output = k.nextLine();
		
		if(choice.equalsIgnoreCase("c"))
		{
			System.out.println("How many bits per code?");
			LZWCompressor.compress(input, output, k.nextInt());
		}
		else
			LZWDecompressor.decompress(input, output);
		k.close();
	}

}
