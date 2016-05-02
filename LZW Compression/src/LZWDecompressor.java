import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class LZWDecompressor {

	public static void decompress(String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader (infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		
		int byteLimit = Integer.parseInt(reader.read() + "" + reader.read());
		
		Hashtable<Integer, String> table = new Hashtable<Integer, String>(500);
		
		for (int i = 0; i <= 255; i++)
			table.put(i,""+(char)i);
		int counter = 256;
		
		StringBuffer binary = new StringBuffer();
		while (reader.ready())
			binary.append(Integer.toBinaryString(reader.read()));
		
		StringBuffer nums = new StringBuffer();
		for (int i = 0; i < nums.length()/8; i++)
			nums.append(Integer.parseInt(nums.substring(8*i, (i+1)*8), 2) + " ");
		
		Scanner scan = new Scanner(nums.toString());
		
		int code = scan.nextInt();
		
		String str = table.get(code);
		writer.print(str);
		
		String entry = "";
		double tableLimit = Math.pow(2, byteLimit);
		
		while(scan.hasNextInt())
		{
			code = scan.nextInt();
			if (!table.containsKey(code))
				entry = str + str.charAt(0);
			else
				entry = table.get(code);
			writer.print(entry);
			if(table.size() < tableLimit)
				table.put(counter++, str+entry.charAt(0));
			str = entry;
		}
		
		reader.close();
		writer.close();
		scan.close();
	}
}
