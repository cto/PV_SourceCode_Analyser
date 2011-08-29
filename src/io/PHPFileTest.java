/**
 * 
 */
package io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Settings;
import datastructure.FileIdentifiers;

import utils.NameLUT;

/**
 * @author kto
 *
 */
public class PHPFileTest {

	private PHPFile _phpFile = null;
	private String	_phpFileName = "";
	private NameLUT	_nameLUT = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this._nameLUT = new NameLUT(Settings.FILE_NAME);
		this._phpFileName = "/home/kto/PV/src/workspace_sites/portavita_head/sites/portavita.nl/pv_phps/antistolling/wijzigen_instellingen_prikplaats/wijzigen_instellingen_prikplaats.php";
		this._nameLUT.add(this._phpFileName);
		this._phpFile = new PHPFile(this._nameLUT.getIndex(this._phpFileName), Settings.PHP, this._nameLUT);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link io.PHPFile#isFile()}.
	 */
	@Test
	public void testIsFile() {
		assertEquals(true, this._phpFile.isFile());
	}

	/**
	 * Test method for {@link io.PHPFile#getFileName()}.
	 */
	@Test
	public void testGetFileName() {
	}

	/**
	 * Test method for {@link io.PHPFile#PHPFile(int, int, NameLUT)}.
	 */
	@Test
	public void testPHPFile() throws Exception {
		
		try{
			this._phpFile = new PHPFile(-3, Settings.PHP, this._nameLUT);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link io.PHPFile#getPostedValues()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetPostedValues() throws Exception {
		this._phpFile = new PHPFile(this._nameLUT.getIndex(this._phpFileName), Settings.PHP, this._nameLUT);
		
		NameLUT povaNameLUT = new NameLUT(Settings.FILE_NAME);

		povaNameLUT.add(this._phpFileName);

		FileIdentifiers povaFiid = this._phpFile.getPostedValues();

		assertEquals(povaNameLUT.getIndex(this._phpFileName), povaFiid.getFileNameId());

		assertEquals(Settings.PHP, povaFiid.getFileType());
		
		// In this file "sites/portavita.nl/pv_phps/antistolling/wijzigen_instellingen_prikplaats/wijzigen_instellingen_prikplaats.php"
		// There are 5 posted_values
		assertEquals(5, povaFiid.getFileIdentifiers().size());
	}

	/**
	 * Test method for {@link io.PHPFile#getPlaceHolders()}.
	 */
	@Test
	public void testGetPlaceHolders() {
		
	}

}
