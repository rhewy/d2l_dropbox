package rob.com.utils;

//==============================================================================
// File         : ZibBy7.java
//
// Current Author: Robert Hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@.com
//
// Purpose : Contains methods for working with the 7Zip JNI
//           e.g. unZipWholeFile
//
// Dependencies: Most of these methods will require parts of the following
//               JARS: sevenzipjbinding and sevenzipjbinding-AllWindows 
//
// Modification Log :
//    --> Created OCT-25-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
import java.io.File;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

public class ZipBy7
{
	// ==============================================================================
	// Method : isArchive
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Determine if the "file" is a true archive file
	// e.g. zip 7z --> yes txt doc csv html --> no
	//
	// Dependencies: sevenzipjbinding and sevenzipjbinding-AllWindows
	//
	// Modification Log :
	// --> Created OCT-25-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	static public boolean isArchive(File archiveFilename)

	{
		boolean zipFile = false;
		// ===========================================================
		// Try to open the file as a zip file if that fails
		// then assume it is not a zip file
		// ===========================================================

		try
		{
			RandomAccessFile randomAccessFile = new RandomAccessFile(
					archiveFilename.getAbsolutePath(), "r");
			ISevenZipInArchive inArchive = SevenZip.openInArchive(null,
					new RandomAccessFileInStream(randomAccessFile));
			inArchive.close();
			zipFile = true;
		} // end of try
		catch (Exception e)
		{
		} // end of catch
		return zipFile;
	} // end of method

	public static void unZipWholeFile(File archive, File toDir)
	{
		if (isArchive(archive))
		{
			try
			{
				// ===========================================================
				// open the archive file as a random access file
				// ===========================================================
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						archive.getAbsolutePath(), "r");
				ISevenZipInArchive inArchive = SevenZip.openInArchive(null,
						new RandomAccessFileInStream(randomAccessFile));
				ISimpleInArchive simpleArchive = inArchive.getSimpleInterface();

				// ===========================================================
				// Switch/Get the simple interface
				// ===========================================================
				ISimpleInArchiveItem[] items = simpleArchive.getArchiveItems();

				// ===========================================================
				// One would need a file/dir location to write the data to
				// and an ISequentialOutStream object to do the writing
				// I built a custom class the wrapped a standard java 
				// FileOutputStream. Take a look at the class FileExtractStream 
				// ===========================================================
				File tmpLoc;
				FileExtractStream bigStream;
				// ===========================================================
				// For every file in the archive. Note: dirs are implied by
				// the files path
				// ===========================================================
				for (ISimpleInArchiveItem item : items)
				{
					// ===========================================================
					// Skip the folders FileExtractStream will make them if
					// needed by calling mkdirs on the parent file
					// ===========================================================
					if (!item.isFolder())
					{
						tmpLoc = new File(toDir, item.getPath());
						// ===========================================================
						// extract the file at the new location
						// ===========================================================
						bigStream = new FileExtractStream(tmpLoc);
						item.extractSlow(bigStream);

						tmpLoc = null;
						bigStream = null;
					} // end of skip folders only do files
				} // end of the for

				inArchive.close();
			} // end of try
			catch (Exception e)
			{
				e.printStackTrace();
			} // end of catch
		} // end of if is a zip file
	} // end of method
} // end of class

