/**
 * 
 */
package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.Settings;

/**
 * @author kto
 *
 */
public class FixedPatternMatcher {
	
	private String 	_patternStr 	= "";
	private Pattern _regexp		= null;
	private Matcher _matcher		= null;
		
	public FixedPatternMatcher(String patternStrIn){
		this._patternStr = patternStrIn;
		this._regexp = Pattern.compile(this._patternStr);
		this._matcher = this._regexp.matcher("");
	}

	/**
	 * Reset the internal matcher to a new string to match its pattern against.
	 * @param newStringToMatchPatternAgainst
	 */
	public void reset(String newStringToMatchPatternAgainst){
		this._matcher.reset(newStringToMatchPatternAgainst);
	}
	
	/**
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return this._matcher;
	}
	
	/**
	 * @return the regexp patternStr
	 */
	public Pattern getRegexpPattern(){
		return this._regexp;
	}

	/**
	 * @return the pattern string
	 */
	public String getPatternStr(){
		return this._patternStr;
	}
}
