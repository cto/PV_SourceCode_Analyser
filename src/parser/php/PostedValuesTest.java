/**
 * 
 */
package parser.php;


import org.junit.After;
import org.junit.Before;

import utils.NameLUT;

import config.Settings;

import datastructure.FileIdentifiers;

/**
 * @author kto
 *
 */
public class PostedValuesTest {

	private PostedValues _pova = null;
	private FileIdentifiers _fiid = null;
	private int _fileNameId = -1;
	private int _identifierType = -1;
	private int _fileType = -1;
	private NameLUT	_povaNameLUT = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		_fileNameId = 10;
		_identifierType = Settings.IDENTIFIER_NAME;
		_fileType = Settings.PHP;
		_fiid = new FileIdentifiers(_fileNameId, _identifierType, _fileType);
		
		_povaNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
//		_pova = new PostedValues(_fiid, inText, _povaNameLUT);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

}
