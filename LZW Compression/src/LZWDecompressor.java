import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class LZWDecompressor {

	public static void decompress(String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader (infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		String num = reader.readLine();
		int byteLimit = Integer.parseInt(num);
		
		Hashtable<Integer, String> table = new Hashtable<Integer, String>(500);
		
		for (int i = 0; i <= 255; i++)
			table.put(i,""+(char)i);
		int counter = 256;
		
		StringBuffer binary = new StringBuffer();
		while (reader.ready())
		{
			String temp = Integer.toBinaryString(reader.read());
			binary.append(makeZeros(8 - temp.length()) + temp);
		}
		
		StringBuffer nums = new StringBuffer();
		int i;
		for (i = 0; i < binary.length()/byteLimit; i++)
			nums.append(Integer.parseInt(binary.substring(byteLimit*i, (i+1)*byteLimit), 2) + " ");
		if (binary.length() % byteLimit != 0)
			nums.append(Integer.parseInt(binary.substring(i*byteLimit), 2));
		
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
	
	private static String makeZeros (int n)
	{
		String zeros = "";
		for (int i = 0; i < n; i++)
			zeros += "0";
		return zeros;
	}
}
