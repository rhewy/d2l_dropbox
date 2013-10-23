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
import java.util.ArrayList;
import java.util.Scanner;

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
	// Dependencies: D2LStudentSubmissionInfo
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static boolean hasPatternItemDB(String file) {
		boolean pattern = false;

		try {

			long db = getD2LItem(file);
			long d2lItem = getDB(file);

			if (db > 0 && d2lItem > 0) {
				pattern = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}

		return shortName;
	}

	public static long getD2LItem(String file) {
		long d2lItem = -1;
		try {
			int firstHyphen = file.indexOf("-");
			String d2lItemAsString = file.substring(0, firstHyphen);
			d2lItem = Long.parseLong(d2lItemAsString.trim());
		} catch (Exception e) {

		}
		return d2lItem;
	}

	public static long getDB(String file) {
		long db = -1;
		try {
			int firstHyphen = file.indexOf("-");
			String rest = file.substring(firstHyphen + 1);
			int secondHyphen = rest.indexOf("-");
			String dbAsString = rest.substring(0, secondHyphen);
			db = Long.parseLong(dbAsString.trim());
		} catch (Exception e) {

		}
		return db;
	}

	public static ArrayList<D2LStudentSubmissionInfo> getStudentList(File dir) {
		ArrayList<D2LStudentSubmissionInfo> studentList = new ArrayList<D2LStudentSubmissionInfo>();

		String[] files = dir.list();
		Scanner oneLine;
		String delimiter = "_";

		D2LStudentSubmissionInfo studentInfo;

		for (String file : files) {
			if (D2L.hasPatternItemDB(file)) {
				studentInfo = new D2LStudentSubmissionInfo();
				studentInfo.setD2lItem(D2L.getD2LItem(file));
				studentInfo.setDb(D2L.getDB(file));

				oneLine = new Scanner(D2L.getNoItemNoDBName(file));
				oneLine.useDelimiter(delimiter);

				studentInfo.setFirstName(oneLine.next());
				studentInfo.setLastName(oneLine.next());
				if (!studentList.contains(studentInfo)) {
					studentList.add(studentInfo);
				}
				oneLine = null;

			}
		}

		return studentList;
	}

}
