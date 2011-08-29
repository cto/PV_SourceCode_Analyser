/**
 * 
 */
package parser;

import java.util.ArrayList;
import java.util.regex.Matcher;

import utils.NameLUT;

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
	private final NameLUT			_nameLUT;
	
	private	int 					_sz = -1;
	
	
	public IdentifierFinder(int fileNameId, ArrayList<String> inText, String patternStr, int identifierType, int fileType, NameLUT nameLUT){
		this._identifiers = new FileIdentifiers(fileNameId, identifierType, fileType);
		this._patternStr = patternStr;
		this._identifierMatcher = new FixedPatternMatcher(this._patternStr).getMatcher();
		this._identifierType = identifierType;
		this._nameLUT = nameLUT;
		parse(inText);
	}
	
	private void parse(ArrayList<String> inText){

		if (inText == null){return;}
		
		_sz = inText.size();
		for (int i = 0; i < _sz; i++)
		{
			this._identifierMatcher.reset(inText.get(i));
			
			while(this._identifierMatcher.find()){						
				String newIdentifierName = this._identifierMatcher.group(1);
				this._nameLUT.add(newIdentifierName);
				this._identifiers.add(this._nameLUT.getIndex(newIdentifierName), i + 1, this._identifierMatcher.start(1));
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
