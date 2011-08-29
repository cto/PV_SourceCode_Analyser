/**
 * 
 */
package main;

import config.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import utils.FileName;
import utils.NameLUT;

import datastructure.AppIdentifiers;
import datastructure.FileIdentifiers;
import datastructure.Identifier;
import io.*;

/**
 * @author kto
 *
 */
public class Main {

	public static int howManyPhpfiles = 0;
	public static int howManyNonPhpFiles = 0;

	public static int howManyPHPPlaceHolders = 0;
	public static int howManyUnusedPHPPlaceHolders = 0;
	
	public static int howManyHTMLUserInputNames = 0;
	public static int howManyNonExistingHTMLUserInputNames = 0;
	
	public static String[] allSubDirNames 	= new String[9999];
	public static String[] allPhpFileNames 	= new String[9999];	

	public static final boolean verboseMode	= true; // true;
	

	private static AppIdentifiers _plhoPHPAppIdentifiers = new AppIdentifiers(Settings.PLACEHOLDER);
	private static AppIdentifiers _plhoHTMLAppIdentifiers = new AppIdentifiers(Settings.PLACEHOLDER);
	
	private static AppIdentifiers infiPHPAppIdentifiers = new AppIdentifiers(Settings.INPUTFIELD);
	private static AppIdentifiers infiHTMLAppIdentifiers = new AppIdentifiers(Settings.INPUTFIELD);

	private static NameLUT	_povaPHPNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
	private static NameLUT	_infiHTMLNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
	private static NameLUT	_plhoPHPNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
	private static NameLUT	_plhoHTMLNameLUT = new NameLUT(Settings.IDENTIFIER_NAME);
	
	private static NameLUT _htmlFileNameLUT = new NameLUT(Settings.FILE_NAME);
	private static NameLUT _phpFileNameLUT = new NameLUT(Settings.FILE_NAME);
	private static NameLUT _jsFileNameLUT = new NameLUT(Settings.FILE_NAME);
	private static NameLUT _pkbFileNameLUT = new NameLUT(Settings.FILE_NAME);
	private static NameLUT _pksFileNameLUT = new NameLUT(Settings.FILE_NAME);
	/**
	 * Fill the global placeholder lists in Settings accordingly
	 * @param phpFileName
	 * @param phpPlhoNameSet
	 */
	private static void _buildGlobalPlaceholderList(String phpFileName, Set<Integer> phpPlhoNameSet){
		if (phpFileName.endsWith("portavita.nl/global_placeholder.php")){
			Settings.GENERIC_GLOBAL_PLACEHOLDERS = new ArrayList<Integer>(phpPlhoNameSet);
			return;
		} else if (phpFileName.endsWith("antistolling/global_placeholder.php")){
			Settings.ANTISTOLLING_GLOBAL_PLACEHOLDERS = new ArrayList<Integer>(phpPlhoNameSet);
			return;
		} else if (phpFileName.endsWith("digitaallogboek.nl/global_placeholder.php")){
			Settings.DIGITAALLOGBOEK_GLOBAL_PLACEHOLDERS = new ArrayList<Integer>(phpPlhoNameSet);
			return;
		} else if (phpFileName.endsWith("servicecenter/global_placeholder.php")){
			Settings.SERVICECENTER_GLOBAL_PLACEHOLDERS = new ArrayList<Integer>(phpPlhoNameSet);
			return;
		}
	}
	/**
	 * 
	 * @param phpFile
	 * @throws Exception
	 */
	private static void collectPlaceHolders(PHPFile phpFile) throws Exception{

		FileIdentifiers filePlaceHolders = phpFile.getPlaceHolders();  // KKKTTT: This is INCORRECT
		int nrPHPPlhos = filePlaceHolders.getFileIdentifiers().size();

		if (nrPHPPlhos <= 0){
			return;
		}

		howManyPHPPlaceHolders += nrPHPPlhos;
		_plhoPHPAppIdentifiers.getAppIdentifiers().put(phpFile.getFileName(), filePlaceHolders);

		Set<Integer> phpPlhoNameSet = filePlaceHolders.getFileIdentifiers().keySet();
		String thisPHPAbsolutePath = phpFile.getFileName();
		
		_buildGlobalPlaceholderList(thisPHPAbsolutePath, phpPlhoNameSet);
		
		String thisHTMLAbsolutePath = "";
		
		thisHTMLAbsolutePath = thisPHPAbsolutePath.replaceFirst(Settings.PHP_BASE, Settings.HTML_BASE);
		thisHTMLAbsolutePath = thisHTMLAbsolutePath.replaceAll("\\.php", "\\.html");		
		HTMLFile 	htmlFile 	= new HTMLFile(thisHTMLAbsolutePath, Settings.HTML);
	
		if ( ! htmlFile.isFile()){ // HTML is not in HTML_BASE, so we hope to find it in HTML_LIB_BASE
			thisHTMLAbsolutePath = thisHTMLAbsolutePath.replaceFirst(Settings.HTML_BASE, Settings.HTML_LIB_BASE);
			htmlFile 	= new HTMLFile(thisHTMLAbsolutePath, Settings.HTML);
			if ( ! htmlFile.isFile()){
				System.out.println("Warning: this is not a file: " + thisHTMLAbsolutePath);
			} 
		}
		
		_plhoHTMLAppIdentifiers.getAppIdentifiers().put(htmlFile.getFileName(), htmlFile.getPlaceHolders());

//			// The following lines are to report missing things
//			Set<String> htmlPlhoNameSet = htmlFile.getPlaceHolders().getFileIdentifiers().keySet();
//
//			for (String htmlPlhoName : htmlPlhoNameSet){
//				if ( 	! Settings.GENERIC_GLOBAL_PLACEHOLDERS.contains(htmlPlhoName)
//					 &&	! Settings.ANTISTOLLING_GLOBAL_PLACEHOLDERS.contains(htmlPlhoName)
//					 &&	! Settings.DIGITAALLOGBOEK_GLOBAL_PLACEHOLDERS.contains(htmlPlhoName)
//					 && ! Settings.SERVICECENTER_GLOBAL_PLACEHOLDERS.contains(htmlPlhoName)
//				   )
//				{
//					if ( ! phpPlhoNameSet.contains(htmlPlhoName)){
////						System.out.println(htmlPlhoName + ": html plho not found @" + htmlFile.getFileName());
//					}
//				}
//			}
//								
//			for (String phpPlhoName : phpPlhoNameSet){
//				if ( ! htmlPlhoNameSet.contains(phpPlhoName)){
//					howManyUnusedPHPPlaceHolders++;
//					if (verboseMode){
//						System.out.println(phpPlhoName + ": PHP plho not found @ " + phpFile.getFileName());
//					}					
//				};				
//			}						
	}
	
