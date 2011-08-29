/**
 * 
 */
package datastructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.NameLUT;

import config.Settings;

/**
 * @author kto
 *
 */
public class IdentifierTest {

	private int _identifierNameId = -1;
	private int _fileNameId = -1;
	private Identifier _thisIdentifier = null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this._identifierNameId = 10;
		this._fileNameId = 2;
		
		_thisIdentifier = new Identifier(this._identifierNameId, this._fileNameId, Settings.PLACEHOLDER);
		
		try{
			_thisIdentifier = new Identifier(-1, 1, 1);
		} catch (IndexOutOfBoundsException ioobe){
			assertEquals("java.lang.IndexOutOfBoundsException", ioobe.getClass().getName());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link datastructure.Identifier#Identifier(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testIdentifier() {
		_thisIdentifier = new Identifier(this._identifierNameId, this._fileNameId, Settings.PLACEHOLDER);
		assertEquals(this._identifierNameId, _thisIdentifier.getFileNameId());
		assertEquals(this._fileNameId, _thisIdentifier.getNameId());
		assertEquals(Settings.PLACEHOLDER, _thisIdentifier.getType());
	}

	/**
	 * Test method for {@link datastructure.Identifier#setPos(java.util.TreeMap)}.
	 */
	@Test
	public void testSetPos() {
		TreeMap<Integer, ArrayList<Integer>> positions = new TreeMap<Integer, ArrayList<Integer>>();
		_thisIdentifier.setPos(positions);
		assertEquals(positions.toString(), _thisIdentifier.getPos().toString());
	}

	/**
	 * Test method for {@link datastructure.Identifier#getPos()}.
	 */
	@Test
	public void testGetPos() {
		TreeMap<Integer, ArrayList<Integer>> positions = new TreeMap<Integer, ArrayList<Integer>>();
		_thisIdentifier.setPos(positions);
		assertEquals(positions.toString(), _thisIdentifier.getPos().toString());
	}

	/**
	 * Test method for {@link datastructure.Identifier#setNameId(int)}.
	 */
	@Test
	public void testSetNameId() {
		this._identifierNameId = 2;
		_thisIdentifier.setNameId(this._identifierNameId);
		assertEquals(2, _thisIdentifier.getNameId());
	}

	/**
	 * Test method for {@link datastructure.Identifier#getNameId()}.
	 */
	@Test
	public void testGetNameId() {
		this._identifierNameId = 3;
		_thisIdentifier.setNameId(this._identifierNameId);
		assertEquals(3, _thisIdentifier.getNameId());
	}

	/**
	 * Test method for {@link datastructure.Identifier#setType(java.lang.String)}.
	 */
	@Test
	public void testSetType() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#getType()}.
	 */
	@Test
	public void testGetType() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#incNrOccurences()}.
	 */
	@Test
	public void testIncNrOccurences() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#setNrOccurences(int)}.
	 */
	@Test
	public void testSetNrOccurences() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#getNrOccurences()}.
	 */
	@Test
	public void testGetNrOccurences() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#setFileNameId(int)}.
	 */
	@Test
	public void testSetFileNameId() {
	}

	/**
	 * Test method for {@link datastructure.Identifier#getFileNameId()}.
	 */
	@Test
	public void testGetFileNameId() {
	}
}
