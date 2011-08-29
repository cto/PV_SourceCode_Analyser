/**
 * 
 */
package datastructure;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author kto
 * Contains all occurrence positions of an Identifier within one file
 * - The Identifier is uniquely idenfified by the set {identifierNameId, identifierType}
 * - The file is uniquely identified by a fileNameId
 * For caching reason, the number of occurrences of the identifier is stored.
 * 
 * FIXME: should this class be renamed "IdentifierPositions" ???
 */
public class Identifier {
	private int	_fileNameId = -1;
	private int	_identifierNameId = -1;
	private int _identifierType = -1; // Placeholder, inputfield, ...
	private int _nrOccurences = -1;  // Number of occurrences of this identifier in a FileIdentifier, not sure if this is a bad design
	
	private TreeMap<Integer, ArrayList<Integer>> _positions = new TreeMap<Integer, ArrayList<Integer>>();
	
	public Identifier(int fileNameId, int identifierNameId, int identifierType){
		
		if ( (fileNameId < 0) || (identifierNameId < 0) || (identifierType < 0)){
			throw new IndexOutOfBoundsException("At least one of the IDs is negative");
		}
		
		this._fileNameId = fileNameId;
		this._identifierNameId = identifierNameId;
		this._identifierType = identifierType;
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
	 * @param nameId the identifierNameId to set
	 */
	public void setNameId(int nameId) {
		this._identifierNameId = nameId;
	}

	/**
	 * @return the identifierNameId
	 */
	public int getNameId() {
		return this._identifierNameId;
	}

	/**
	 * @param identifierType the identifierType to set
	 */
	public void setType(int type) {
		this._identifierType = type;
	}

	/**
	 * @return the identifierType
	 */
	public int getType() {
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
	 * @param fileNameId the fileNameId to set
	 */
	public void setFileNameId(int fileNameId) {
		this._fileNameId = fileNameId;
	}

	/**
	 * @return the fileNameId
	 */
	public int getFileNameId() {
		return this._fileNameId;
	}	
}