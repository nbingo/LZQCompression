import java.io.*;
import java.util.ArrayList;

public class LZWCompressor {
	
	public static void compress (String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		ArrayList<String> table = new ArrayList<String>(500);
		
		for (int i = 0; i <= 255; i++)
			table.add(""+(char)i);
		
		String str = ""+(char)reader.read();
		
		while(reader.ready())
		{
			char character = (char)reader.read();
			if (table.contains(str+character))
				str += character;
			else
			{
				writer.print(table.indexOf(str));
				table.add(str+character);
				str = ""+character;
			}
		}
		writer.print(table.indexOf(str));
		
		reader.close();
		writer.close();
	}
	
}
