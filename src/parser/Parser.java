/**
 * 
 */
package parser;

import java.util.ArrayList;

import parser.php.PlaceHolderFunction;
import parser.php.PostedValues;
import utils.NameLUT;
import datastructure.FileIdentifiers;

/**
 * @author kto
 *
 */
public class Parser {
                                 
	private PlaceHolderFunction _plhoFunc	= null;
	private PostedValues 		_pova = null;

	public Parser(FileIdentifiers fileIdentifiers, ArrayList<String> inText, NameLUT nameLUT) throws Exception
	{
		if (null == inText){
			throw new Exception("Text to be parse is null");
		}
		
		this._plhoFunc = new PlaceHolderFunction(inText);
		this._pova = new PostedValues(fileIdentifiers, inText, nameLUT);
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
		return this._plhoFunc.getPlhoName(inText);
	}	
	/**
	 * Return the body of the place holder function inside the PHP file
	 * @param inText
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String> getPlhoFunctionLines() throws Exception
	{	
		return this._plhoFunc.getPlhoFuncLines();
	}
	
	public FileIdentifiers getAllPostedValues(){
		return this._pova.getAllPostedValues();
	}
}
