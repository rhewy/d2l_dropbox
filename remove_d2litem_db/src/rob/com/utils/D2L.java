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
import java.io.FileOutputStream;
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
	// Method : makeDirs
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
	public static void makeDirs(File dir,
			ArrayList<D2LStudentSubmissionInfo> list)
	{
		File tmpDir;
		for (D2LStudentSubmissionInfo info : list)
		{
			tmpDir = new File(dir, info.getFirstLastStudID());
			tmpDir.mkdir();
			tmpDir = null;

		}
	}

	// ==============================================================================
	// Method : moveFiles
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : move students files from the down loaded drop box to the
	// matching
	// student directory
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-24-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static void moveFiles(File dir,
			ArrayList<D2LStudentSubmissionInfo> list)
	{
		// ===========================================================
		// get the complete file dir list from the input dir
		// ===========================================================

		String[] files;
		files = dir.list();

		File toFile, toDir, fromFile;
		// ===========================================================
		// For every student in the list move the files
		// ===========================================================
		for (D2LStudentSubmissionInfo info : list)
		{
			// ===========================================================
			// Build if the destination directory
			// ===========================================================
			toDir = new File(dir, info.getFirstLastStudID());

			for (String filename : files)
			{
				fromFile = new File(dir, filename);
				// ===========================================================
				// If the file is a file and has the pattern then move the file
				// by renaming the file
				// ===========================================================
				if (fromFile.isFile())
				{
					if (fromFile.getName().contains(
							Long.toString(info.getD2lItem()))
							&& fromFile.getName().contains(info.getFirstName())
							&& fromFile.getName().contains(info.getLastName()))
					{
						toFile = new File(toDir, fromFile.getName());
						fromFile.renameTo(toFile);
					} // end of move the file by renaming the file
				} // end if it is a file and not a dir
			} // end for each file/dir
		} // end of the for each student
	} // end of method

	// ==============================================================================
	// Method : unZipFiles
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : unzip any files in the student's directory
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-24-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static void unZipAllStudentFiles(File dir,
			ArrayList<D2LStudentSubmissionInfo> list)
	{
		File studDir;
		for (D2LStudentSubmissionInfo info : list)
		{
			// ===========================================================
			// Get your feet right: point at an individual students dir
			// ===========================================================
			studDir = new File(dir, info.getFirstLastStudID());
		    unZipOneStudentsFiles(studDir);	
		}
		
	}

	public static void unZipOneStudentsFiles(File dir)
	{

		// ===========================================================
		// vars for the core student dir and the files in their dirs
		// ===========================================================
		// File studDir;
		File[] studFiles;
		studFiles = dir.listFiles();
		File tmpUnzipDir;
		// ===========================================================
		// build a sub directory to unzip files ... stop collisions
		// ===========================================================
		String zipDirPrefix = "zf_";
		int zipFileCount = 0;
		String zipDirName = null;
		// ===========================================================
		// for all the students unzip all the files in the student dir
		// ===========================================================
		for (File file : studFiles)
		{
			if (ZipBy7.isArchive(file))
			{
				zipFileCount += 1;
				// ===========================================================
				// make the dir that will be used for a zip file
				// ===========================================================
				zipDirName = String
						.format("%s%03d", zipDirPrefix, zipFileCount);
				System.out.println(zipDirName);
				tmpUnzipDir = new File(file.getParentFile(), zipDirName);
				tmpUnzipDir.mkdir();
				ZipBy7.unZipWholeFile(file, tmpUnzipDir);
			}
		}
	}
} // end of the class
