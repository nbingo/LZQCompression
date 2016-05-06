import java.io.*;
import java.util.Hashtable;

public class LZWCompressor {
	
	public static void compress (String infilename, String outfilename, int byteLimit) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		
		writer.write(byteLimit+"\n");
		
		double tableLimit = Math.pow(2, byteLimit);
		Hashtable<String, Integer> table = new Hashtable<String, Integer>((int)tableLimit);
		
		for (int i = 0; i <= 255; i++)
			table.put(""+(char)i, i);
		
		int counter = 256;
		
		String str = ""+(char)reader.read();
		
		PrintWriter code = new PrintWriter(new BufferedWriter(new FileWriter("tempBin.txt")));
		
		int binCounter = 0;
		
		while(reader.ready())
		{
			char character = (char)reader.read();
			if (table.containsKey(str+character))
				str += character;
			else
			{
				String bin = intToBinary(table.get(str), byteLimit);
				code.write(bin);
				binCounter += bin.length();
				if (table.size() < tableLimit)
					table.put(str+character, counter++);
				str = ""+character;
			}
		}
		String bin = intToBinary(table.get(str), byteLimit);
		code.write(bin);
		binCounter += bin.length();

		code.close();
		BufferedReader binReader = new BufferedReader(new FileReader("tempBin.txt"));
		
		if (binCounter%8!=0)
		{
			int i;
			for (i = 0; i < binCounter/8 - 1; i++)
				writer.write((char)Integer.parseInt(eightChars(binReader)));
			
			String lastFew = lastFewChars(binReader);
			lastFew = lastFew + makeZeros(8-lastFew.length());
			writer.write((char)Integer.parseInt(lastFew, 2));
		}
		else
			for (int i = 0; i < binCounter/8 && binReader.ready(); i++)
				writer.write((char)Integer.parseInt(eightChars(binReader)));
		
		reader.close();
		writer.close();
		binReader.close();
	}
	
	private static String intToBinary(int n, int byteLimit)
	{
		String result = Integer.toBinaryString(n);
		if (result.length() < byteLimit)
			result = makeZeros(byteLimit-result.length()) + result;
		return result;
	}
	
	private static String makeZeros (int n)
	{
		String zeros = "";
		for (int i = 0; i < n; i++)
			zeros += "0";
		return zeros;
	}
	
	private static String eightChars(BufferedReader reader) throws IOException
	{
		String result = "";
		for (int i = 0; i < 8; i++)
			result += (char)reader.read();
		return result;
	}
	
	private static String lastFewChars(BufferedReader reader) throws IOException
	{
		String result = "";
		while (reader.ready())
			result += (char)reader.read();
		return result;
	}
	
}
