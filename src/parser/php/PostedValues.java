/**
 * 
 */
package parser.php;

import datastructure.FileIdentifiers;

import java.util.ArrayList;
import java.util.regex.Matcher;

import config.Settings;

import parser.FixedPatternMatcher;
import utils.NameLUT;

/**
 * @author kto
 * Adds to a FileIdentifiers all the posted values inside an "inText" 
 */
public class PostedValues {

	private final String		_povaPatternStr 	= "\\$posted_values\\[['|\"]" + Settings.IDENTIFIER_NAME_PAT_STR + "['|\"]\\]";
	private final Matcher 		_povaMatcher		= new FixedPatternMatcher(_povaPatternStr).getMatcher();
	private final Matcher 		_povaAntiMatcher	= new FixedPatternMatcher(_povaPatternStr + "\\s+=").getMatcher();
	private FileIdentifiers 	_allPostedValues 	= null;
	private ArrayList<String> 	_antiUserInputNames = null;
	
	private NameLUT _povaNameLUT = null;
	
	//Adds to a FileIdentifiers all the posted values inside an "inText"
	public PostedValues(FileIdentifiers allPostedValues, ArrayList<String> inText, NameLUT nameLUT) throws Exception{

		this._allPostedValues = allPostedValues;
		this._povaNameLUT = nameLUT;
		parsePHP(inText);
	}
	
	private void parsePHP(ArrayList<String> inText) throws Exception{
		this._antiUserInputNames = new ArrayList<String>();

		String lineStr = null;

		int sz = inText.size();
		for (int i = 0; i < sz; i++)
		{
			lineStr = inText.get(i);			 
			
			this._povaAntiMatcher.reset(lineStr);			
			while(this._povaAntiMatcher.find()){
				
				String matchedAntiInputName = this._povaAntiMatcher.group(1);
				if ( ! this._antiUserInputNames.contains(matchedAntiInputName)){
					this._antiUserInputNames.add(matchedAntiInputName);
				}
			}

			this._povaMatcher.reset(lineStr);
			
			while (this._povaMatcher.find()){
				
				String matchedInputName = this._povaMatcher.group(1);
				this._povaNameLUT.add(matchedInputName);
				
				if ( 	! this._allPostedValues.getFileIdentifiers().containsKey(this._povaNameLUT.getIndex(matchedInputName))
					 && ! _antiUserInputNames.contains(matchedInputName)){
					
					this._allPostedValues.add(this._povaNameLUT.getIndex(matchedInputName), i + 1, _povaMatcher.start(1));
				}
			}
		}
	}
	
	public FileIdentifiers getAllPostedValues(){
		return this._allPostedValues;
	}
}