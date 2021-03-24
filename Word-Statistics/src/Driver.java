import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

public class Driver {
	
	static HashMap<String, Integer> uniqueWordCount;
	
	public static void main(String[] args) {
		
		
		try {
			for( int j = 0; j < args.length; j++)
			{
				
				
				
				Scanner scan;
				scan = new Scanner( new FileReader(args[j]) );
			
				uniqueWordCount = new HashMap<String, Integer>();
				ArrayList<String> temp = new ArrayList<String>();
				int line = 0;
				int charNum = 0;
				
				while( scan.hasNextLine() )
				{
					String fileLine = scan.nextLine();
					temp = parseLine(fileLine);	
					charNum = numOfChars(fileLine, charNum);
					countUniqueWords(temp);
					line++;
				}
				charNum = charNum + line - 1;
				scan.close();

				printData(args[j],line, charNum);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		

	}
	
	public static int numOfChars( String line, int charNum )
	{
		int val = charNum + line.length();
		return val;
	}
	
	
	public static ArrayList<String> parseLine( String line )
	{
		String val = "";
		ArrayList<String> arr = new ArrayList<String>();		
		String[] tokens = line.split("[\\p{Punct}\\s]+");	
		for(int i = 0; i < tokens.length; i++ )
		{
			val = removeWhiteSpace(tokens[i]);
			if( !val.isBlank() && !val.matches("-?(0|[1-9]\\d*)"))
				arr.add(val);
		}
		return arr;
	}
	
	public static String removeWhiteSpace(String str)
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

	public static void printData(String filename, int line, int charNum)
	{
		System.out.println("FILENAME: " + filename.toUpperCase());
		System.out.println("NO. OF LINES: " + line);
		System.out.println("NO. OF CHARACTERS: " + charNum);
		System.out.println("UNIQUE WORD FREQUENCY:");
		for( String value : uniqueWordCount.keySet())
		{
			System.out.println("\t" + value.toUpperCase() + ": " + uniqueWordCount.get(value));
		}
		
	}
}	
