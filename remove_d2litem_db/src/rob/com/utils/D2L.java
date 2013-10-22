package rob.com.utils;

//==============================================================================
//File         : D2L.java
//
//Current Author: Robert hewlett
//
//Previous Author: None
//
//Contact Info: rob.hewy@.com
//
//Purpose : General methods to test for the NNNNN-NNNNNN pattern and return
//          as short name i.e. no NNNNN-NNNNNN
//
//Dependencies: None
//
//Modification Log :
// --> Created OCT-21-2013 (rh)
// --> Updated MMM-DD-YYYY (fl)
//
//=============================================================================
import java.io.File;

public class D2L {
	// ==============================================================================
	// Method : hasPatternItemDB
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Determine if the NNNN-NNNNN pattern is present
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static boolean hasPatternItemDB(String file) {
		boolean pattern = false;

		try {
			int firstHyphen = file.indexOf("-");

			String d2lItemAsString = file.substring(0, firstHyphen);
			String rest = file.substring(firstHyphen + 1);

			int secondHyphen = rest.indexOf("-");
			String dbAsString = rest.substring(0, secondHyphen);

			long dbAsLong = Long.parseLong(dbAsString.trim());
			long d2lItemAsLong = Long.parseLong(d2lItemAsString.trim());

			pattern = true;

		} catch (Exception e) {
			// System.out.println(e);
		}

		return pattern;
	}

	// ==============================================================================
		// Method : getNoItemNoDBName
		//
		// Current Author: Robert Hewlett
		//
		// Previous Author: None
		//
		// Contact Info: rob.hewy@gmail.com
		//
		// Purpose : Determine if the NNNN-NNNNN pattern is present
		//
		// Dependencies: None
		//
		// Modification Log :
		// --> Created OCT-21-2013 (rh)
		// --> Updated MMM-DD-YYYY (fl)
		//
		// =============================================================================
	public static String getNoItemNoDBName(String file) {
		String shortName = null;
		try {
			int firstHyphen = file.indexOf("-");
			String rest = file.substring(firstHyphen + 1);
			int secondHyphen = rest.indexOf("-");
			shortName = rest.substring(secondHyphen + 1).trim();

		} catch (Exception e) {

		}

		return shortName;
	}

}
