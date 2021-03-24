import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Driver {
	
	static HashMap<String, Integer> uniqueWordCount;

	public static void main(String[] args) {
		
		
		try {
			for( int j = 0; j < args.length; j++)
			{
				
				System.out.println(args[j].toUpperCase());
				
				Scanner scan;
				scan = new Scanner( new FileReader(args[j]) );
			
				uniqueWordCount = new HashMap<String, Integer>();
				ArrayList<String> temp = new ArrayList<String>();
		
				while( scan.hasNextLine() )
				{
					temp = parseLine(scan.nextLine());	
					countUniqueWords(temp);
				}
				scan.close();
				
				printHashmap();
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		

	}
	
	
	public static ArrayList<String> parseLine( String line )
	{
		String val = "";
		ArrayList<String> arr = new ArrayList<String>();		
		String[] tokens = line.split("[\\p{Punct}\\s]+");	
		for(int i = 0; i < tokens.length; i++ )
		{
			val = formatWord(tokens[i]);
			if( !val.isBlank() && !val.matches("-?(0|[1-9]\\d*)"))
				arr.add(val);
		}
		return arr;
	}
	
	public static String formatWord(String str)
	{
		String val = str.replaceAll("\\W", "").toLowerCase();
		return val;
	}
	
	public static void countUniqueWords(ArrayList<String> temp)
	{
		int count;
		
		for(int i = 0; i < temp.size(); i++ )
		{
			Set<String> keys = uniqueWordCount.keySet();
			if( keys.contains(temp.get(i)))
				count = uniqueWordCount.get(temp.get(i)) + 1;
			else
				count = 1;
				
			uniqueWordCount.put(temp.get(i), count);
		}

	}

	public static void printHashmap()
	{
		for( String value : uniqueWordCount.keySet())
		{
			System.out.println(value.toUpperCase() + ": " + uniqueWordCount.get(value));
		}
		
	}
}	
