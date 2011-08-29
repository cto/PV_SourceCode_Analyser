/**
 * 
 */
package io;

import java.util.ArrayList;

import config.Settings;
import datastructure.FileIdentifiers;
import parser.IdentifierFinder;
import utils.NameLUT;

/**
 * @author kto
 *
 */

//TODO: do some getInstance with Singleton here, a file like this needs to be unique ?
public class HTMLFile extends PVFileBase implements PVFileInterface{
	
	private	static 	final	String _HTML_PH_BEGIN	= "@[";
	private static 	final 	String _HTML_PH_END 	= "]@";
	
	private 		final	IdentifierFinder _inputFieldsFinder; 
	private 		final	IdentifierFinder _placeHoldersFinder;
	
	public HTMLFile(int fNameId, int fileType, NameLUT nameLUT){
		super(nameLUT.getName(fNameId));
		this._inputFieldsFinder = new IdentifierFinder(fNameId, super.getContentAL(), Settings.HTML_INPUTFIELD_PATTERN_STR, Settings.PLACEHOLDER, fileType, nameLUT);
		this._placeHoldersFinder = new IdentifierFinder(fNameId, super.getContentAL(), Settings.HTML_PLHO_PATTERN_STR, Settings.INPUTFIELD, fileType, nameLUT);
	}
	
	public boolean isPlhoFound(String plhoName){
		return this.getContentInString().contains(_HTML_PH_BEGIN + plhoName + _HTML_PH_END);
	}
	
	public boolean isUserInputNameFound(String userInputName){
		return this.getContentInString().contains("\"" + userInputName + "\"");
	}
	
	public int[] whereIsThisPlhoUsed(String plhoName){
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public FileIdentifiers getInputFields(){
		return this._inputFieldsFinder.getIdentifiers();
	}
	
	public FileIdentifiers getPlaceHolders(){
		return this._placeHoldersFinder.getIdentifiers();
	}
	
	/**
	 * @return the isFile
	 */
	public boolean isFile() {
		return super.isFile();
	}
	
	public String getFileName(){
		return super.getFileName();
	}
}
