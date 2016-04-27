import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class LZWDecompressor {

	public static void decompress(String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		Scanner reader = new Scanner(new BufferedReader(new FileReader (infilename)));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		
		Hashtable<Integer, String> table = new Hashtable<Integer, String>(500);
		
		for (int i = 0; i <= 255; i++)
			table.put(i,""+(char)i);
		int counter = 256;
		
		int code = reader.nextInt();
		
		String str = table.get(code);
		writer.print(str);
		
		String entry = "";
		
		while(reader.hasNextInt())
		{
			code = reader.nextInt();
			if (!table.containsKey(code))
				entry = str + str.charAt(0);
			else
				entry = table.get(code);
			writer.print(entry);
			table.put(counter++, str+entry.charAt(0));
			str = entry;
		}
		
		reader.close();
		writer.close();
	}
}
