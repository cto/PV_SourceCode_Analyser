/**
 * 
 */
package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import utils.NameLUT;

/**
 * @author kto
 *
 */
public class PVFileBase implements PVFileInterface{

	private String 				_fileName 		= null;
	                            
	private LineNumberReader	_lineNumbReader 	= null;
	                            
	private ArrayList<String>	_contentAL 	= null;
	private	String				_contentInString	= "";
	private String				_strLine = "";
	private boolean				_isFile = true;
	
	public PVFileBase(String fName)
	{
		this._fileName = fName;
		try{
			File thisFile = new File(fName);
			if (thisFile.isFile()){
				_isFile = true;
				readToContentAL(fName);
				this._contentInString = readToContentInString(fName);
			} else {
				_isFile = false;
				throw new Exception("Not a file");
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	private String readToContentInString(String fName) throws IOException{
		FileInputStream stream = new FileInputStream(new File(fName));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
	}

	private void readToContentAL(String fName) throws FileNotFoundException{
		this._contentAL 		= new ArrayList<String>();
		this._lineNumbReader = new LineNumberReader(new FileReader(fName));
		try{
			while ((_strLine = this._lineNumbReader.readLine()) != null){
				this._contentAL.add(this._lineNumbReader.getLineNumber() - 1, _strLine);
			}
		} catch (Exception e){
			System.err.println("Error reading from this file: " + fName);
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<String> getContentAL() {
		return this._contentAL;
	}

	@Override
	public String getContentInString() {
		return this._contentInString;
	}

	/**
	 * @return the _isFile
	 */
	public boolean isFile() {
		return _isFile;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName(){
		return this._fileName;
	}
}