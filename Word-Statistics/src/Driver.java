import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Driver {
	
	static HashMap<String, Integer> uniqueWordCount;
	static HashMap<Integer, String> replacedLines;
	
	public static void main(String[] args) {
		
		
		try {

			String patternWord = args[0];
			String replacementWord = args[1];
			
			for( int j = 2; j < args.length; j++)
			{
				
				File f = new File("text.txt");
				String path = f.getAbsolutePath();
				
				Scanner scan;
				scan = new Scanner( new FileReader(args[j]) );//
			
				replacedLines = new HashMap<Integer, String>();
				uniqueWordCount = new HashMap<String, Integer>();
				ArrayList<String> temp = new ArrayList<String>();

				int numOfLine = 0;
				int charNum = 0;
				
				while( scan.hasNextLine() )
				{
					String line = scan.nextLine();
					numOfLine++;
					replaceWord(numOfLine, line, patternWord, replacementWord);
					temp = parseLine(line);	
					charNum = numOfChars(line, charNum);
					countUniqueWords(temp);
				}
				charNum = charNum + numOfLine - 1;
				scan.close();

				printData(path,numOfLine, charNum);//args[j]
			}
		
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
	
	public static String replaceWord(int lineNo, String line, String patternWord, String replacementWord)
	{
		String updatedLine = line.replaceAll("\\b"+patternWord+"\\b", replacementWord);
		if(!line.equals(updatedLine))
			replacedLines.put(lineNo,updatedLine);
		return updatedLine;
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
		System.out.println("LINES WITH REPLACED WORDS:");
		for( Integer val : replacedLines.keySet())
		{
			System.out.println("\t" + "LINE NO. " + val + ": " + replacedLines.get(val).toUpperCase());
		}
		
	}
}	
