package rob.com.utils;

import java.io.File;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

public class ZipBy7
{
	static public boolean isArchive(String archiveFilename)

	{
		boolean zipFile = false;
		// ===========================================================
		// Try to open the file as a zip file if that fails
		// then assume it is not a zip file
		// ===========================================================

		try
		{
			RandomAccessFile randomAccessFile = new RandomAccessFile(
					archiveFilename, "r");
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
		if (isArchive(archive.getAbsolutePath()))
		{
			try
			{
				RandomAccessFile randomAccessFile = new RandomAccessFile(
						archive.getAbsolutePath(), "r");
				ISevenZipInArchive inArchive = SevenZip.openInArchive(null,
						new RandomAccessFileInStream(randomAccessFile));
				ISimpleInArchive simpleArchive = inArchive.getSimpleInterface();

				// ===========================================================
				// Switch/Get the simple interface
				// ===========================================================
				ISimpleInArchiveItem[] items = simpleArchive.getArchiveItems();

				FileExtractStream bigStream;
				File tmpLoc;
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
					}
				}

				inArchive.close();
			} // end of try
			catch (Exception e)
			{
				e.printStackTrace();
			} // end of catch
		} // end of if is a zip file
	} // end of method
} // end of class

