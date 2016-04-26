import java.io.*;
import java.util.ArrayList;

public class LZWDecompressor {

	public static void decompress(String infilename, String outfilename) throws FileNotFoundException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader (infilename));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outfilename)));
		
		
	}
}
