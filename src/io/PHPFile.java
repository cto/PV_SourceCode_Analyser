/**
 * 
 */
package io;

import config.Settings;

import datastructure.FileIdentifiers;

import parser.Parser;
import utils.NameLUT;

/**
 * @author kto
 *
 */
public class PHPFile extends PVFileBase implements PVFileInterface {

	private final   Parser				_parser;
	private final   FileIdentifiers 	_allPlaceholders;
			
	public PHPFile(int fNameId, int fileType, NameLUT nameLUT) throws Exception
	{
		super(nameLUT.getName(fNameId));

		this._allPlaceholders = new FileIdentifiers(fNameId, Settings.PLACEHOLDER, fileType);  // KKKTTT: this could be an error for the NullPointerException
		
		this._parser = new Parser(this._allPlaceholders, super.getContentAL(), nameLUT);
	}

	/**
	 * @return the isFile
	 */
	public boolean isFile() {
		return super.isFile();
	}
	
	public FileIdentifiers getPostedValues(){
		return this._parser.getAllPostedValues();
	}
	
	public FileIdentifiers getPlaceHolders(){
		return this._allPlaceholders;
	}
	
	public String getFileName(){
		return super.getFileName();
	}
}
