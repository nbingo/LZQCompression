import java.io.*;
import java.util.Hashtable;

public class LZWCompressor {
	
	public static void compress (String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		Hashtable<String, Integer> table = new Hashtable<String, Integer>(500);
		
		for (int i = 0; i <= 255; i++)
			table.put(""+(char)i, i);
		
		int counter = 256;
		
		String str = ""+(char)reader.read();
		
		while(reader.ready())
		{
			char character = (char)reader.read();
			if (table.containsKey(str+character))
				str += character;
			else
			{
				writer.print(table.get(str)+" ");
				table.put(str+character, counter);
				counter++;
				str = ""+character;
			}
		}
		writer.print(table.get(str));
		
		reader.close();
		writer.close();
	}
	
}
