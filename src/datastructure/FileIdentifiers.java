/**
 * 
 */
package datastructure;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;

/**
 * @author kto
 * Contain all identifiers in a file
 */
public class FileIdentifiers {

	private String _fileName = "";
	private String _fileType = ""; //PHP, HTML, JS, SQL, PKB, PKS
	
	// TreeMap<identifier_name, Identifier>.
	// identifier_name is also stored inside Identifier
	private TreeMap<String, Identifier> _fileIdentifiers = null;
	private TreeMap<Integer, ArrayList<Integer>> _currentRow = null;
	private ArrayList<Integer> _currentRowCols = null;

	private Identifier _currentIdentifier = null;
	
	private int _identifierType = -1;
	
	private String _BOL = "\n"; // Used in serialize(), to print the begin of one line
	private String _BOI = "\n==============================\n"; // Used in serialize(), to print the begin of one identifier

	public FileIdentifiers(String fileName, int identifierType){
		this._fileIdentifiers = new TreeMap<String, Identifier>();
		this._currentRow = new TreeMap<Integer, ArrayList<Integer>>();
		this._currentRowCols = new ArrayList<Integer>();
		this._fileName = fileName;
		this._identifierType = identifierType;
	}
	
	public FileIdentifiers(String fileName, String bol, String boi, int identifierType){
		this._BOL = bol;
		this._BOI = boi;
		
		new FileIdentifiers(fileName, identifierType);
	}
	
	/**
	 * 
	 * @param identifierName
	 * @param row
	 * @param col
	 */
	public void add(String identifierName, int row, int col){
			
		if (null != (_currentIdentifier = _fileIdentifiers.get(identifierName))){
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
			
			_currentIdentifier = new Identifier(_fileName, identifierName);
			_currentIdentifier.setPos(_currentRow);
			_currentIdentifier.incNrOccurences();
			
			_fileIdentifiers.put(identifierName, _currentIdentifier);			
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String serialize(){
		
		String retStr = "";
		
		Set<String> nameKeySet = _fileIdentifiers.keySet();
		for (String name : nameKeySet){
			retStr += _BOI + name + ":[" + _BOL;
			_currentIdentifier = _fileIdentifiers.get(name);
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
		Set<String> nameKeySet = _fileIdentifiers.keySet();
		for (String name : nameKeySet){
			if (_fileIdentifiers.get(name).getNrOccurences() > 1){
				occurenceStats += name + ": " + _fileIdentifiers.get(name).getNrOccurences() + ", ";
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
	public TreeMap<String, Identifier> getFileIdentifiers() {
		return _fileIdentifiers;
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
		return _fileName;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this._fileType = fileType;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return _fileType;
	}
}
