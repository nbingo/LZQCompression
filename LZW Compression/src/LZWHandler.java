import java.io.File;
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
			File in = new File (input), out = new File(output);
			System.out.println("Original file size: " + makeHumanReadable(in.length()));
			System.out.print("Compressed file size: " + makeHumanReadable(out.length()));
		}
		else
			LZWDecompressor.decompress(input, output);
		k.close();
	}
	
	private static String makeHumanReadable(long bytes)
	{
		if (bytes < 1000)
			return bytes + " B";
		else if (bytes < 1e6)
			return bytes/1000 + " kB";
		else if (bytes < 1e9)
			return bytes/1e6 + " MB";
		else if (bytes < 1e12)
			return bytes/1e9 + " GB";
		else if (bytes < 1e15)
			return bytes/1e12 + " TB";
		else
			return bytes/1e15 + " PB";
	}

}
