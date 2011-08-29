/**
 * 
 */
package datastructure;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author kto
 * Contains all Identifiers of the same type in all the files in the Application
 */
public class AppIdentifiers {

	private int _identifierType = -1; // 
	
	                  // <FileNameId, FileIdentifiers>
	private final TreeMap<Integer,    FileIdentifiers> _appIdentifiers;

	private FileIdentifiers _currentFileIdentifiers = null;
	
	private String _BOF = "\n"; // Used in serialize(), to print the begin of one file
	private String _BOI = "\n===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===\n"; // Used in serialize(), to print the begin of one file

	
	public AppIdentifiers(int identifierType){
		this._identifierType = identifierType;
		this._appIdentifiers = new TreeMap<Integer, FileIdentifiers>();
	}

	/**
	 * @return the appIdentifiers
	 */
	public TreeMap<Integer, FileIdentifiers> getAppIdentifiers() {
		return this._appIdentifiers;
	}

	
	public String serialize(){
		Set<Integer> filenameIdSet = _appIdentifiers.keySet();
		
		String retStr = "";

		for (int fileNameId : filenameIdSet){
			retStr += _BOF + fileNameId + ":[" + _BOI;
			_currentFileIdentifiers = _appIdentifiers.get(fileNameId);
			
			retStr += _currentFileIdentifiers.serialize();
			retStr += "]";
		}
		
		return retStr;
	}
	
	/**
	 * Search for an identifier within this appIdentifier
	 * @param identifierName
	 */
	public int searchIdentifier(	int needleIdentifierNameId
									,	int haystackFileNameId
									,	TreeMap<Integer, Identifier> placesFound)
	{
		FileIdentifiers fiid = this._appIdentifiers.get(haystackFileNameId);
		
		if (null == fiid){
			System.out.println(needleIdentifierNameId + " is not found at " + haystackFileNameId);
		}
		TreeMap<Integer, Identifier> hoho = fiid.getFileIdentifiers();
		
		if (hoho.containsKey(needleIdentifierNameId)){
			return 0;
		}
		
//		if (this._appIdentifiers.get(haystackFileName).getFileIdentifiers().containsKey(needleIdentifierName)){
//			return 0;
//		}
		
		int found = -1;
		Set<Integer> thisFileNameIdSet = this._appIdentifiers.keySet();
		
		for (int currentfileNameId : thisFileNameIdSet){
			Identifier foundIdentifier = null;
			if (null != (foundIdentifier = this._appIdentifiers.get(currentfileNameId).getFileIdentifiers().get(needleIdentifierNameId))){
				placesFound.put(currentfileNameId, foundIdentifier);
				found = 1;
			}
		}
		
		return found;
	}

	/**
	 * @param identifierType the _identifierType to set
	 */
	public void set_identifierType(int identifierType) {
		this._identifierType = identifierType;
	}

	/**
	 * @return the _identifierType
	 */
	public int get_identifierType() {
		return _identifierType;
	}
}
