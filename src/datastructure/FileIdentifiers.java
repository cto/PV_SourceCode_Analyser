/**
 * 
 */
package datastructure;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;

/**
 * @author kto
 * Contain all identifiers in a file that are of a same type (Placeholder, inputfield, ...)
 */
public class FileIdentifiers {

	private int _fileNameId = -1;
	private int _fileType = -1; //PHP, HTML, JS, SQL, PKB, PKS
	
	// TreeMap<identifierNameId, Identifier>.
	// identifierNameId is also stored inside each Identifier
	private TreeMap<Integer, Identifier> _fileIdentifiers = null;
	private TreeMap<Integer, ArrayList<Integer>> _currentRow = null;
	private ArrayList<Integer> _currentRowCols = null;

	private Identifier _currentIdentifier = null;
	
	private int _identifierType = -1;
	
	private String _BOL = "\n"; // Used in serialize(), to print the begin of one line
	private String _BOI = "\n==============================\n"; // Used in serialize(), to print the begin of one identifier

	public FileIdentifiers(int fileNameId, int identifierType, int fileType){
		
		this._fileIdentifiers = new TreeMap<Integer, Identifier>();
		this._currentRow = new TreeMap<Integer, ArrayList<Integer>>();
		this._currentRowCols = new ArrayList<Integer>();

		this._fileNameId = fileNameId;
		this._identifierType = identifierType;
		this._fileType = fileType;
	}
	
	public FileIdentifiers(int fileNameID, String bol, String boi, int identifierType, int fileType){
		this._BOL = bol;
		this._BOI = boi;
		
		new FileIdentifiers(fileNameID, identifierType, fileType);
	}
	
	/**
	 * 
	 * @param identifierNameId
	 * @param row
	 * @param col
	 */
	public void add(int identifierNameId, int row, int col){
			
		if (null != (_currentIdentifier = _fileIdentifiers.get(identifierNameId))){
			_currentIdentifier.incNrOccurences();
			_currentRow = _currentIdentifier.getPos();
		
			if (null != (_currentRowCols = _currentRow.get(row))){
				_currentRowCols.add(col);
			
			} else {
				_currentRowCols = new ArrayList<Integer>();
				_currentRowCols.add(col);
				
				_currentRow.put(row, _currentRowCols);
				
			}					
		} else {
			_currentRowCols = new ArrayList<Integer>();
			_currentRowCols.add(col);
			
			_currentRow = new TreeMap<Integer, ArrayList<Integer>>();
			_currentRow.put(row, _currentRowCols);
			
			_currentIdentifier = new Identifier(_fileNameId, identifierNameId, this._identifierType );
			_currentIdentifier.setPos(_currentRow);
			_currentIdentifier.incNrOccurences();
			
			_fileIdentifiers.put(identifierNameId, _currentIdentifier);			
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String serialize(){
		
		String retStr = "";
		
		Set<Integer> nameKeySet = _fileIdentifiers.keySet();
		for (int nameId : nameKeySet){
			retStr += _BOI + nameId + ":[" + _BOL;
			_currentIdentifier = _fileIdentifiers.get(nameId);
			Set<Integer> rowKeySet = _currentIdentifier.getPos().keySet();

			for (Integer row : rowKeySet){
				retStr += row + ":[";
				ArrayList<Integer> currentRow = _currentIdentifier.getPos().get(row);
				int sz = currentRow.size() - 1;
				for (int col = 0; col < sz; col++){
					retStr += currentRow.get(col) + ",";
				}
				retStr += currentRow.get(currentRow.size() - 1) + "]";				
			};
			retStr += "]";
		}
		
		return retStr;		
	}
	
	// Print some statistics of these identifiers
	public void printStats(){
		if (_fileIdentifiers.keySet().size() > 0){
			System.out.println("This set of identifiers has " +  _fileIdentifiers.keySet().size() + " identifiers");
		}
		
		String occurenceStats = "";
		Set<Integer> nameKeySet = _fileIdentifiers.keySet();
		for (int nameId : nameKeySet){
			if (_fileIdentifiers.get(nameId).getNrOccurences() > 1){
				occurenceStats += nameId + ": " + _fileIdentifiers.get(nameId).getNrOccurences() + ", ";
			}
		}
		if (occurenceStats != ""){
			occurenceStats = "The following identifiers each has more than one appearance: \n\n" + occurenceStats;
			System.out.println(occurenceStats);
		}	
	}
	
	/**
	 * @return the fileIdentifiers
	 */
	public TreeMap<Integer, Identifier> getFileIdentifiers() {
		return _fileIdentifiers;
	}

	/**
	 * @param fileNameId the fileName to set
	 */
	public void setFileNameId(int fileNameId) {
		this._fileNameId = fileNameId;
	}

	/**
	 * @return the fileName
	 */
	public int getFileNameId() {
		return _fileNameId;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(int fileType) {
		this._fileType = fileType;
	}

	/**
	 * @return the fileType
	 */
	public int getFileType() {
		return _fileType;
	}
}
