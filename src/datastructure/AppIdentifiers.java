/**
 * 
 */
package datastructure;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author kto
 * Contains all Identifiers of all the files in the Application
 */
public class AppIdentifiers {

	private int _identifierType = -1; // 
	
	//            <FileName, FileIdentifiers>
	private final TreeMap<String, FileIdentifiers> _appIdentifiers;

	private FileIdentifiers _currentFileIdentifiers = null;
	
	private String _BOF = "\n"; // Used in serialize(), to print the begin of one file
	private String _BOI = "\n===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===_BOI===\n"; // Used in serialize(), to print the begin of one file

	
	public AppIdentifiers(int identifierType){
		this._identifierType = identifierType;
		this._appIdentifiers = new TreeMap<String, FileIdentifiers>();
	}

	/**
	 * @return the appIdentifiers
	 */
	public TreeMap<String, FileIdentifiers> getAppIdentifiers() {
		return this._appIdentifiers;
	}

	
	public String serialize(){
		Set<String> filenameSet = _appIdentifiers.keySet();
		
		String retStr = "";

		for (String fileName : filenameSet){
			retStr += _BOF + fileName + ":[" + _BOI;
			_currentFileIdentifiers = _appIdentifiers.get(fileName);
			
			retStr += _currentFileIdentifiers.serialize();
			retStr += "]";
		}
		
		return retStr;
	}
	
	/**
	 * Search for an identifier within this appIdentifier
	 * @param identifierName
	 */
	public int searchIdentifier(	String needleIdentifierName
									,	String haystackFileName
									,	TreeMap<String, Identifier> placesFound)
	{
		FileIdentifiers fiid = this._appIdentifiers.get(haystackFileName);
		
		if (null == fiid){
			System.out.println(needleIdentifierName + " is not found at " + haystackFileName);
		}
		TreeMap<String, Identifier> hoho = fiid.getFileIdentifiers();
		
		if (hoho.containsKey(needleIdentifierName)){
			return 0;
		}
		
//		if (this._appIdentifiers.get(haystackFileName).getFileIdentifiers().containsKey(needleIdentifierName)){
//			return 0;
//		}
		
		int found = -1;
		Set<String> thisFileNameSet = this._appIdentifiers.keySet();
		
		for (String currentfileName : thisFileNameSet){
			Identifier foundIdentifier = null;
			if (null != (foundIdentifier = this._appIdentifiers.get(currentfileName).getFileIdentifiers().get(needleIdentifierName))){
				placesFound.put(currentfileName, foundIdentifier);
				found = 1;
			}
		}
		
		return found;
	}
}
