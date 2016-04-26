import java.io.*;
import java.util.ArrayList;

public class LZWCompressor {
	
	public static void compress (String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		ArrayList<TableEntry> table = new ArrayList<TableEntry>(500);
		
		for (int i = 0; i <= 255; i++)
			table.add(new TableEntry(i, ""+(char)i));
		int counter = 256;
		
		String str = ""+(char)reader.read();
		
		while(reader.ready())
		{
			char character = (char)reader.read();
			if (table.contains(new TableEntry(0, str+character)))
				str += character;
			else
			{
				writer.print(table.indexOf(new TableEntry(0, str)));
				table.add(new TableEntry(counter, str+character));
				counter++;
				str = ""+character;
			}
		}
		writer.print(table.indexOf(new TableEntry(0, str)));
		
		reader.close();
		writer.close();
	}
	
}
