package utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Settings;

public class NameLUTTest {

	private NameLUT _fileNameLUT;
	private NameLUT _identifierNameLUT;
	
	@Before
	public void setUp() throws Exception {
		this._fileNameLUT = new NameLUT(Settings.FILE_NAME);
		this._identifierNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNameLUT() {
		assertNotNull(this._fileNameLUT);
		assertNotNull(this._identifierNameLUT);
	}

	@Test
	public void testAdd() {
		//FileName
		assertEquals(true, this._fileNameLUT.add("/home/kto/file1.php"));
		assertEquals(true, this._fileNameLUT.add("/home/kto/file2.php"));
		assertEquals(false, this._fileNameLUT.add("/home/kto/file2.php"));
		
		//IdentifierName
		assertEquals(true, this._identifierNameLUT.add("PTNT_ID"));
		assertEquals(true, this._identifierNameLUT.add("ASEN_ID"));
		assertEquals(false, this._identifierNameLUT.add("ASEN_ID"));		
	}

	@Test
	public void testGetName() {
		//FileName
		this._fileNameLUT.add("/home/kto/file1.php");
		this._fileNameLUT.add("/home/kto/file2.php");

		assertEquals("/home/kto/file1.php", this._fileNameLUT.getName(0));
		assertEquals("/home/kto/file2.php", this._fileNameLUT.getName(1));
		
		try{
			assertEquals(null, this._fileNameLUT.getName(2));
			assertEquals(null, this._fileNameLUT.getName(-1));
		}catch(IndexOutOfBoundsException ioobe){
			ioobe.printStackTrace();
		}
		
		//IdentifierName
		this._identifierNameLUT.add("PTNT_ID1");
		this._identifierNameLUT.add("PTNT_ID2");
		this._identifierNameLUT.add("PTNT_ID3");
		this._identifierNameLUT.add("PTNT_ID3");

		assertEquals("PTNT_ID1", this._identifierNameLUT.getName(0));
		assertEquals("PTNT_ID2", this._identifierNameLUT.getName(1));
		assertEquals("PTNT_ID3", this._identifierNameLUT.getName(2));
		
		try{
			assertEquals(null, this._identifierNameLUT.getName(3));
			assertEquals(null, this._identifierNameLUT.getName(-1));
		}catch(IndexOutOfBoundsException ioobe){
			ioobe.printStackTrace();
		}
	}

	@Test
	public void testGetIndex() {
		
		//FileName
		this._fileNameLUT.add("/home/kto/file1.php");
		this._fileNameLUT.add("/home/kto/file2.php");

		assertEquals(0, this._fileNameLUT.getIndex("/home/kto/file1.php"));
		assertEquals(1, this._fileNameLUT.getIndex("/home/kto/file2.php"));
				
		try{
			assertNull(this._fileNameLUT.getIndex("non existant file name")); // On NullPointerException, Eclipse would complain about "Source not found"
																		  // To avoid that, do the following 3 things:
//			1. Clicked on the "Breakpoints" window at the bottom of the debugging screen
//			2. Right clicked "NullPointerException"
//			3. Unchecked "Caught"
		} catch (NullPointerException npe){
			npe.printStackTrace();
		} 
		
		//IdentifierName
		this._identifierNameLUT.add("PTNT_ID1.php");
		this._identifierNameLUT.add("PTNT_ID2.php");

		assertEquals(0, this._identifierNameLUT.getIndex("PTNT_ID1.php"));
		assertEquals(1, this._identifierNameLUT.getIndex("PTNT_ID2.php"));
				
		try{
			assertNull(this._identifierNameLUT.getIndex("non existant file name")); // On NullPointerException, Eclipse would complain about "Source not found"
																		  // To avoid that, do the following 3 things:
//			1. Clicked on the "Breakpoints" window at the bottom of the debugging screen
//			2. Right clicked "NullPointerException"
//			3. Unchecked "Caught"
		} catch (NullPointerException npe){
			npe.printStackTrace();
		} 
	}

	@Test
	public void testGetLUTSize() {
		this._fileNameLUT.add("/home/kto/file1.php");
		
		// Repeat 4 times
		this._fileNameLUT.add("/home/kto/file2.php");
		this._fileNameLUT.add("/home/kto/file2.php");
		this._fileNameLUT.add("/home/kto/file2.php");
		this._fileNameLUT.add("/home/kto/file2.php");
		
		assertEquals(2, this._fileNameLUT.getLUTSize());
	}
}
