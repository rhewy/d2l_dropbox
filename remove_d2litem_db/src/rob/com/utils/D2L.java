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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class D2L
{
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
	public static boolean hasPatternItemDB(String file)
	{
		boolean pattern = false;
		long db = getD2LItem(file);
		long d2lItem = getDB(file);

		if (db > 0 && d2lItem > 0)
		{
			pattern = true;
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
	public static String getNoItemNoDBName(String file)
	{
		String shortName = null;
		try
		{
			int firstHyphen = file.indexOf("-");
			String rest = file.substring(firstHyphen + 1);
			int secondHyphen = rest.indexOf("-");
			shortName = rest.substring(secondHyphen + 1).trim();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return shortName;
	}

	// ==============================================================================
	// Method : getD2LItem
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Determine if the d2lItem pattern is present in the string
	// the string should start with a long number followed by a hyphen
	// returns -1 if the pattern is not found
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static long getD2LItem(String file)
	{
		long d2lItem = -1;
		try
		{
			int firstHyphen = file.indexOf("-");
			String d2lItemAsString = file.substring(0, firstHyphen);
			d2lItem = Long.parseLong(d2lItemAsString.trim());
		}
		catch (Exception e)
		{

		}
		return d2lItem;
	}

	// ==============================================================================
	// Method : getDB
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Determine if the db pattern is present in the string
	// the string should start with a long number followed by a hyphen
	// followed by a long number followed by a hyphen
	// e.g. 123456 - 12345 - Robert_Hewlett.doc
	// returns -1 if the pattern is not found
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================

	public static long getDB(String file)
	{
		long db = -1;
		try
		{
			int firstHyphen = file.indexOf("-");
			String rest = file.substring(firstHyphen + 1);
			int secondHyphen = rest.indexOf("-");
			String dbAsString = rest.substring(0, secondHyphen);
			db = Long.parseLong(dbAsString.trim());
		}
		catch (Exception e)
		{

		}
		return db;
	}

	// ==============================================================================
	// Method : getStudentList
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : create a unique student list from all the files down loaded
	// from the
	// drop box. The list could be used to create student dirs if needed
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================

	public static ArrayList<D2LStudentSubmissionInfo> getStudentList(File dir)
	{
		ArrayList<D2LStudentSubmissionInfo> studentList = new ArrayList<D2LStudentSubmissionInfo>();

		String[] files = dir.list();
		Scanner oneLine;
		// ===========================================================
		// our schools delimiter is underscore ... could be an parameter
		// in the future
		// ===========================================================
		String delimiter = "_";
		// ===========================================================
		// student info will temporarily hold data extracted from the file name
		// ===========================================================
		D2LStudentSubmissionInfo studentInfo;
		// ===========================================================
		// Process all the file in the dir
		// ===========================================================
		for (String file : files)
		{
			// ===========================================================
			// only use file with the nnnnnn-nnnnn -* pattern ... skip the rest
			// ===========================================================
			if (D2L.hasPatternItemDB(file))
			{
				studentInfo = new D2LStudentSubmissionInfo();
				// ===========================================================
				// get the student id and db id from the file name
				// ===========================================================
				studentInfo.setD2lItem(D2L.getD2LItem(file));
				studentInfo.setDb(D2L.getDB(file));

				oneLine = new Scanner(D2L.getNoItemNoDBName(file));
				oneLine.useDelimiter(delimiter);
				// ===========================================================
				// get the first and last name
				// ===========================================================
				studentInfo.setFirstName(oneLine.next());
				studentInfo.setLastName(oneLine.next());
				// ===========================================================
				// add the student info if this is a new student
				// ===========================================================
				if (!studentList.contains(studentInfo))
				{
					studentList.add(studentInfo);
				}
				// ===========================================================
				// help out the garbage collector ... null the tmp object
				// ===========================================================
				oneLine = null;

			}
		}

		return studentList;
	}

	// ==============================================================================
	// Method : saveStudentList
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Save the student list of D2LStudentSubmissionInfo to disc
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static void saveStudentList(File file,
			ArrayList<D2LStudentSubmissionInfo> list)
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(list);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// ==============================================================================
	// Method : LoadStudentList
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Save the student list of D2LStudentSubmissionInfo to disc
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static ArrayList<D2LStudentSubmissionInfo> loadStudentList(File file)
	{
		ArrayList<D2LStudentSubmissionInfo> list = null;
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			list = (ArrayList<D2LStudentSubmissionInfo>) in.readObject();
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	// ==============================================================================
	// Method : getStudentList
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : create a unique student list from all the files down loaded
	// from the
	// drop box. The list could be used to create student dirs if needed
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-21-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================

}
