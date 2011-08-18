/**
 * 
 */
package utils;

import config.Settings;

/**
 * @author kto
 *
 */
public class FileName {
	
	private final String	_htmlFileName;
	private final String	_phpFileName;
	
	public FileName(String htmlFileName, String phpFileName){
		_htmlFileName = htmlFileName;
		_phpFileName = phpFileName;
	}

	/**
	 * @return the _htmlFileName
	 */
	public String getHtmlFileName() {
		return _htmlFileName;
	}

	/**
	 * @return the _phpFileName
	 */
	public String getPhpFileName() {
		return _phpFileName;
	}
	
	public static String html2php(String htmlFileName){
		String thisHTMLAbsolutePath = htmlFileName.replaceFirst(Settings.HTML_BASE, Settings.PHP_BASE);
		return thisHTMLAbsolutePath.replaceFirst("\\.html", "\\.php");
	}
	
	public static String php2html(String phpFileName){
		String htmlAbsolutePath = phpFileName.replaceFirst(Settings.PHP_BASE, Settings.HTML_BASE);
		return htmlAbsolutePath.replaceFirst("\\.php", "\\.html");
	}
}
