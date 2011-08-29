/**
 * 
 */
package datastructure;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Settings;

/**
 * @author kto
 *
 */
public class AppIdentifiersTest {

	private AppIdentifiers _appIdentifiers = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this._appIdentifiers = new AppIdentifiers(Settings.INPUTFIELD);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link datastructure.AppIdentifiers#AppIdentifiers(int)}.
	 */
	@Test
	public void testAppIdentifiers() {		
		assertEquals(Settings.INPUTFIELD, this._appIdentifiers.get_identifierType());
	}
	
	/**
	 * Test method for {@link datastructure.AppIdentifiers#getAppIdentifiers()}.
	 */
	@Test
	public void testGetAppIdentifiers() {
	}

	/**
	 * Test method for {@link datastructure.AppIdentifiers#serialize()}.
	 */
	@Test
	public void testSerialize() {
	}

	/**
	 * Test method for {@link datastructure.AppIdentifiers#searchIdentifier(int, int, java.util.TreeMap)}.
	 */
	@Test
	public void testSearchIdentifier() {
	}

	/**
	 * Test method for {@link datastructure.AppIdentifiers#set_identifierType(int)}.
	 */
	@Test
	public void testSet_identifierType() {
	}

	/**
	 * Test method for {@link datastructure.AppIdentifiers#get_identifierType()}.
	 */
	@Test
	public void testGet_identifierType() {
	}
}
