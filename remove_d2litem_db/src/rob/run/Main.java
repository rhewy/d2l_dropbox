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
import javax.swing.JFileChooser;
import rob.com.utils.D2L;;

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
				getPath.showDialog(null, "Select the dropbox Dir");
				dropbox_dir = getPath.getSelectedFile();

			}

			File tmpFile, destFile;
			// ===========================================================
			// Strip the item and db ids off the file
			// ===========================================================
			String[] files = dropbox_dir.list();
			String shortName;
			for (String file : files) {
				if (D2L.hasPatternItemDB(file)) {
					tmpFile = new File(dropbox_dir, file);
					shortName = D2L.getNoItemNoDBName(file);
					destFile = new File(dropbox_dir, shortName);
					tmpFile.renameTo(destFile);

					System.out.printf("Renaming %s ----->  %s ...... %n", file,
							shortName);

				}
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	} // end of the method main
} // end of the class Main
