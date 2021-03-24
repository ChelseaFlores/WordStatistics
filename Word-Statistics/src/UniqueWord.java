import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class UniqueWord {
	
	private String word;
	private int count;
	
	public UniqueWord(String word, int count)
	{
		this.word = word;
		this.count = count;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
/*

	public ArrayList<String> parseLine( String line )
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
	
	public void countUniqueWords(ArrayList<String> array)
	{
		for(int i = 0; i < array.size(); i++ )
		{
			this.uniqueWords.add(array.get(i));
		}
	}

	public void printSet()
	{
		Set<String> printval = getUniqueWords();
		for( String value : printval)
		{
			System.out.println(value);
		}
		
	}
	
	public Set<String> getUniqueWords() {
		return this.uniqueWords;
	}

	public void setUniqueWords(String word) {
		this.uniqueWords = uniqueWords;
	}
	*/
}
