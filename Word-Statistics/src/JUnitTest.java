import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitTest {
	
	protected Scanner scan1;
	protected int lines;
	
	@Before
	public void setUp1()
	{
		lines = 0;
		try {
			scan1 = new Scanner( new FileReader("TestText.txt") );
			
			while( scan1.hasNextLine() )
			{
				scan1.nextLine();
				lines++;
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFileLineCount() {
		assertEquals("Number of lines: ", 13, lines);
	}
	
	@After
	public void tearDown1()
	{
		scan1.close();
	}
	
	protected int charNum;
	protected Scanner scan2;
	
	@Before
	public void setUp2()
	{
		try {
			scan2 = new Scanner( new FileReader("TestText.txt") );
			charNum = 0;
			while( scan2.hasNextLine() )
			{
				String line = scan2.nextLine();
				charNum = Driver.numOfChars(line, charNum);
			}	
			
			charNum = charNum + 13; //13 is the number of lines. This line is present after the while loop in processData method.
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileCharCount()
	{
		assertEquals("Number of lines: ", 450, charNum);
	}
	
	@After
	public void tearDown2()
	{
		scan2.close();
	}
	
	@Test
	public void testStringCharCount()
	{
		String testCharCount = "How many char are in this string? We will test if it is correct!";
		int noChar = 0;
		noChar = Driver.numOfChars(testCharCount, noChar);
		assertEquals("Number of char: ", 64, noChar);
	}
	
	
	protected HashMap<String, Integer> uniqueWordCountTest;
	protected HashMap<String, Integer> uniqueWordCountRes;
	protected ArrayList<String> arr;
	
	@Before
	public void setUp3()
	{
		uniqueWordCountTest = new HashMap<String, Integer>();
		uniqueWordCountRes = new HashMap<String, Integer>();
		arr = new ArrayList<String>();
		
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
	}
	
	@Test
	public void testWordFrequency()
	{
		assertTrue(uniqueWordCountTest.equals(uniqueWordCountRes));
	}
	
	protected HashMap<Integer, String> replacedLinesTest;
	protected HashMap<Integer, String> replacedLinesRes;
	protected Scanner scan;
	
	@Before
	public void setUp()
	{
		replacedLinesTest = new HashMap<Integer, String>();
		replacedLinesTest.put(1, "This is a result file that we will use for junit Testing!");
		replacedLinesTest.put(2, "Test should not match lowercase result for the replace");
		
		replacedLinesRes = new HashMap<Integer, String>();
		try {
			scan = new Scanner( new FileReader("TestText.txt") );
			int lineNo = 0;
			while( scan.hasNextLine() )
			{
				String line = scan.nextLine();
				lineNo++;	
				replacedLinesRes = Driver.replaceWord(lineNo, line, "test", "result", replacedLinesRes);
			}	
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReplace()
	{
		assertTrue(replacedLinesTest.equals(replacedLinesRes));
	}
	
	@After
	public void tearDown()
	{
		scan.close();
	}
}
