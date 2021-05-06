import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Driver {
	
	public static void main(String[] args) {
		
		String patternWord = args[0];
		String replacementWord = args[1];
		
			
		for( int j = 2; j < args.length; j++)
		{

			processFile(args[j], patternWord, replacementWord);

		}	

	}
	
	public static void processFile(String arg, String patternWord, String replacementWord)
	{
		try {			
			Scanner scan;
			scan = new Scanner( new FileReader(arg) );//
			HashMap<Integer, String> replacedLines = new HashMap<Integer, String>();
			HashMap<String, Integer> uniqueWordCount = new HashMap<String, Integer>();
			int numOfLine = 0;
			int charNum = 0;
			ArrayList<String> temp = new ArrayList<String>();
			
			while( scan.hasNextLine() )
			{
				String line = scan.nextLine();
				numOfLine++;
				replacedLines = replaceWord(numOfLine, line, patternWord, replacementWord, replacedLines);
				temp = parseLine(line);	
				charNum = numOfChars(line, charNum);
				uniqueWordCount = countUniqueWords(temp, uniqueWordCount);
			}
			
			charNum = charNum + numOfLine;
			scan.close();
	
			printData(arg,numOfLine, charNum, uniqueWordCount, replacedLines);
		} catch (IOException e) {
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
			val = removeNonWordChar(tokens[i]);
			if( !val.isBlank() && !val.matches("-?(0|[1-9]\\d*)"))
				arr.add(val);
		}
		return arr;
	}
	
	public static String removeNonWordChar(String str)
	{
		String val = str.replaceAll("\\W", "").toLowerCase();
		return val;
	}
	
	public static HashMap<String, Integer> countUniqueWords(ArrayList<String> temp, HashMap<String, Integer> uniqueWordCount)
	{
		int count = 0;
		Set<String> keys;

		for(int i = 0; i < temp.size(); i++ )
		{
			if(uniqueWordCount.isEmpty())
			{
				count = 1;
			}
			else
			{
				keys = uniqueWordCount.keySet();
				if( keys.contains(temp.get(i)))
					count = uniqueWordCount.get(temp.get(i)) + 1;
				else
					count = 1;
			}
					
			uniqueWordCount.put(temp.get(i), count);
		}
		
		return uniqueWordCount;
	}
	
	public static HashMap<Integer, String> replaceWord(int lineNo, String line, String patternWord, String replacementWord, HashMap<Integer,String> replacedLines)
	{
		String updatedLine = line.replaceAll("\\b"+patternWord+"\\b", replacementWord);
		if(!line.equals(updatedLine))
			replacedLines.put(lineNo,updatedLine);
		return replacedLines;
	}

	public static void printData(String filename, int line, int charNum, HashMap<String,Integer> uniqueWordCount, HashMap<Integer,String> replacedLines)
	{
		System.out.println("FILENAME: " + filename.toUpperCase());
		System.out.println("NO. OF LINES: " + line);
		System.out.println("NO. OF CHARACTERS: " + charNum);
		System.out.println("UNIQUE WORD FREQUENCY:");
		for( String value : uniqueWordCount.keySet())
		{
			System.out.println("\t" + value.toUpperCase() + ": " + uniqueWordCount.get(value));
		}
		
		if(replacedLines.isEmpty())
		{
			System.out.println("NO WORDS REPLACED");
		}
		else
		{
			System.out.println("LINES WITH REPLACED WORDS:");
			for( Integer val : replacedLines.keySet())
			{
				System.out.println("\t" + "LINE NO. " + val + ": " + replacedLines.get(val));
			}
		}
	}
}		
