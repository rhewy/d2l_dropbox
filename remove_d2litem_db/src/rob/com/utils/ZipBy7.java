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
import java.util.ArrayList;

import net.sf.sevenzipjbinding.ExtractOperationResult;
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
		boolean isZipFile = false;
		// ===========================================================
		// Try to open the file as a zip file if that fails
		// then assume it is not a zip file
		// ===========================================================
		if (!archiveFilename.getName().endsWith(".odb"))
		{
			try
			{
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						archiveFilename.getAbsolutePath(), "r");
				ISevenZipInArchive inArchive = SevenZip.openInArchive(null,
						new RandomAccessFileInStream(randomAccessFile));
				inArchive.close();
				isZipFile = true;
			} // end of try
			catch (Exception e)
			{
			} // end of catch
		}
		return isZipFile;
	} // end of method

	// ==============================================================================
	// Method : unZipWholeFile
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose :
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created MMM-DD-YYYY (fl)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	public static void unZipWholeFile(File archive, File toDir,
			ArrayList<FileExtension> skipExtensions)
	{
		if (isArchive(archive))
		{
			try
			{
				System.out.println("Trying to unzip "
						+ archive.getAbsolutePath());
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
				ExtractOperationResult result = null;
				// ===========================================================
				// For every file in the archive. Note: dirs are implied by
				// the file's path
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
						boolean skipFile = false;
						// ===========================================================
						// Skip common document formats that use zip e.g. LibreOffice
						// .odb, ods, odt, odp, odd
						// ===========================================================
						ExtensionsToSkip skip = new ExtensionsToSkip();
						for(FileExtension extension:skip.getExtensions())
						{
							if(item.getPath().endsWith(extension.getExtention()))
							{
								skipFile = true;
							}
						}

						if (!skipFile)
						{
							// ===========================================================
							// extract the file at the new location
							// ===========================================================
							try
							{
								bigStream = new FileExtractStream(tmpLoc);
								result = item.extractSlow(bigStream);
								bigStream.close();
							}
							catch (Exception e)
							{
								System.out
										.printf("Problem extacting %s : from file : %s : result %s",
												item.getPath(),
												archive.getAbsolutePath(),
												result);
							}
						}
						tmpLoc = null;
						bigStream = null;
						System.out.println("Done unzipping .... "
								+ archive.getAbsolutePath());
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

