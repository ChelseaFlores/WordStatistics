import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void testFileLineCount() {
		try {
			Scanner scan = new Scanner( new FileReader("TestText.txt") );
			int lines = 0;
			while( scan.hasNextLine() )
			{
				scan.nextLine();
				lines++;
			}	
			scan.close();
			assertEquals("Number of lines: ", 13, lines);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileCharCount()
	{
		try {
			Scanner scan = new Scanner( new FileReader("TestText.txt") );
			int charNum = 0;
			while( scan.hasNextLine() )
			{
				String line = scan.nextLine();
				charNum = Driver.numOfChars(line, charNum);
			}	
			
			charNum = charNum + 13; //13 is the number of lines. This line is present after the while loop in processData method.
			
			scan.close();
			assertEquals("Number of lines: ", 450, charNum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStringCharCount()
	{
		String testCharCount = "How many char are in this string? We will test if it is correct!";
		int noChar = 0;
		noChar = Driver.numOfChars(testCharCount, noChar);
		assertEquals("Number of char: ", 64, noChar);
	}
	
	@Test
	public void testWordFrequency()
	{
		HashMap<String, Integer> uniqueWordCountTest = new HashMap<String, Integer>();
		HashMap<String, Integer> uniqueWordCountRes = new HashMap<String, Integer>();
		ArrayList<String> arr = new ArrayList<String>();
		
		arr.add("A");
		arr.add("A");
		arr.add("AFFECT");
		arr.add("AND");
		arr.add("ANY");
		arr.add("ARE");
		arr.add("BEHAVE");
		arr.add("BEHAVE");
		arr.add("BEHAVE");
		arr.add("BREAKS");
		arr.add("BREAKS");
		arr.add("BREAKS");
		arr.add("BREAKS");
		
		uniqueWordCountRes = Driver.countUniqueWords(arr, uniqueWordCountRes);
		
		uniqueWordCountTest.put("A", 2);
		uniqueWordCountTest.put("AFFECT", 1);
		uniqueWordCountTest.put("AND", 1);
		uniqueWordCountTest.put("ANY", 1);
		uniqueWordCountTest.put("ARE", 1);
		uniqueWordCountTest.put("BEHAVE", 3);
		uniqueWordCountTest.put("BREAKS", 4);
		
		assertTrue(uniqueWordCountTest.equals(uniqueWordCountRes));
	
	}
	
	@Test
	public void testReplace()
	{
		HashMap<Integer, String> replacedLinesTest = new HashMap<Integer, String>();
		replacedLinesTest.put(1, "This is a result file that we will use for junit Testing!");
		replacedLinesTest.put(2, "Test should not match lowercase result for the replace");
		
		HashMap<Integer, String> replacedLinesRes = new HashMap<Integer, String>();
		try {
			Scanner scan = new Scanner( new FileReader("TestText.txt") );
			int lineNo = 0;
			while( scan.hasNextLine() )
			{
				String line = scan.nextLine();
				lineNo++;	
				replacedLinesRes = Driver.replaceWord(lineNo, line, "test", "result", replacedLinesRes);
			}	
			scan.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertTrue(replacedLinesTest.equals(replacedLinesRes));
	}
	

}
