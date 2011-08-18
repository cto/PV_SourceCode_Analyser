/**
 * 
 */
package io;

import config.Settings;

import datastructure.FileIdentifiers;

import parser.Parser;

/**
 * @author kto
 *
 */
public class PHPFile extends PVFileBase implements PVFileInterface {

	private final   Parser				_parser;
	private final   FileIdentifiers 	_allPlaceholders;
	
	public PHPFile(String fName) throws Exception
	{
		super(fName);
		this._allPlaceholders = new FileIdentifiers(fName, Settings.PLACEHOLDER);
		this._parser = new Parser(this._allPlaceholders, super.getContentAL());
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
