/**
 * 
 */
package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;

import datastructure.FileIdentifiers;

/**
 * @author kto
 *
 */
public class IdentifierFinder {

	private final String			_patternStr;
	private final Matcher 			_identifierMatcher;
	private final FileIdentifiers 	_identifiers;
	private final int 				_identifierType;
	private	int 					_sz = 0;
	
	public IdentifierFinder(String fileName, ArrayList<String> inText, String patternStr, int identifierType){
		this._identifiers = new FileIdentifiers(fileName, identifierType);
		this._patternStr = patternStr;
		this._identifierMatcher = new FixedPatternMatcher(this._patternStr).getMatcher();
		this._identifierType = identifierType;
		
		parse(inText);
	}
	
	private void parse(ArrayList<String> inText){

		if (inText == null){return;}
		
		_sz = inText.size();
		for (int i = 0; i < _sz; i++)
		{
			this._identifierMatcher.reset(inText.get(i));
			
			while(this._identifierMatcher.find()){							
				this._identifiers.add(this._identifierMatcher.group(1), i + 1, this._identifierMatcher.start(1));
			}
		}
	}

	/**
	 * @return the identifiers
	 */
	public FileIdentifiers getIdentifiers() {
		return this._identifiers;
	}

	/**
	 * @return the _identifierType
	 */
	public int get_identifierType() {
		return _identifierType;
	}
}
