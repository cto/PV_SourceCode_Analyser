/**
 * 
 */
package parser.php;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;

import parser.FixedPatternMatcher;

import config.Settings;

/**
 * @author kto
 * Deal with functions that replace a placeholder to a string value
 * Currently, only deal with functions "...placeholder"
 * Will think about "print_..." functions later
 */
public class PlaceHolderFunction {
	
	private String 				_inputParam = "";
	private ArrayList<String> 	_plhoFuncLines = null;                                      				//"function global_placeholder ( $name )
	private String 				_plhoFuncPatternStr = "function\\s+[a-zA-Z_0-9]*placeholder\\s*\\(\\s*\\$" + Settings.IDENTIFIER_NAME_PAT_STR + "\\s*\\)";
	private String 				_printFuncPatternStr = "function\\s+print\\s+[a-zA-Z_0-9]+";
	private final Matcher 		_plhoFuncMatcher	= new FixedPatternMatcher(_plhoFuncPatternStr).getMatcher();
	
	                                  				//"case "A_PLACEHOLDER_NAME" :
	private String 				_plhoLinePatternStr = "case\\s+['|\"]"+Settings.IDENTIFIER_NAME_PAT_STR+"['|\"]\\s*:";
	private final Matcher 		_plhoLineMatcher	= new FixedPatternMatcher(_plhoLinePatternStr).getMatcher();

	private int 				_bracketCounter 				= 0;
	private boolean 			_hasMetOpeningBracket 	= false;
	private boolean 			_hasMetFunctionHeader	= false;

	public PlaceHolderFunction(ArrayList<String> inText) throws Exception{
		this._plhoFuncLines = new ArrayList<String>();
		parsePHP(inText);
	}

	private void parsePHP(ArrayList<String> inText) throws Exception{
		this._bracketCounter 			= 0;
		this._hasMetOpeningBracket 	= false;
		this._hasMetFunctionHeader	= false;

		String lineStr = null;

		int sz = inText.size();
		for (int i = 0; i < sz; i++)
		{
			lineStr = inText.get(i);

			if ( ! this._hasMetFunctionHeader){
				this._plhoFuncMatcher.reset(lineStr);
				
				if ( ! this._plhoFuncMatcher.find()){
					continue;
				}
				
				this._inputParam = this._plhoFuncMatcher.group(1);
				
				this._hasMetFunctionHeader = true;
			}

			this._plhoFuncLines.add(lineStr);
			
			if (lineStr.contains(Settings.OCB)){
				this._bracketCounter++;
				this._hasMetOpeningBracket = true;
			}
			
			if (lineStr.contains(Settings.CCB)){
				this._bracketCounter--;
			}			
			
			if (this._hasMetOpeningBracket && (this._bracketCounter == 0)){
				break;
			}
		}		
	}
	
	/**
	 * @param plhoFuncLines the plhoFuncLines to set
	 */
	public void setPhFunctionLines(ArrayList<String> plhoFuncLines) {
		this._plhoFuncLines = plhoFuncLines;
	}

	/**
	 * @return the plhoFuncLines
	 */
	public ArrayList<String> getPlhoFuncLines() {
		return this._plhoFuncLines;
	}
	
	/**
	 * @return the inputParam
	 */
	public String getInputParam() {
		return this._inputParam;
	}

	/**
	 * @param inputParam the inputParam to set
	 */
	public void setInputParam(String inputParam) {
		this._inputParam = inputParam;
	}
	
	/**
	 * @TODO
	 * Determine if current line is in the region within which if you return a value, that value would be the value of a HTML plho 
	 * @param iLine
	 * @return
	 */
	public boolean isAnHTMLPlaceHolder(int iLine){
		return true;
	}
	
	/**
	 * Given a text string (expected to be a line), return the name of the place holder which follows the following pattern
	 * 
	 * "case 'PLACEHOLDER_NAME':"
	 * or
	 * "case "PLACEHOLDER_NAME":"
	 * 
	 * @param inText
	 * @return
	 * @throws Exception 
	 */
	public String getPlhoName(String inText) throws Exception
	{
		this._plhoLineMatcher.reset(inText);
		
		if ( ! this._plhoLineMatcher.find()){
			return "";
		}

		String thePHPattern = this._plhoLineMatcher.group(1);

		return thePHPattern;
	}

}
