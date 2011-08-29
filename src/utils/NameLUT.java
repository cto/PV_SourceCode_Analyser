/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author kto
 * Two-way LookUpTable for the names (file names or identifier names) in our PV source code [directories].
 * This provides a map between a (unique) long [file] name and a unique number (aka index)
 *  
 * Why do we need this class? To avoid expensive storage and searching on [file] names, because:
 * 
 * (1) For file names:
 *   - File names are long (200 characters on maximum, 70 (guessed !) characters on average) with base names repeated between them
 *   - The size of variability is small (we have less than 10 K files)
 * , so each file is represented by 4 digits instead of 70 characters.
 * (2) For identifier names:
 *   - Identifier names are on average 6 characters (guessed !)
 *   - The size of variability is small (we have much less than 10 K identifiers)
 *
 * In client code, you would use the index to refer to a [file] name
 * 
 * Looking up is two-way:
 * - First way: from index to name, complexity is O(log(n)), most of the cases. Searching for wrong index would throw
 *   an IndexOutOfBoundException
 * 
 * - Second way: from name to index, complexity is O(n), much less frequent. Searching for wrong name woudl throw
 *   a NullPointerException
 * 
 * 
 */
public class NameLUT {

	              /*<name,  index  >*/
	private HashMap<String, Integer> _name2IdxLUT = null;

	               /*<name>*/
	private ArrayList<String> _idx2NameLUT = null;

	private int _index = -1;
	
	private int _nameType = -1;
	
	public NameLUT(int nameType){
		this._name2IdxLUT = new HashMap<String, Integer>();
		this._idx2NameLUT = new ArrayList<String>();
		this.set_nameType(nameType);
		this._index = 0;
	}
	
	/**
	 * Add a name as key to the LUT, if the name does not exist in the LUT
	 * @param name
	 * @return		true if addition succeeds, false if the name already exists
	 */
	public boolean add(String name){
		if (this._name2IdxLUT.containsKey(name)){
			return false;
		}
		
		this._name2IdxLUT.put(name, this._index);
		this._idx2NameLUT.add(name);
		this._index++;
		
		return true;
	}
	
	/**
	 * 
	 * @param idx
	 * @return		the found name, or null if not found
	 */
	public String getName(int idx){
		return this._idx2NameLUT.get(idx);
	}
	
	/**
	 * 
	 * @param name
	 * @return		the found index, or a NullPointerException is thrown if not found
	 */
	public int getIndex(String name){
		return this._name2IdxLUT.get(name);
	}
	
	/**
	 * 
	 * @return	the table size
	 */
	public int getLUTSize(){
		return this._index;
	}

	/**
	 * @param _nameType the _nameType to set
	 */
	public void set_nameType(int _nameType) {
		this._nameType = _nameType;
	}

	/**
	 * @return the _nameType
	 */
	public int get_nameType() {
		return _nameType;
	}
}
