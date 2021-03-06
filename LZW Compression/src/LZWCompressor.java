import java.io.*;
import java.util.Hashtable;

public class LZWCompressor {
	
	public static void compress (String infilename, String outfilename, int byteLimit) throws FileNotFoundException, IOException
	{
		File temp = new File(infilename);
		long bytesRead = 0, totalBytes = temp.length();
		
		BufferedReader reader = new BufferedReader(new FileReader(infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		
		writer.write(byteLimit+"\n");
		
		double tableLimit = Math.pow(2, byteLimit);
		Hashtable<String, Integer> table = new Hashtable<String, Integer>((int)tableLimit);
		
		for (int i = 0; i <= 255; i++)
			table.put(""+(char)i, i);
		
		int counter = 256;
		
		String str = ""+(char)reader.read();
		
		StringBuffer code = new StringBuffer();
		
		while(reader.ready())
		{
			char character = (char)reader.read();
			bytesRead++;
			if (table.containsKey(str+character))
				str += character;
			else
			{
				code.append(intToBinary(table.get(str), byteLimit));
				if (table.size() < tableLimit)
					table.put(str+character, counter++);
				str = ""+character;
			}
			
			while (code.length() > 8)
			{
				writer.write((char)Integer.parseInt(code.substring(0, 8),2));
				code.delete(0, 8);
			}
			if (bytesRead%1e6==0)
				System.out.println("% of file compressed: " + (bytesRead/(totalBytes+.0)*100));
		}
		code.append(intToBinary(table.get(str), byteLimit));
		
		if (code.length()%8!=0)
		{
			int i;
			for (i = 0; i < code.length()/8 - 1; i++)
				writer.write((char)Integer.parseInt(code.substring(8*i, (i+1)*8),2));
			
			String lastFew = code.substring(i*8);
			lastFew = lastFew + makeZeros(8-lastFew.length());
			writer.write((char)Integer.parseInt(lastFew, 2));
		}
		else
			for (int i = 0; i < code.length()/8; i++)
				writer.write((char)Integer.parseInt(code.substring(8*i, (i+1)*8),2));
		
		reader.close();
		writer.close();
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
	
}