	/**
	 * 
	 * @param phpFile
	 */
	private static void collectPostedValues(PHPFile phpFile){
		
		FileIdentifiers allUserInputNames = phpFile.getPostedValues();
		
		int nrPHPInfi = allUserInputNames.getFileIdentifiers().size();
		
		if (nrPHPInfi <= 0){
			return;
		}
		
		howManyHTMLUserInputNames += nrPHPInfi;
		
		infiPHPAppIdentifiers.getAppIdentifiers().put(phpFile.getFileName(), allUserInputNames);

		String thisHTMLAbsolutePath = phpFile.getFileName().replaceFirst(Settings.PHP_BASE, Settings.HTML_BASE);
		thisHTMLAbsolutePath = thisHTMLAbsolutePath.replaceFirst("\\.php", "\\.html");
		HTMLFile 	htmlFile = new HTMLFile(thisHTMLAbsolutePath, null);
		
		infiHTMLAppIdentifiers.getAppIdentifiers().put(htmlFile.getFileName(), htmlFile.getPlaceHolders());

//		//The following lines are to report missing things	
//		Set<String> allUserInputNamesKeySet = allUserInputNames.getFileIdentifiers().keySet();
//		for (String userInputName : allUserInputNamesKeySet){
//			if ( 	( ! phpFile.getFileName().contains("php_includes")) // PHP files in php_includes/ does not have an accompanied HTML file
//				&&	( ! htmlFile.isUserInputNameFound(userInputName))
//			   )
//			{
//				howManyNonExistingHTMLUserInputNames++;
//				if (verboseMode){
//					System.out.println("Possibly non-existing user input: ===[   " + userInputName + "   ]=== in the file " + phpFile.getFileName());
//				}
//			}
//		}
	}
	
	/**
	 * If this file is a PHP file, this function finds 
	 * - all of its plhos
	 * - all of its unused placholders
	 * @param dir
	 * @throws Exception
	 */
	public static void process(File fPossiblePHP) throws Exception{
	
		String thisName = fPossiblePHP.getName();

		if ( ! thisName.endsWith(Settings.PHP_FILE_EXTENSION)){
			allSubDirNames[howManyNonPhpFiles++] 	= thisName;
			return;
		}
				
		allPhpFileNames[howManyPhpfiles++] 	= thisName;	
		
		PHPFile	phpFile	= new PHPFile(fPossiblePHP.getAbsolutePath());
		collectPlaceHolders(phpFile);
		collectPostedValues(phpFile);
	}
	
