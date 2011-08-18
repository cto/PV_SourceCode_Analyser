/**
 * 
 */
package datastructure;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author kto
 *
 */
public class Identifier {
	private String _fileName = "";
	private String _identifierName = "";
	private String _identifierType = ""; // Placeholder inside a "...placeholder()", or inside a "print...()", or a inputfield, ...
	private int _nrOccurences = 0;  // Number of occurences of this identifier in a FileIdentifier, not sure if this is a bad design
	
	private TreeMap<Integer, ArrayList<Integer>> _positions = new TreeMap<Integer, ArrayList<Integer>>();
	
	public Identifier(String identifierName, String fileName){
		this._fileName = fileName;
		this._identifierName = identifierName;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPos(TreeMap<Integer, ArrayList<Integer>> positions) {
		this._positions = positions;
	}

	/**
	 * @return the positions
	 */
	public TreeMap<Integer, ArrayList<Integer>> getPos() {
		return this._positions;
	}

	/**
	 * @param identifierName the identifierName to set
	 */
	public void setName(String name) {
		this._identifierName = name;
	}

	/**
	 * @return the identifierName
	 */
	public String getName() {
		return this._identifierName;
	}

	/**
	 * @param identifierType the identifierType to set
	 */
	public void setType(String type) {
		this._identifierType = type;
	}

	/**
	 * @return the identifierType
	 */
	public String getType() {
		return this._identifierType;
	}

	/**
	 * Increases the nrOccurences to one
	 */
	public void incNrOccurences(){
		this._nrOccurences++;
	}
	
	/**
	 * @param nrOccurences the nrOccurences to set
	 */
	public void setNrOccurences(int nrOccurences) {
		this._nrOccurences = nrOccurences;
	}

	/**
	 * @return the nrOccurences
	 */
	public int getNrOccurences() {
		return this._nrOccurences;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this._fileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this._fileName;
	}	
}