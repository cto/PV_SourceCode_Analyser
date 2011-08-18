/**
 * 
 */
package parser.html;

import java.util.ArrayList;
import java.util.regex.Matcher;

import datastructure.FileIdentifiers;
import parser.FixedPatternMatcher;

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
	
	public InputField(String fileName, ArrayList<String> inText, int identifierType){
		this._userInputNames =  new FileIdentifiers(fileName, identifierType);
		parseHTML(inText);
	}
	
	private void parseHTML(ArrayList<String> inText){
	
		int sz = inText.size();
		for (int i = 0; i < sz; i++)
		{
			this._infiMatcher.reset(inText.get(i));
			
			while(this._infiMatcher.find()){							
				this._userInputNames.add(this._infiMatcher.group(1), i + 1, this._infiMatcher.start(1));
			}
		}
		
//		System.out.println(userInputNames.serialize());
//		userInputNames.giveStats();
	}
}
