/**
 * 
 */
package config;

import java.util.ArrayList;

/**
 * @author kto
 *
 */
public final class Settings {

	public static final String PHP_INCLUDES  					= "/home/kto/PV/src/workspace_sites/portavita_head/php_includes";		
	public static final String SITE_PORTAVITA_SRC_BASE  		= "/home/kto/PV/src/workspace_sites/portavita_head/sites/portavita.nl";		
	public static final String SITE_DIGITAALLOGBOEK_SRC_BASE 	= "/home/kto/PV/src/workspace_sites/portavita_head/sites/digitaallogboek.nl";
		
	public static final String PHP_BASE 		= "pv_phps";
	public static final String HTML_BASE 		= "pv_templates";
	public static final String HTML_LIB_BASE 	= "pv_libraries";
	public static final String JS_BASE 			= "htdocs/javascript";
	public static final String EXE_PHP_BASE 	= "htdocs";
	
	public static final String PHP_FILE_EXTENSION	= ".php";

	public static final String	OCB				= "{"; //OPENING_CURLY_BRACKET
	public static final String	CCB				= "}"; //CLOSING_CURLY_BRACKET
	public static final String	ORB				= "("; //OPENING_ROUND_BRACKET
	public static final String	CRB				= ")"; //CLOSING_ROUND_BRACKET

	public static final String	SWITCH_KEYWORD				= "switch";
	
	public static final long NANOSECONDS_PER_SECONDS = 1000000000;
	
	/**
	 * CONSTANTS FOR PATTERN STRINGS
	 */
	public static final String IDENTIFIER_NAME_PAT_STR = ORB + "[a-zA-Z_0-9]+" + CRB;
	
	//<input type="text" name = "one_input_name" value="">
	//<input[^>]+name\s*=\s*"\s*([a-zA-Z_0-9]+)\s*"[^>]*>
	public static final String HTML_INPUTFIELD_PATTERN_STR = "<input[^>]+name\\s*=\\s*\"\\s*" + IDENTIFIER_NAME_PAT_STR + "\\s*\"[^>]*>";
	
	
	//@[PLACE_HOLDER]@
	//"@\[[A-Z_0-9]+\]@"
	public static final String HTML_PLHO_PATTERN_STR = "@\\[" + IDENTIFIER_NAME_PAT_STR +"\\]@";

	/**
	 * GLOBAL VARIABLES
	 */
	
	// String arrays each containing global placeholders in alphabetical orders
	
	//[ACCOUNT_STATUS, ADRES, CURRENT_DATE, DOMAIN, EMAIL, GEBOORTEPLAATS, GEB_DATUM, GESLACHT, HEADER_GIF, HEADER_GIF_FULL_PATH, HOMEPAGE, HUISARTS, HUISARTS_TEL, LAND, LOCALE, LOGIN_HTML, MESSAGE, MESSAGE_FOCUS, MOBIELNR, NAAM, ORGA_NAME, PATIENTNUMMER, POLISNUMMER, POSTCODE, PTNT_ID, SCOPING_ORGA_NAME, TD_NAME, TELNR, THUISAPOTHEEK, TITEL, TITEL_TROMBOSE, UITGELOGD_HTML, USER_NAME, VERZEKERING, WOONPLAATS]
	public static ArrayList<String> GENERIC_GLOBAL_PLACEHOLDERS = new ArrayList<String>();

	//[ANTISTOLLINGS_INDICATIE, CURRENT_DATE, DATUM_LAATSTE_INR, HEADER_GIF, INTENSITEITSGROEP, LAATSTE_METING, LOGIN_HTML, MAX_LENGTE_ADRES, MAX_LENGTE_OPM_DOSEERBRIEF, MESSAGE, MESSAGE_FOCUS, ORGA_NAME, PID_ADMINISTRATED_SEX_S, PID_AGE, PID_DATE_OF_BIRTH, PID_NAME_SORT, SCOPING_ORGA_NAME, STARTDATUM_BEHANDELING, STATUS, TD_NAME, TITEL_TROMBOSE, UITGELOGD_HTML, USER_NAME]
	public static ArrayList<String> ANTISTOLLING_GLOBAL_PLACEHOLDERS = new ArrayList<String>();
		
	//[ANTISTOLLINGS_INDICATIE, CURRENT_DATE, DATUM_LAATSTE_INR, HEADER_GIF, INTENSITEITSGROEP, LAATSTE_METING, LOGIN_HTML, MENU, MESSAGE, MESSAGE_FOCUS, PESO_NAME, PID_ADMINISTRATED_SEX_S, PID_DATE_OF_BIRTH, PID_NAME_SORT, TD_NAME, TRBO_NAME, UITGELOGD_HTML, USER_NAME, YEAR]
	public static ArrayList<String> DIGITAALLOGBOEK_GLOBAL_PLACEHOLDERS = new ArrayList<String>();
	
	//[CURRENT_DATE, HEADER_GIF, LOGIN_HTML, MESSAGE, MESSAGE_FOCUS, TD_NAME, TITEL_SCHERM, UITGELOGD_HTML, USER_NAME]
	public static ArrayList<String> SERVICECENTER_GLOBAL_PLACEHOLDERS = new ArrayList<String>();
	
	
	/**
	 * Settings for error detection purposes
	 */
	public static final boolean QUICKMODE = false;
	public static final boolean STRICTMODE = false;
	public static final boolean SCARYMODE = true;
	
	/**
	 * Settings for debugging purposes
	 */
	public static final boolean VERBOSE = false;
	
	
	/**
	 * Settings for identifier types
	 */
	public static final int PLACEHOLDER = 0;
	public static final int INPUTFIELD  = 1;	
	
	/**
	 * Settings for file types
	 */
	public static final int PHP	= 0;
	public static final int HTML = 1;	
	public static final int JS = 2;
	public static final int PKB = 3;
	public static final int PKS = 4;	
	
	/**
	 * Settings for name types
	 */
	public static final int FILE_NAME = 0;
	public static final int IDENTIFIER_NAME = 1;	
}