	/**
	 * Process all files and directories under dir
	 * @param dir
	 * @throws Exception
	 */
	public static void visitAllDirsAndFiles(File dir) throws Exception {
	    process(dir);

	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        int sz = children.length;
	        for (int i=0; i < sz; i++) {
	            visitAllDirsAndFiles(new File(dir, children[i]));
	        }
	    }
	}
	
	/**
	 * Process only directories under dir
	 * @param dir
	 * @throws Exception
	 */
	public static void visitAllDirs(File dir) throws Exception {
	    if (dir.isDirectory()) {
	        process(dir);

	        String[] children = dir.list();
	        int sz = children.length;
	        for (int i=0; i < sz; i++) {
	            visitAllDirs(new File(dir, children[i]));
	        }
	    }
	}

	/**
	 * Process only files under dir
	 * @param dir
	 * @throws Exception
	 */
	public static void visitAllFiles(File dir) throws Exception {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        int sz = children.length;
	        for (int i=0; i < sz; i++) {
	            visitAllFiles(new File(dir, children[i]));
	        }
	    } else {
	        process(dir);
	    }
	}
	
	/**
	 * Scan a PHP source directory for analyzing irregular pieces of code in its PHP files
	 * @param phpDir
	 * @throws Exception 
	 */
	public static void scanSourceDir(String phpDir) throws Exception{
		howManyPhpfiles = 0;
		howManyNonPhpFiles = 0;
		howManyPHPPlaceHolders = 0;
		howManyUnusedPHPPlaceHolders = 0;
		howManyHTMLUserInputNames = 0;
		howManyNonExistingHTMLUserInputNames = 0;


		File dir = new File(phpDir);
		String[] children = dir.list();
				
		if (null != children){
			visitAllFiles(dir);
		}
		
		System.out.println("\nIn this directory: " + phpDir + "\n, there are: " + howManyPhpfiles + " PHP files, " + howManyNonPhpFiles + " non PHP files, " + howManyPHPPlaceHolders + " place holders, " + howManyUnusedPHPPlaceHolders + " unused PHP place holders\n");
		System.out.println("\nIn this directory: " + phpDir + "\n, there are: " + howManyPhpfiles + " PHP files, " + howManyNonPhpFiles + " non PHP files, " + howManyHTMLUserInputNames + " user input names, " + howManyNonExistingHTMLUserInputNames + " non existing HTML user input names\n");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try{
			long startTime = System.nanoTime();
			scanSourceDir(Settings.PHP_INCLUDES);
			scanSourceDir(Settings.SITE_PORTAVITA_SRC_BASE + "/" + Settings.PHP_BASE);
			scanSourceDir(Settings.SITE_DIGITAALLOGBOEK_SRC_BASE + "/" + Settings.PHP_BASE);		
			
			reportOnPlaceHolders(_plhoPHPAppIdentifiers, _plhoHTMLAppIdentifiers);
			
			long elapsedTime = System.nanoTime() - startTime;
						
			System.out.println("Elapsed time (seconds) used to scan: " + Math.round(elapsedTime / Settings.NANOSECONDS_PER_SECONDS));
						
			System.out.println("Total memory used (in MB): " + Runtime.getRuntime().totalMemory() / 1048576);
			
//			System.out.println(_plhoPHPAppIdentifiers.serialize());

		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private static void reportOnPlaceHolders(
			AppIdentifiers plhoPHPAppIdentifiers,
			AppIdentifiers plhoHTMLAppIdentifiers) throws Exception {
		
		Set<String> phpFileNames = plhoPHPAppIdentifiers.getAppIdentifiers().keySet();
		Set<String> htmlFileNames = plhoHTMLAppIdentifiers.getAppIdentifiers().keySet();
		
		
		for (String phpFileName : phpFileNames){
			if (phpFileName.contains("php_includes")){
				continue;
			}
			
			Set<String> phpPlhos = plhoPHPAppIdentifiers.getAppIdentifiers().get(phpFileName).getFileIdentifiers().keySet();
			
			for (String phpPlho : phpPlhos){
				int found = -9999;
				TreeMap<String, Identifier> placesFound = new TreeMap<String, Identifier>();
				found = plhoHTMLAppIdentifiers.searchIdentifier(phpPlho, FileName.php2html(phpFileName), placesFound);

				switch (found){
				case -1:
					System.out.println("UNFOUND: phpPlho = " + phpPlho + " @ phpFileName = " + phpFileName);
					continue;
				case 1:
					System.out.println("FOUND AT OTHER FILES: " + placesFound.toString());
					continue;
				case 0:
					continue;
				default:
					throw new Exception("wtf: corresponding HTML placeholders not found");
				}
			}			
		}
	}
}
