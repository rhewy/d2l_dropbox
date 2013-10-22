package rob.run;

//==============================================================================
// File         : Main.java
//
// Current Author: Robert hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@.com
//
// Purpose : Strip the d2lItem number and db number off the files down loaded 
//           from a drop box
//
// Dependencies: None
//
// Modification Log :
//    --> Created OCT-21-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class Main {
	public static void main(String[] args) {
		try {
			File dropbox_dir = null;
			// ===========================================================
			// Check the command line for a dir otherwise pop a file chooser
			// ===========================================================
			if (args.length > 0) {
				dropbox_dir = new File(args[0]);
			} else {
				JFileChooser getPath = new JFileChooser();
				getPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				getPath.showOpenDialog(null);
				dropbox_dir = getPath.getSelectedFile();

			}

			ArrayList<D2LStudentSubmissionInfo> studList = D2L
					.getStudentList(dropbox_dir);
			System.out.println(studList.size());

			// File tmpFile, destFile;
			// // ===========================================================
			// // Strip the item and db ids off the file
			// // ===========================================================
			// String[] files = dropbox_dir.list();
			// String shortName;
			//
			//
			// for (String file : files) {
			// if (D2L.hasPatternItemDB(file)) {
			// tmpFile = new File(dropbox_dir, file);
			// shortName = D2L.getNoItemNoDBName(file);
			// destFile = new File(dropbox_dir, shortName);
			// tmpFile.renameTo(destFile);
			// System.out.printf("Renaming %s ----->  %s ...... %n", file,
			// shortName);
			//
			// } // end of the if pattern is there
			// } // end of the for
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	} // end of the method main
} // end of the class Main
