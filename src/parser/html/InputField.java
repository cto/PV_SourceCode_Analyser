/**
 * 
 */
package parser.html;

import java.util.ArrayList;
import java.util.regex.Matcher;

import datastructure.FileIdentifiers;
import parser.FixedPatternMatcher;
import utils.NameLUT;

/**
 * @author kto
 *
 */
public class InputField {
	
	//<input type="text" name = "one_input_name" value="">
	//<input[^>]+name\s*=\s*"\s*([a-zA-Z_0-9]+)\s*"[^>]*>
	private String 			_patternStr = "<input[^>]+name\\s*=\\s*\"\\s*([a-zA-Z_0-9]+)\\s*\"[^>]*>";
	private final Matcher 	_infiMatcher = new FixedPatternMatcher(this._patternStr).getMatcher();
	private FileIdentifiers _userInputNames = null;
	private final NameLUT	_nameLUT;
	
	public InputField(int fileNameId, ArrayList<String> inText, int identifierType, int fileType, NameLUT nameLUT){
		this._userInputNames =  new FileIdentifiers(fileNameId, identifierType, fileType);
		this._nameLUT = nameLUT;
		parseHTML(inText);
	}
	
	private void parseHTML(ArrayList<String> inText){
	
		int sz = inText.size();
		for (int i = 0; i < sz; i++)
		{
			this._infiMatcher.reset(inText.get(i));
			
			while(this._infiMatcher.find()){		
				String newIdentifierName = this._infiMatcher.group(1);
				this._nameLUT.add(newIdentifierName);
				this._userInputNames.add(this._nameLUT.getIndex(newIdentifierName), i + 1, this._infiMatcher.start(1));
			}
		}
		
//		System.out.println(userInputNames.serialize());
//		userInputNames.giveStats();
	}
}
